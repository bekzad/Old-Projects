package com.example.sokobanandroid

import android.view.GestureDetector
import android.view.MotionEvent
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
import com.example.sokobanandroid.SokobanProperties.Companion.SWIPE_THRESHOLD
import com.example.sokobanandroid.SokobanProperties.Companion.SWIPE_VELOCITY_THRESHOLD
import kotlin.math.abs

class SwipeListener : GestureDetector.SimpleOnGestureListener {

    private val controller: Controller

    constructor(controller: Controller) {
        this.controller = controller
    }

    override fun onDown(event: MotionEvent): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

        var result = false

        val diffY = e2.y - e1.y
        val diffX = e2.x - e1.x

        if (abs(diffX) > abs(diffY)) {
            if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
                result = true
            }

        } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
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
        //controller.move(MOVE_RIGHT)
    }

    private fun onSwipeLeft() {
        //controller.move(MOVE_LEFT)
    }

    private fun onSwipeBottom() {
        //controller.move(MOVE_BOTTOM)
    }

    private fun onSwipeTop() {
        //controller.move(MOVE_TOP)
    }
}