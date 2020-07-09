package com.vin.animationdeo.custom_view

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.vin.animationdeo.util.Utility

class FrameCutLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val path = Path()
    private val paint: Paint
    private val imageDimen = 160
    private val imageDimenDP = Utility.toDP(context, imageDimen)
    private var scale = 1f;
    private var transparency = 0f;

    init {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, android.R.color.white)
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val left = (width / 2f) - (imageDimenDP / 2)
            val top = (height / 2f) - (imageDimenDP / 2)
            path.addRect(
                RectF(left, top, left + imageDimenDP, top + imageDimenDP),
                Path.Direction.CCW
            )

            path.close()

            invalidate()
        }, 1000)
    }


    fun setImageTransparency(transparency: Float) {
        this.transparency = transparency
    }

    fun setCutDimension(scale: Float) {
        this.scale = scale
        invalidate()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        paint.alpha = (transparency * 255).toInt()

        canvas?.scale(scale, scale, (width/2).toFloat(), (height/2).toFloat())
        canvas?.drawPath(path, paint)
    }
}