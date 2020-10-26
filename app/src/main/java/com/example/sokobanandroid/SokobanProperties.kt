package com.example.sokobanandroid

class SokobanProperties {

    companion object {
        const val SWIPE_THRESHOLD: Float = 100f
        const val SWIPE_VELOCITY_THRESHOLD: Float = 100f

        const val MOVE_RIGHT: String = "Right"
        const val MOVE_LEFT: String = "Left"
        const val MOVE_TOP: String = "Top"
        const val MOVE_BOTTOM: String = "Bottom"

        const val TARGET: Int = 1
        const val WALL: Int = 2
        const val BOX: Int = 3
        const val PLAYER: Int = 4
    }

}