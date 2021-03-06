package com.bektursun.sokobanandroid

import android.content.Context
import java.util.*

class SokobanProperties {

    companion object {
        const val SWIPE_THRESHOLD: Float          = 100f
        const val SWIPE_VELOCITY_THRESHOLD: Float = 100f

        const val MOVE_RIGHT:  String = "Right"
        const val MOVE_LEFT:   String = "Left"
        const val MOVE_TOP:    String = "Top"
        const val MOVE_BOTTOM: String = "Bottom"

        const val SPACE:     Int = 0
        const val PLAYER:    Int = 1
        const val WALL:      Int = 2
        const val BOX:       Int = 3
        const val TARGET:    Int = 4
        const val BOX_ON_TARGET: Int = 5

        const val MAP_LEVEL_ONE:   String = "levelOne"
        const val MAP_LEVEL_TWO:   String = "levelTwo"
        const val MAP_LEVEL_THREE: String = "levelThree"

        const val MAP_LEVEL_FOUR: String = "Levels/levelFour"
        const val MAP_LEVEL_FIVE: String = "Levels/levelFive"
        const val MAP_LEVEL_SIX:  String = "Levels/levelSix"

        const val MAP_LEVEL_SEVEN: String = "7"
        const val MAP_LEVEL_EIGHT: String = "8"
        const val MAP_LEVEL_NINE:  String = "9"

        fun readProperty(propertyName: String, context: Context): String {
            val properties = Properties()
            val assetManager = context.assets
            val inputStream = assetManager.open("config.properties")
            properties.load(inputStream)

            val property = properties.getProperty(propertyName)
            inputStream.close()

            return property
        }
    }
}