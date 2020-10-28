package com.bektursun.sokobanandroid

class Properties {

    companion object {
        const val SWIPE_THRESHOLD: Float = 100f
        const val SWIPE_VELOCITY_THRESHOLD: Float = 100f

        const val MOVE_RIGHT: String = "Right"
        const val MOVE_LEFT: String = "Left"
        const val MOVE_TOP: String = "Top"
        const val MOVE_BOTTOM: String = "Bottom"

        const val FLOOR: Int = 0
        const val PLAYER: Int = 1
        const val WALL: Int = 2
        const val BOX: Int = 3
        const val TARGET: Int = 4
        const val ONTARGET: Int = 5

        const val LEVEL_ONE: String = "levelOne"
        const val LEVEL_TWO: String = "levelTwo"
        const val LEVEL_THREE: String = "levelThree"
    }

}