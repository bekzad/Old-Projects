package com.bektursun.sokobanandroid

import android.content.DialogInterface
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_EIGHT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FIVE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FOUR
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_NINE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_ONE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_SEVEN
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_SIX
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_THREE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_TWO
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
import com.bektursun.sokobanandroid.SokobanProperties.Companion.SWIPE_THRESHOLD
import com.bektursun.sokobanandroid.SokobanProperties.Companion.SWIPE_VELOCITY_THRESHOLD
import kotlin.math.abs


class Controller : View.OnTouchListener, GestureDetector.SimpleOnGestureListener,
    DialogInterface.OnClickListener {

    private val model: Model
    private val gestureDetector: GestureDetector

    constructor(viewer: Viewer) {
        this.model = Model(viewer)
        this.gestureDetector = GestureDetector(viewer, this)
    }


    fun getModel(): Model {
        return model
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            println(event.x)
            println(event.y)

            val x = 170f
            val y = 210f

            if (abs(event.x) >= x && abs(event.y) <= y) {
                println("REFRESH!")
            }
        }
        return gestureDetector.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(
        motionStart: MotionEvent,
        motionEnd: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return userSwipe(motionStart, motionEnd, velocityX, velocityY)
    }

    private fun userSwipe(
        motionStart: MotionEvent,
        motionEnd: MotionEvent, velocityX: Float,
        velocityY: Float
    ): Boolean {

        var result = false

        val diffY = motionEnd.y - motionStart.y
        val diffX = motionEnd.x - motionStart.x

        if (abs(diffX) > abs(diffY)) {
            if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) onSwipeRight()
                else onSwipeLeft()

                result = true
            }

        } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffY > 0) onSwipeBottom()
            else onSwipeTop()

            result = true
        }
        return result
    }

    private fun onSwipeRight() {
        model.move(MOVE_RIGHT)
    }

    private fun onSwipeLeft() {
        model.move(MOVE_LEFT)
    }

    private fun onSwipeBottom() {
        model.move(MOVE_BOTTOM)
    }

    private fun onSwipeTop() {
        model.move(MOVE_TOP)
    }

    override fun onClick(dialogInterface: DialogInterface, level: Int) {
        when (level) {
            0 -> model.chooseLocalLevel(MAP_LEVEL_ONE)
            1 -> model.chooseLocalLevel(MAP_LEVEL_TWO)
            2 -> model.chooseLocalLevel(MAP_LEVEL_THREE)
            3 -> model.chooseLocalLevel(MAP_LEVEL_FOUR)
            4 -> model.chooseLocalLevel(MAP_LEVEL_FIVE)
            5 -> model.chooseLocalLevel(MAP_LEVEL_SIX)
            6 -> model.chooseLevel(MAP_LEVEL_SEVEN)
            7 -> model.chooseLevel(MAP_LEVEL_EIGHT)
            else -> model.chooseLevel(MAP_LEVEL_NINE)
        }
        dialogInterface.dismiss()
    }

}