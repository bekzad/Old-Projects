package com.example.sokobanandroid

class SokobanProperties {

    companion object {
        const val SWIPE_THRESHOLD: Float = 100f
        const val SWIPE_VELOCITY_THRESHOLD: Float = 100f

        const val MOVE_RIGHT: String = "Right"
        const val MOVE_LEFT: String = "Left"
        const val MOVE_TOP: String = "Top"
        const val MOVE_BOTTOM: String = "Bottom"

        const val EMPTY_SPACE_IN_GAME_MAP: Int = 0
        const val PLAYER: Int = 1
        const val WALL_IN_GAME_MAP: Int = 2
        const val BOX_IN_GAME_MAP: Int = 3
        const val TARGET_IN_GAME_MAP: Int = 4
    }

}