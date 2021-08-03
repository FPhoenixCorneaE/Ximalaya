package com.fphoenixcorneae.ximalaya.common.widget

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import com.fphoenixcorneae.ext.dpToPx
import kotlin.math.min
import kotlin.math.tan

/**
 * @desc：GlobalPlay
 * @date：2021/08/03 14:27
 */
class GlobalPlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs), AnimatorUpdateListener {
    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
    }
    private val mBpPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
    }
    private var mWidth = 0f
    private var mHeight = 0f
    private val mUnreachColor = -0x7759595a
    private val mReachedColor = -0x8fb0
    private var mRadius = 0f
    private var mBarWidth = 0f
    private var mBitmap: Bitmap? = null
    private var mPlayPath = Path()

    /**
     * 0.0 ~ 1.0
     */
    private var mProgress = 0f

    /**
     * 矩形范围
     */
    private var mRectF = RectF()
    private var mMatrix = Matrix()
    private var mShader: BitmapShader? = null
    private var mDegree = 0
    private var isPlaying = false
    private val mAnimator by lazy {
        ValueAnimator.ofInt(360).apply {
            duration = 5000
            interpolator = LinearInterpolator()
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener(this@GlobalPlay)
        }
    }
    private var mPathEffect: CornerPathEffect = CornerPathEffect(2.dpToPx())

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w.toFloat()
        mHeight = h.toFloat()
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        if (mBitmap == null) {
            throw NullPointerException("You should call setBitmap() method first!")
        }
        // 0.先将坐标移至中心点
        canvas.translate(mWidth / 2, mHeight / 2)
        canvas.rotate(-90f)
        // 1.画未到达进度条弧形
        mPaint.style = Paint.Style.STROKE
        mPaint.color = mUnreachColor
        mPaint.strokeWidth = mBarWidth
        canvas.drawArc(mRectF, mProgress * 360, (1 - mProgress) * 360, false, mPaint)
        // 2.画到达进度条弧形
        mPaint.color = mReachedColor
        canvas.drawArc(mRectF, 0f, mProgress * 360, false, mPaint)
        canvas.rotate(90f)
        // 3.画圆形图片
        mMatrix.setRotate(mDegree.toFloat(), (mBitmap!!.width shr 1).toFloat(), (mBitmap!!.height shr 1).toFloat())
        mMatrix.postTranslate((-mBitmap!!.width shr 1).toFloat(), (-mBitmap!!.height shr 1).toFloat())
        val scale = (mRadius - mBarWidth) * 1.0f / (min(mBitmap!!.width, mBitmap!!.height) shr 1)
        mMatrix.postScale(scale, scale)
        mShader!!.setLocalMatrix(mMatrix)
        mBpPaint.shader = mShader
        canvas.drawCircle(0f, 0f, mRadius - mBarWidth, mBpPaint)
        // 4.绘制半透明蒙版
        if (isPlaying) {
            return
        }
        mPaint.color = -0x77000001
        mPaint.style = Paint.Style.FILL
        canvas.drawCircle(0f, 0f, mRadius - mBarWidth, mPaint)
        mPaint.color = mReachedColor
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.pathEffect = mPathEffect
        canvas.translate(mRadius / 2.3f, 0f)
        // 5.绘制开始播放按钮
        canvas.drawPath(mPlayPath, mPaint)
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        mDegree = animation.animatedValue as Int
        invalidate()
    }

    fun play(drawable: Drawable) {
        setBitmap(drawable.toBitmap())
        isPlaying = true
        if (mAnimator.isStarted) {
            mAnimator.resume()
        } else {
            mAnimator.start()
        }
    }

    fun play(@DrawableRes res: Int) {
        setBitmap(BitmapFactory.decodeResource(resources, res))
        isPlaying = true
        if (mAnimator.isStarted) {
            mAnimator.resume()
        } else {
            mAnimator.start()
        }
    }

    fun pause() {
        mAnimator.pause()
        isPlaying = false
        invalidate()
    }

    fun setProgress(progress: Float) {
        mProgress = progress
        invalidate()
    }

    fun show() {
        animate().translationY(0f)
            .setDuration(300)
            .withStartAction {
                if (isPlaying) {
                    if (mAnimator.isStarted) {
                        mAnimator.resume()
                    } else {
                        mAnimator.start()
                    }
                }
                visibility = VISIBLE
            }
    }

    fun hide() {
        animate().translationY(height.toFloat())
            .setDuration(300)
            .withEndAction {
                visibility = GONE
                mAnimator.pause()
            }
    }

    fun setImage(drawable: Drawable) {
        setBitmap(drawable.toBitmap())
        invalidate()
    }

    fun setImage(@DrawableRes res: Int) {
        setBitmap(BitmapFactory.decodeResource(resources, res))
        invalidate()
    }

    fun setBitmap(@DrawableRes res: Int) = apply {
        setBitmap(BitmapFactory.decodeResource(resources, res))
    }

    fun setBitmap(bitmap: Bitmap) = apply {
        mBitmap = bitmap
        mShader = BitmapShader(mBitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    }

    fun setSize(width: Float, height: Float) = apply {
        mWidth = width
        mHeight = height
    }

    fun setRadius(radius: Float) = apply {
        mRadius = radius
        mRectF.set(-mRadius, -mRadius, mRadius, mRadius)
        // 考虑线条宽度,向内缩小半个宽度
        mRectF.inset(mBarWidth / 2, mBarWidth / 2)
        mPlayPath.reset()
        mPlayPath.moveTo(0f, 0f)
        mPlayPath.lineTo(-mRadius / 1.4f, (tan(Math.toRadians(30.0)) * mRadius / 1.4f).toFloat())
        mPlayPath.lineTo(-mRadius / 1.4f, (-(tan(Math.toRadians(30.0)) * mRadius / 1.4f)).toFloat())
        mPlayPath.close()
    }

    fun setBarWidth(barWidth: Float) = apply {
        mBarWidth = barWidth
    }
}