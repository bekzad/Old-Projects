package com.bektursun.sokobanandroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createScaledBitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import com.bektursun.sokobanandroid.SokobanProperties.Companion.BOX
import com.bektursun.sokobanandroid.SokobanProperties.Companion.BOX_ON_TARGET
import com.bektursun.sokobanandroid.SokobanProperties.Companion.PLAYER
import com.bektursun.sokobanandroid.SokobanProperties.Companion.TARGET
import com.bektursun.sokobanandroid.SokobanProperties.Companion.WALL
import com.example.sokobanandroid.R


class SokobanCanvas : View {

    private val model: Model
    private var wall: Bitmap?
    private var player: Bitmap?
    private var box: Bitmap?
    private var target: Bitmap?
    private var onTarget: Bitmap?
    private var isSetView: Boolean

    constructor(model: Model, context: Context) : super(context) {
        this.model = model
        wall = null
        player = null
        box = null
        target = null
        onTarget = null
        isSetView = false
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isSetView) {
            drawMap(canvas)
        }
    }

    fun update() {
        invalidate()
    }

    private fun drawMap(canvas: Canvas) {
        if (model.isSetView()) {
            val arrayMap = model.getArrayGameMap()
            val mapObjectSize = calculateMapObjectSize(arrayMap!!)
            createBitmapMapObjects(mapObjectSize)
            drawMapObjects(
                arrayMap, canvas, mapObjectSize,
                calculateDifferenceInVerticalOffsetsOfTheMap(mapObjectSize, arrayMap[0].size)
            )
        }

    }

    private fun createBitmapMapObjects(objectSize: Int) {
        wall = createScaledBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.rsz_wall
            ),
            objectSize,
            objectSize,
            true
        )
        player = createScaledBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.character
            ),
            objectSize,
            objectSize,
            true
        )
        box = createScaledBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.lootbox
            ),
            objectSize,
            objectSize,
            true
        )
        target = createScaledBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.close
            ),
            objectSize,
            objectSize,
            true
        )
        onTarget = createScaledBitmap(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.loot_boxsecond
            ),
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
                    BOX_ON_TARGET -> {
                        canvas.drawBitmap(onTarget!!, coordinateX, coordinateY, paint)
                    }
                }
                coordinateX += mapObjectSize.toFloat()
            }
            coordinateX = 0f
            coordinateY += mapObjectSize.toFloat()
        }
    }

    private fun calculateDifferenceInVerticalOffsetsOfTheMap(
        objectSize: Int,
        arrayMapVerticalCount: Int
    ): Int {
        val mapHeight = objectSize * arrayMapVerticalCount
        val screenHeight = height
        val screenDifferences = screenHeight - mapHeight
        return screenDifferences / 2
    }

    private fun calculateMapObjectSize(arrayMap: Array<IntArray>): Int {
        val screenWidth: Int = width
        val dimension =  screenWidth / arrayMap[0].size
        return dimension
    }

    fun setViewOrNot(isSet: Boolean) {
        isSetView = isSet
    }
}