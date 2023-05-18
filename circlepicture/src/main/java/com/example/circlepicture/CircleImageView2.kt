package com.example.circlepicture

import android.content.Context
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable

class CircleImageView2(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    override fun setBackground(background: Drawable?) {
        if (background == null) {
            super.setBackground(background)
        } else {
            super.setBackground(background.toCircle())
        }
    }

    override fun setImageDrawable(drawable: Drawable?) {
        if (drawable == null) {
            super.setImageDrawable(drawable)
        } else {
            super.setImageDrawable(drawable.toCircle())
        }
    }

    private fun Drawable.toCircle(): Drawable {
        val width = this.intrinsicWidth
        val height = this.intrinsicHeight
        val path = Path().apply {
            addCircle(width/2f, height/2f, (width + height)/4f, Path.Direction.CCW)
//                addOval(0f, 0f, width.toFloat(), height.toFloat(), Path.Direction.CCW)
        }
        val circleBackground = createBitmap(width, height).applyCanvas {
            clipPath(path)
            drawBitmap(this@toCircle.toBitmap(), 0f, 0f, null)
        }
        return circleBackground.toDrawable(resources)
    }
}