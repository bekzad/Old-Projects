package com.bektursun.sokobanandroid

import android.content.DialogInterface
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_BOTTOM
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_LEFT
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_RIGHT
import com.bektursun.sokobanandroid.Properties.Companion.MOVE_TOP
import kotlin.math.abs


class Controller : View.OnTouchListener, GestureDetector.SimpleOnGestureListener, DialogInterface.OnClickListener {

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
            if (abs(diffX) > Properties.SWIPE_THRESHOLD && abs(velocityX) > Properties.SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight()
                } else {
                    onSwipeLeft()
                }
                result = true
            }

        } else if (abs(diffY) > Properties.SWIPE_THRESHOLD && abs(velocityY) > Properties.SWIPE_VELOCITY_THRESHOLD) {
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

    override fun onClick(dialogInterface: DialogInterface, p1: Int) {
        restartGame()
        dialogInterface.dismiss()
    }

    private fun restartGame() {
        println("Game is Restarted! Yeah! Bitch!")
    }

}