package com.fphoenixcorneae.ximalaya.common.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fphoenixcorneae.coretrofit.exception.ApiException
import com.fphoenixcorneae.coretrofit.exception.ExceptionHandle
import com.fphoenixcorneae.coretrofit.model.BaseResponse
import com.fphoenixcorneae.coretrofit.model.NetResult
import com.fphoenixcorneae.coretrofit.model.paresException
import com.fphoenixcorneae.coretrofit.model.paresResult
import com.fphoenixcorneae.ext.appContext
import com.fphoenixcorneae.ext.loge
import com.fphoenixcorneae.jetpackmvvm.base.activity.BaseActivity
import com.fphoenixcorneae.jetpackmvvm.base.fragment.BaseFragment
import com.fphoenixcorneae.jetpackmvvm.base.viewmodel.BaseViewModel
import com.fphoenixcorneae.jetpackmvvm.livedata.Event
import com.fphoenixcorneae.ximalaya.common.R
import kotlinx.coroutines.*

/**
 * @desc：BaseViewModel 请求协程封装
 */

/**
 * 显示页面状态
 * @param netResult   接口返回值
 * @param onSuccess   成功回调
 * @param onError     失败回调
 * @param onLoading   加载中
 */
fun <T> BaseActivity<*>.parseState(
    netResult: NetResult<T>,
    onSuccess: (T) -> Unit,
    onError: ((ApiException) -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
) {
    when (netResult) {
        is NetResult.Loading -> {
            showLoading(netResult.loadingMessage)
            onLoading?.run { this }
        }
        is NetResult.Success -> {
            showContent()
            onSuccess(netResult.data)
        }
        is NetResult.Error -> {
            showError(netResult.exception.errorMsg)
            onError?.run { this(netResult.exception) }
        }
    }
}

/**
 * 显示页面状态
 * @param netResult   接口返回值
 * @param onSuccess   成功回调
 * @param onError     失败回调
 * @param onLoading   加载中
 */
fun <T> BaseFragment<*>.parseState(
    netResult: NetResult<T>,
    onSuccess: (T) -> Unit,
    onError: ((ApiException) -> Unit)? = null,
    onLoading: (() -> Unit)? = null
) {
    when (netResult) {
        is NetResult.Loading -> {
            showLoading(netResult.loadingMessage)
            onLoading?.invoke()
        }
        is NetResult.Success -> {
            showContent()
            onSuccess(netResult.data)
        }
        is NetResult.Error -> {
            showError(netResult.exception.errorMsg)
            onError?.run { this(netResult.exception) }
        }
    }
}


/**
 * net request 不校验请求结果数据是否是成功
 * @param block          请求体方法，返回值为 [BaseResponse]
 * @param netResult      请求回调的 [NetResult] 数据
 * @param isShowDialog   是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestBaseNoCheck(
    block: suspend () -> BaseResponse<T>,
    netResult: MutableLiveData<NetResult<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = appContext.getString(R.string.common_loading_message)
): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) {
                netResult.value = NetResult.onLoading(loadingMessage)
            }
            // 请求体
            block()
        }.onSuccess {
            netResult.paresResult(it)
        }.onFailure {
            it.message?.loge()
            netResult.paresException(it)
        }
    }
}

/**
 * net request 不校验请求结果数据是否是成功
 * @param block          请求体方法，返回值为 [T]
 * @param netResult      请求回调的 [NetResult] 数据
 * @param isShowDialog   是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestAnyNoCheck(
    block: suspend () -> T,
    netResult: MutableLiveData<NetResult<T>>,
    isShowDialog: Boolean = false,
    loadingMessage: String = appContext.getString(R.string.common_loading_message)
): Job {
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) {
                netResult.value = NetResult.onLoading(loadingMessage)
            }
            // 请求体
            block()
        }.onSuccess {
            netResult.paresResult(it)
        }.onFailure {
            it.message?.loge()
            netResult.paresException(it)
        }
    }
}

/**
 * 过滤服务器结果，失败抛异常
 * @param block          请求体方法，必须要用 suspend 关键字修饰，返回值为 [BaseResponse]
 * @param success        成功回调
 * @param error          失败回调，可不传
 * @param isShowDialog   是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (ApiException) -> Unit = {},
    isShowDialog: Boolean = true,
    loadingMessage: String = appContext.getString(R.string.common_loading_message)
): Job {
    // 如果需要弹窗 通知Activity/fragment弹窗
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) {
                loadingChange.showDialog.postValue(Event(loadingMessage))
            }
            // 请求体
            block()
        }.onSuccess {
            // 网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(Event(true))
            runCatching {
                // 校验请求结果码是否正确，不正确会抛出异常走下面的onFailure
                executeResponse(it) { t ->
                    success(t)
                }
            }.onFailure { e ->
                // 打印错误消息
                e.message?.loge()
                // 失败回调
                error(ExceptionHandle.handleException(e))
            }
        }.onFailure {
            // 网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(Event(true))
            // 打印错误消息
            it.message?.loge()
            // 失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 * 不过滤请求结果
 * @param block          请求体 必须要用 suspend 关键字修饰
 * @param success        成功回调
 * @param error          失败回调 可不给
 * @param isShowDialog   是否显示加载框
 * @param loadingMessage 加载框提示内容
 */
fun <T> BaseViewModel.requestNoCheck(
    block: suspend () -> T,
    success: (T) -> Unit,
    error: (ApiException) -> Unit = {},
    isShowDialog: Boolean = true,
    loadingMessage: String = appContext.getString(R.string.common_loading_message)
): Job {
    // 如果需要弹窗 通知Activity/fragment弹窗
    if (isShowDialog) {
        loadingChange.showDialog.postValue(Event(loadingMessage))
    }
    return viewModelScope.launch {
        runCatching {
            // 请求体
            block()
        }.onSuccess {
            // 网络请求成功 关闭弹窗
            loadingChange.dismissDialog.postValue(Event(true))
            // 成功回调
            success(it)
        }.onFailure {
            // 网络请求异常 关闭弹窗
            loadingChange.dismissDialog.postValue(Event(true))
            // 打印错误消息
            it.message?.loge()
            // 失败回调
            error(ExceptionHandle.handleException(it))
        }
    }
}

/**
 * 请求结果过滤，判断请求服务器请求结果是否成功，不成功则会抛出异常
 */
suspend fun <T> executeResponse(
    response: BaseResponse<T>,
    success: suspend CoroutineScope.(T) -> Unit
) {
    coroutineScope {
        when {
            response.isSuccess() -> {
                success(response.getResponseData())
            }
            else -> {
                throw ApiException(
                    response.getResponseCode(),
                    response.getResponseMsg()
                )
            }
        }
    }
}

/**
 * 调用携程
 * @param block   操作耗时操作任务
 * @param success 成功回调
 * @param error   失败回调，可不传
 */
fun <T> BaseViewModel.launch(
    block: suspend () -> T,
    success: (T) -> Unit,
    error: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        kotlin.runCatching {
            withContext(Dispatchers.IO) {
                block()
            }
        }.onSuccess {
            success(it)
        }.onFailure {
            error(it)
        }
    }
}
