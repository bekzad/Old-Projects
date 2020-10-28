package com.bektursun.sokobanandroid

import android.content.Context
import com.bektursun.sokobanandroid.Properties.Companion.LEVEL_ONE
import com.bektursun.sokobanandroid.Properties.Companion.LEVEL_THREE
import com.bektursun.sokobanandroid.Properties.Companion.LEVEL_TWO
import java.util.*


class ReadLevelsFromFile {

    private var mapSize: Int = 0
    private var mapRow: Int = 0

    fun readLevelFromFile(levelName: String, context: Context): Array<IntArray> {
        return when (levelName) {
            LEVEL_ONE -> getLevelFile(LEVEL_ONE, context)
            LEVEL_TWO -> getLevelFile(LEVEL_TWO, context)
            else -> getLevelFile(LEVEL_THREE, context)
        }
    }

    private fun getLevelFile(levelName: String, context: Context): Array<IntArray> {
        return when (levelName) {
            LEVEL_ONE -> {
                readFile(LEVEL_ONE, context)
            }
            LEVEL_TWO -> {
                readFile(LEVEL_TWO, context)
            }
            else -> {
                readFile(LEVEL_THREE, context)
            }
        }
    }

    private fun readFile(levelName: String, context: Context): Array<IntArray> {
        getArrayMapSize(levelName, context)
        val mapArray =
            Array(mapSize) { IntArray(mapRow) }

        val fileLevel = context.assets.open(levelName)

        val scanner = Scanner(fileLevel)

        while (scanner.hasNextLine()) {
            for (element in mapArray) {
                val line: List<String> = scanner.nextLine()!!.trim().split(" ")
                for (j in line.indices) {
                    element[j] = line[j].toInt()
                }
            }
        }

        return mapArray
    }

    private fun getArrayMapSize(fileName: String, context: Context) {
        val file = context.assets.open(fileName)

        val scanner = Scanner(file)
        var stringBuilder = ""

        var row = scanner.nextLine()
        row = row.replace(" ", "")
        val arrayRow = row.length

        while (scanner.hasNextLine()) {
            stringBuilder += (scanner.nextLine() + "\n")
        }
        stringBuilder = stringBuilder.replace(" ", "")

        mapSize = countLines(stringBuilder)
        mapRow = arrayRow
    }

    private fun countLines(str: String): Int {
        val lines = str.split("[\n\r]".toRegex()).toTypedArray()
        return lines.size
    }
}
