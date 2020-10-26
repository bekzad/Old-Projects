package com.example.sokobanandroid

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_BOTTOM
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_LEFT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_RIGHT
import com.example.sokobanandroid.SokobanProperties.Companion.MOVE_TOP
import kotlin.math.abs


class Controller : View.OnTouchListener, GestureDetector.SimpleOnGestureListener {

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
}