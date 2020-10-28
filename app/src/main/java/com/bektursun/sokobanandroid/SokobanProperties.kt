package com.bektursun.sokobanandroid

class SokobanProperties {

    companion object {
        const val SWIPE_THRESHOLD: Float          = 100f
        const val SWIPE_VELOCITY_THRESHOLD: Float = 100f

        const val MOVE_RIGHT: String  = "Right"
        const val MOVE_LEFT: String   = "Left"
        const val MOVE_TOP: String    = "Top"
        const val MOVE_BOTTOM: String = "Bottom"

        const val SPACE_IN_GAME_MAP: Int  = 0
        const val PLAYER: Int             = 1
        const val WALL_IN_GAME_MAP: Int   = 2
        const val BOX_IN_GAME_MAP: Int    = 3
        const val TARGET_IN_GAME_MAP: Int = 4
        const val ON_TARGET_GAME_MAP: Int = 5

        const val LEVEL_ONE: String   = "levelOne"
        const val LEVEL_TWO: String   = "levelTwo"
        const val LEVEL_THREE: String = "levelThree"

        const val MAP_LEVEL_FOUR: String = "levelFour"
        const val MAP_LEVEL_FIVE: String = "levelFive"
        const val MAP_LEVEL_SIX: String  = "levelSix"

        const val LEVEL_SEVEN: String  = "levelSeven"
        const val LEVEL_EIGHT: String  = "levelEight"
        const val LEVEL_NINE:  String  = "levelNine"

    }

}