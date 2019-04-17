package io.github.hurshi.fontmetrics.textview_style

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import io.github.hurshi.fontmetrics.R


class FontMetricsTextView : AppCompatTextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private val STROKE_WIDTH = 5.0f
    private var mAscentPaint: Paint? = null
    private var mTopPaint: Paint? = null
    private var mBaselinePaint: Paint? = null
    private var mDescentPaint: Paint? = null
    private var mBottomPaint: Paint? = null
    private var mMeasuredWidthPaint: Paint? = null
    private var mTextBoundsPaint: Paint? = null
    private var mLinePaint: Paint? = null
    private var mRectPaint: Paint? = null
    private var mIsTopVisible: Boolean = false
    private var mIsAscentVisible: Boolean = false
    private var mIsBaselineVisible: Boolean = false
    private var mIsDescentVisible: Boolean = false
    private var mIsBottomVisible: Boolean = false
    private var mIsBoundsVisible: Boolean = false
    private var mIsWidthVisible: Boolean = false


    init {
        mLinePaint = Paint()
        mLinePaint!!.color = Color.RED
        mLinePaint!!.strokeWidth = STROKE_WIDTH

        mAscentPaint = Paint()
        mAscentPaint!!.color = resources.getColor(R.color.ascent)
        mAscentPaint!!.strokeWidth = STROKE_WIDTH

        mTopPaint = Paint()
        mTopPaint!!.color = resources.getColor(R.color.top)
        mTopPaint!!.strokeWidth = STROKE_WIDTH

        mBaselinePaint = Paint()
        mBaselinePaint!!.color = resources.getColor(R.color.baseline)
        mBaselinePaint!!.strokeWidth = STROKE_WIDTH

        mBottomPaint = Paint()
        mBottomPaint!!.color = resources.getColor(R.color.bottom)
        mBottomPaint!!.strokeWidth = STROKE_WIDTH

        mDescentPaint = Paint()
        mDescentPaint!!.color = resources.getColor(R.color.descent)
        mDescentPaint!!.strokeWidth = STROKE_WIDTH

        mMeasuredWidthPaint = Paint()
        mMeasuredWidthPaint!!.color = resources.getColor(R.color.measured_width)
        mMeasuredWidthPaint!!.strokeWidth = STROKE_WIDTH
        mMeasuredWidthPaint!!.style = Paint.Style.STROKE

        mTextBoundsPaint = Paint()
        mTextBoundsPaint!!.color = resources.getColor(R.color.text_bounds)
        mTextBoundsPaint!!.strokeWidth = STROKE_WIDTH
        mTextBoundsPaint!!.style = Paint.Style.STROKE

        mRectPaint = Paint()
        mRectPaint!!.color = Color.BLACK
        mRectPaint!!.strokeWidth = STROKE_WIDTH
        mRectPaint!!.style = Paint.Style.STROKE


        mIsTopVisible = true
        mIsAscentVisible = true
        mIsBaselineVisible = true
        mIsDescentVisible = true
        mIsBottomVisible = true
        mIsBoundsVisible = true
        mIsWidthVisible = true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var startX = 0f
        var startY = 0f
        var stopX = this.measuredWidth.toFloat()
        var stopY = 0f

        // draw lines

        if (mIsTopVisible) {
            startY = paint.fontMetrics.top + baseline
            stopY = startY
            canvas.drawLine(startX, startY, stopX, stopY, mTopPaint!!)
        }

        if (mIsAscentVisible) {
            startY = paint.fontMetrics.ascent + baseline
            stopY = startY
            //mLinePaint.setColor(Color.GREEN);
            canvas.drawLine(startX, startY, stopX, stopY, mAscentPaint!!)
        }

        if (mIsBaselineVisible) {
            startY = baseline.toFloat()
            stopY = startY
            canvas.drawLine(startX, startY, stopX, stopY, mBaselinePaint!!)
        }

        if (mIsDescentVisible) {
            startY = paint.fontMetrics.descent + baseline
            stopY = startY
            canvas.drawLine(startX, startY, stopX, stopY, mDescentPaint!!)
        }

        if (mIsBottomVisible) {
            startY = paint.fontMetrics.bottom + baseline
            stopY = startY
            canvas.drawLine(startX, startY, stopX, stopY, mBottomPaint!!)
        }

        if (mIsBoundsVisible) {
            val textRect = Rect().also { paint.getTextBounds(text.toString(), 0, text.length, it) }
            textRect.left = textRect.left + paddingLeft
            textRect.top = textRect.top + baseline
            textRect.bottom = textRect.bottom + baseline
            textRect.right = textRect.right + paddingLeft
            canvas.drawRect(textRect, mTextBoundsPaint)
        }

        if (mIsWidthVisible) {
            canvas.drawLine(paddingLeft.toFloat(),0f,paddingLeft.toFloat(),height.toFloat(),mMeasuredWidthPaint)
            canvas.drawLine((width - paddingRight).toFloat(),0f,(width - paddingRight).toFloat(),height.toFloat(),mMeasuredWidthPaint)
//            canvas.drawRect(
//                Rect(paddingLeft, paddingTop, width - paddingRight, height - paddingBottom),
//                mMeasuredWidthPaint
//            )
        }
    }


    fun setTopVisible(isVisible: Boolean) {
        mIsTopVisible = isVisible
        invalidate()
    }

    fun setAscentVisible(isVisible: Boolean) {
        mIsAscentVisible = isVisible
        invalidate()
    }

    fun setBaselineVisible(isVisible: Boolean) {
        mIsBaselineVisible = isVisible
        invalidate()
    }

    fun setDescentVisible(isVisible: Boolean) {
        mIsDescentVisible = isVisible
        invalidate()
    }

    fun setBottomVisible(isVisible: Boolean) {
        mIsBottomVisible = isVisible
        invalidate()
    }

    fun setBoundsVisible(isVisible: Boolean) {
        mIsBoundsVisible = isVisible
        invalidate()
    }

    fun setWidthVisible(isVisible: Boolean) {
        mIsWidthVisible = isVisible
        invalidate()
    }
}
