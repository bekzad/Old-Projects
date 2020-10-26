package com.example.sokobanandroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import com.example.sokobanandroid.SokobanProperties.Companion.BOX
import com.example.sokobanandroid.SokobanProperties.Companion.PLAYER
import com.example.sokobanandroid.SokobanProperties.Companion.TARGET
import com.example.sokobanandroid.SokobanProperties.Companion.WALL


class SokobanCanvas : View {

    private val model: Model
    private var wall: Bitmap?
    private var player: Bitmap?
    private var box: Bitmap?
    private var target: Bitmap?

    constructor(model: Model, context: Context) : super(context) {
        this.model = model
        wall = null
        player = null
        box = null
        target = null
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawMap(canvas)
        val w = this.measuredWidth
        val h = this.measuredHeight
        println("measureW: $w, measureH: $h")
    }

    fun update() {
        invalidate()
    }

    private fun drawMap(canvas: Canvas) {
        val screenWidth: Int = width

        val desktop = model.getArrayGameMap()
        val mapObjectSize = screenWidth / desktop[0].size

        createBitmapMapObjects(mapObjectSize)
        println(desktop.size)
        drawMapObjects(desktop, canvas, mapObjectSize,
            calculateDifferenceInVerticalOffsetsOfTheMap(mapObjectSize, desktop.size)
        )
    }

    private fun createBitmapMapObjects(objectSize: Int) {
        wall = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.rsz_wall),
            objectSize,
            objectSize,
            true
        )
        player = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.character),
            objectSize,
            objectSize,
            true
        )
        box = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.lootbox),
            objectSize,
            objectSize,
            true
        )
        target = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, R.drawable.close),
            objectSize,
            objectSize,
            true
        )
    }

    private fun drawMapObjects(
        arrayMap: Array<IntArray>,
        canvas: Canvas,
        mapObjectSize: Int,
        mapYMargin: Int
    ) {

        var coordinateX = 0f
        var coordinateY = mapYMargin.toFloat()

        val paint = Paint()

        for (row in arrayMap) {
            for (column in row) {
                when (column) {
                    WALL -> {
                        canvas.drawBitmap(wall!!, coordinateX, coordinateY, paint)
                    }
                    TARGET -> {
                        canvas.drawBitmap(target!!, coordinateX, coordinateY, paint)
                    }
                    BOX -> {
                        canvas.drawBitmap(box!!, coordinateX, coordinateY, paint)
                    }
                    PLAYER -> {
                        canvas.drawBitmap(player!!, coordinateX, coordinateY, paint)
                    }
                }
                coordinateX += mapObjectSize.toFloat()
            }
            coordinateX = 0f
            coordinateY += mapObjectSize.toFloat()
        }
    }

    private fun calculateDifferenceInVerticalOffsetsOfTheMap(objectSize: Int, arrayMapVerticalCount: Int): Int {
        val mapHeight = objectSize * arrayMapVerticalCount
        val screenHeight = height
        val screenDifferences = screenHeight - mapHeight
        val mapYMargin = screenDifferences / 2
        return mapYMargin
    }

}