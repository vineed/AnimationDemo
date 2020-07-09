package com.vin.animationdeo.custom_view

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.scale
import com.vin.animationdeo.R
import com.vin.animationdeo.util.Utility

class TwitterCutLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val path = Path()
    private val paint: Paint
    private val imageDimen = 160
    private val imageDimenDP = Utility.toDP(context, imageDimen)
    private var scale = 1f;
    private var transparency = 1f;
    private var image: Bitmap

    init {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = ContextCompat.getColor(context, android.R.color.white)
            xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
        }
        paint.isAntiAlias = true

        image = BitmapFactory.decodeResource(resources, R.drawable.twitter_icon)
        image = Bitmap.createScaledBitmap(image, imageDimenDP, imageDimenDP, true)

        image.setHasAlpha(true)
    }

    fun setCutDimension(scale: Float) {
        this.scale = scale
        invalidate()
    }/*

    fun setImageTransparency(transparency: Float) {
        this.transparency = transparency
    }*/

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.scale(scale, scale, (width/2).toFloat(), (height/2).toFloat())
        canvas?.drawBitmap(
            image,
            (width / 2f) - (image.width / 2),
            (height / 2f) - (image.height / 2),
            paint
        )
        super.dispatchDraw(canvas)
        //paint.alpha = (transparency * 255).toInt()
    }
}