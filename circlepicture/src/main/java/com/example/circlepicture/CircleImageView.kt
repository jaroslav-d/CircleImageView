package com.example.circlepicture

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.drawable.toBitmap

class CircleImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    private var mBackground: Drawable? = null

    override fun setBackground(background: Drawable?) {
        mBackground = background
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mBackground == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        } else {
            val width = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY)
                MeasureSpec.getSize(widthMeasureSpec) else mBackground!!.minimumWidth
            val height = if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY)
                MeasureSpec.getSize(heightMeasureSpec) else mBackground!!.minimumHeight
            setMeasuredDimension(width, height)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mBackground != null) {
            val path = Path().apply {
                addCircle(width/2f, height/2f, (width + height)/4f, Path.Direction.CCW)
//                addOval(0f, 0f, width.toFloat(), height.toFloat(), Path.Direction.CCW)
            }
            val picture = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).applyCanvas {
                clipPath(path)
                drawBitmap(mBackground!!.toBitmap(width, height), 0f, 0f, null)
            }
            canvas.drawBitmap(picture, 0f, 0f, null)
        }
    }
}