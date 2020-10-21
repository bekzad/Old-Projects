package labs.o.sokobanmvcpattern

import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Canvas(private val model: Model, context: AppCompatActivity) : View(context) {
    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(50F, 50F, 100F, 100F, paint)

    }
}