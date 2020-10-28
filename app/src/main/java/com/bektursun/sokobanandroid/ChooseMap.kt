package com.bektursun.sokobanandroid

import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_EIGHT
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FIVE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_FOUR
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_NINE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_ONE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_SEVEN
import com.bektursun.sokobanandroid.SokobanProperties.Companion.MAP_LEVEL_SIX
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_THREE
import com.bektursun.sokobanandroid.SokobanProperties.Companion.LEVEL_TWO

class ChooseMap {

    private val viewer: Viewer

    constructor(viewer: Viewer) {
        this.viewer = viewer
    }

    fun chooseMap(level: String): Array<IntArray> {
        println("CHOOSEMAP: $level")
        when (level) {

            LEVEL_ONE -> return getFirstLevel()
            LEVEL_TWO -> return getSecondLevel()
            LEVEL_THREE -> return getThirdLevel()

            MAP_LEVEL_FOUR -> return ReadLevelsFromFile().readLevelFromFile(MAP_LEVEL_FOUR, viewer)
            MAP_LEVEL_FIVE -> return ReadLevelsFromFile().readLevelFromFile(MAP_LEVEL_FIVE, viewer)
            MAP_LEVEL_SIX  -> return ReadLevelsFromFile().readLevelFromFile(MAP_LEVEL_SIX, viewer)

            LEVEL_SEVEN -> {
                val connectToServer = Connect()
                return connectToServer.send(LEVEL_SEVEN)
            }
            LEVEL_EIGHT -> {
                val connectToServer = Connect()
                return connectToServer.send(LEVEL_EIGHT)
            }
            else -> {
                val connectToServer = Connect()
                return connectToServer.send(LEVEL_NINE)
            }
        }
    }

    private fun getFirstLevel(): Array<IntArray> {
        return arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 4, 0, 0, 0, 2, 0, 0, 0, 2),
            intArrayOf(2, 0, 2, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 0, 2, 0, 0, 3, 0, 0, 2, 2),
            intArrayOf(2, 0, 2, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 2, 0, 0, 1, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
        )
    }

    private fun getSecondLevel(): Array<IntArray> {
        return arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 4, 0, 0, 0, 2, 0, 0, 2, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 2, 2, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 2, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 2, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 2, 0, 0, 1, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
        )
    }

    private fun getThirdLevel(): Array<IntArray> {
        return arrayOf(
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
            intArrayOf(2, 4, 0, 0, 0, 2, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 4, 0, 0, 0, 0, 2, 0, 0, 2),
            intArrayOf(2, 0, 0, 2, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 0, 0, 2, 0, 3, 0, 0, 0, 2),
            intArrayOf(2, 4, 0, 2, 0, 2, 0, 0, 1, 2),
            intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
        )
    }

}