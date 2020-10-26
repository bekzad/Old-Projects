package com.example.sokobanandroid

fun main() {
    val desktop = arrayOf(
        intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
        intArrayOf(2, 3, 0, 0, 0, 0, 0, 0, 0, 2),
        intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
        intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
        intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
        intArrayOf(2, 0, 0, 0, 0, 3, 0, 0, 0, 2),
        intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
        intArrayOf(2, 0, 0, 0, 0, 0, 0, 4, 4, 2),
        intArrayOf(2, 0, 0, 0, 0, 0, 0, 0, 0, 2),
        intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2),
        intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
    )

    println("size: ${desktop[0].size}")
    println("size: ${desktop[1].size}")
    println("size: ${desktop[2].size}")
    println("size: ${desktop.size}")
}