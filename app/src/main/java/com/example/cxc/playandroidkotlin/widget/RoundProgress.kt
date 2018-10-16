package com.example.cxc.playandroidkotlin.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.cxc.playandroidkotlin.R
import kotlin.math.log

class RoundProgress(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : View(context, attrs, defStyleAttr) {

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null, 0)

    var color: Int?
    var progress: Int?
    var mPaint: Paint?
    var mWidth: Int? = null
    var mHeight: Int? = null
    var padding: Int? = null


    private var radius: Float?

    private var backgroundColor: Int?

    init {
        var attrs = context?.obtainStyledAttributes(attrs, R.styleable.roundProgressStyle)
        color = attrs?.getColor(R.styleable.roundProgressStyle_color, Color.GRAY)
        progress = attrs?.getInt(R.styleable.roundProgressStyle_progress, 0)
        radius = attrs?.getDimension(R.styleable.roundProgressStyle_radius, 10f)
        backgroundColor = attrs?.getColor(R.styleable.roundProgressStyle_backgroundColor, Color.GRAY)
        padding = 10
        attrs?.recycle()

        if (progress!! < 0 || progress!! > 100) {
            throw Exception("输入进度只能在0-100之间")
        }


        mPaint = Paint()
        mPaint?.color = backgroundColor!!
        mPaint?.isAntiAlias = true
        mPaint?.strokeWidth = 2F


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var pointA = PointF(radius!!.toFloat(), 0F + padding!!)
        var pointB = PointF(radius!!.toFloat(), radius!! * 2.toFloat())
        var pointC = PointF(mWidth!! - radius!!.toFloat(), radius!! * 2.toFloat())
        val pointD = PointF(mWidth!! - radius!!.toFloat(), 0F + padding!!)
        mPaint!!.style = Paint.Style.STROKE

        var path = Path()
        path.moveTo(pointA.x, pointA.y)
        path.lineTo(pointD.x, pointD.y)
        path.arcTo(RectF(pointD.x - radius!!.toFloat(), pointD.y, pointC.x + radius!!.toFloat(), pointC.y), 90F, -180F, true)
        path.moveTo(pointC.x, pointC.y)
        path.lineTo(pointB.x, pointB.y)
        path.arcTo(RectF(pointA.x - radius!!.toFloat(), pointA.y, pointB.x + radius!!.toFloat(), pointB.y), -90f, -180F, true)
        canvas!!.drawPath(path, mPaint)

        var percent = radius!! / mWidth!! * 100    //2.9  = radius
        Log.   d("tag","percent $percent")
        mPaint!!.color = color!!
        mPaint!!.style = Paint.Style.FILL

        var path1 = Path()
        when {
            progress!! < percent -> {  // 5/100
                Log.d("tag", "progress $progress")
                var radius1 = progress!! / percent * radius!!
                path1.addCircle(radius1, radius!!+padding!!/2,radius1,Path.Direction.CW)
                canvas.drawPath(path1,mPaint)
            }
            100 - progress!! < percent -> { //   95/100
                path1.moveTo(radius!!, (padding!!/2).toFloat())
                path1.arcTo(RectF(0f, (padding!!/2).toFloat(),radius!!+padding!!/2,radius!!),90f,180f,true)
//                path1.addCircle(radius1, radius!!+padding!!/2,radius1,Path.Direction.CW)
                canvas.drawPath(path1,mPaint)

            }
            else -> {  //  5/100  -  95/100

            }
        }


    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }
}
