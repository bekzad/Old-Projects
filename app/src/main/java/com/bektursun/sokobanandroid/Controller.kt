package com.bektursun.sokobanandroid

import android.content.DialogInterface
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_EIGHT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FIVE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FOUR
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_NINE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_ONE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_SEVEN
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_SIX
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_THREE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_TWO
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
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
            if (abs(diffX) > SokobanProperties.SWIPE_THRESHOLD && abs(velocityX) > SokobanProperties.SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
                result = true
            }

        } else if (abs(diffY) > SokobanProperties.SWIPE_THRESHOLD && abs(velocityY) > SokobanProperties.SWIPE_VELOCITY_THRESHOLD) {
            if (diffY > 0) {
                onSwipeBottom()
            } else {
                onSwipeTop()
            }
            result = true
        }

        return result
    }

    private fun onSwipeRight() {
        model.move(MOVE_RIGHT)
        if (model.getWinGame()) restartGame()
    }

    private fun onSwipeLeft() {
        model.move(MOVE_LEFT)
        if (model.getWinGame()) restartGame()
    }

    private fun onSwipeBottom() {
        model.move(MOVE_BOTTOM)
        if (model.getWinGame()) restartGame()
    }

    private fun onSwipeTop() {
        model.move(MOVE_TOP)
        if (model.getWinGame()) restartGame()
    }

    override fun onClick(dialogInterface: DialogInterface, level: Int) {
        println("level: $level")
        when (level) {
            0 -> {
                model.chooseLevel(LEVEL_ONE)
            }
            1 -> model.chooseLevel(LEVEL_TWO)
            2 -> model.chooseLevel(LEVEL_THREE)
            3 -> model.chooseLevel(MAP_LEVEL_FOUR)
            4 -> model.chooseLevel(MAP_LEVEL_FIVE)
            5 -> model.chooseLevel(MAP_LEVEL_SIX)
            6 -> model.chooseLevel(LEVEL_SEVEN)
            7 -> model.chooseLevel(LEVEL_EIGHT)
            else -> model.chooseLevel(LEVEL_NINE)
        }
        dialogInterface.dismiss()
    }

    private fun restartGame() {
        println("Game is Restarted! Yeah! Bitch!")
    }

}