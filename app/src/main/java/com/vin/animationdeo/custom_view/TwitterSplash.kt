package com.vin.animationdeo.custom_view

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.vin.animationdeo.R

class TwitterSplash @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val path = Path()
    private val paint: Paint
    private val image: Bitmap

    init {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, android.R.color.white)
            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

        image = BitmapFactory.decodeResource(resources, R.drawable.twitter_icon)

        Handler(Looper.getMainLooper()).postDelayed({
            path.addCircle(width / 2f, height / 2f, 200f, Path.Direction.CCW)

            path.close()

            invalidate()
        }, 1000)
    }


    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

        //canvas?.drawPath(path, paint)
        canvas?.drawBitmap(
            image,
            (width / 2f) - (image.width / 2),
            (height / 2f) - (image.height / 2),
            paint
        )
    }
}