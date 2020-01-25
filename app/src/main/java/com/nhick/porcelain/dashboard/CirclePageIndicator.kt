package com.nhick.porcelain.dashboard

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CirclePageIndicator() : RecyclerView.ItemDecoration() {

    private val colorActive = 0x727272
    private val colorInActive =0xF44336

    private val dp = Resources.getSystem().displayMetrics.density

    private val heightIndicator = (dp * 16).toInt()

    private val strokeIndicatorWidth = dp * 2

    private val itemLengthIndicator = dp * 16

    private val itemPaddingIndicator = dp * 4

    private val interPolator = AccelerateDecelerateInterpolator()

    private val paint = Paint()

    init {
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = strokeIndicatorWidth
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true
    }


    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.adapter?.itemCount?.minus(1)

        val totalLength = itemLengthIndicator * itemCount!!

        val paddingBetweenItems = 0.coerceAtLeast(itemCount - 1) * itemPaddingIndicator

        val indicatorTotalWidth = totalLength + paddingBetweenItems

        val indicatorStartX = (parent.width - indicatorTotalWidth) /2f

        val indicatorPosY = (parent.height - heightIndicator) / 2f

        drawInactiveIndicators(c,indicatorStartX,indicatorPosY ,itemCount)

        val layoutManager = parent.layoutManager as LinearLayoutManager?

        val activePosition = layoutManager!!.findFirstVisibleItemPosition()

        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }

        // find offset of active page (if the user is scrolling)
        // find offset of active page (if the user is scrolling)
        val activeChild: View = layoutManager.findViewByPosition(activePosition)!!
        val left: Int = activeChild.left
        val width: Int = activeChild.width

        // on swipe the active item will be positioned from [-width, 0]
        // interpolate offset for smooth animation
        // on swipe the active item will be positioned from [-width, 0]
        // interpolate offset for smooth animation
        val progress: Float = interPolator.getInterpolation(left * -1 / width.toFloat())

        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount)

    }

    private fun drawInactiveIndicators(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, itemCount: Int) {
        paint.color = Color.GRAY
        // width of item indicator including padding
        val itemWidth: Float = itemLengthIndicator + itemPaddingIndicator
        var start = indicatorStartX
        for (i in 0 until itemCount) { // draw the line for every item
            c.drawCircle(start + itemLengthIndicator, indicatorPosY, itemWidth / 6, paint)
            //  c.drawLine(start, indicatorPosY, start + mIndicatorItemLength, indicatorPosY, mPaint);
            start += itemWidth
        }
    }

    private fun drawHighlights(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, highlightPosition: Int, progress: Float, itemCount: Int) {
        paint.color = Color.WHITE
        // width of item indicator including padding
        val itemWidth: Float = itemLengthIndicator + itemPaddingIndicator
        if (progress == 0f) { // no swipe, draw a normal indicator
            val highlightStart = indicatorStartX + itemWidth * highlightPosition
            /*   c.drawLine(highlightStart, indicatorPosY,
                    highlightStart + mIndicatorItemLength, indicatorPosY, mPaint);
        */c.drawCircle(highlightStart, indicatorPosY, itemWidth / 6, paint)
        } else {
            var highlightStart = indicatorStartX + itemWidth * highlightPosition
            // calculate partial highlight
            val partialLength: Float = itemLengthIndicator * progress
            c.drawCircle(
                highlightStart + itemLengthIndicator,
                indicatorPosY,
                itemWidth / 6,
                paint
            )

        }
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State){
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = heightIndicator
    }



}