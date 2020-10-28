package com.bektursun.sokobanandroid

import java.io.BufferedOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket
import kotlin.concurrent.thread

class ConnectToServer {

    private var socket: Socket?

    constructor() {
        socket = null
    }

    private fun connectToServer(): Socket {
        socket = Socket("194.152.37.7", 4446)
        return socket!!
    }

    fun sendLevelToServer(level: String): Array<IntArray> {
        var map: Array<IntArray>? = null
        thread(start = true) {
            try {
                val socket = Socket("194.152.37.7", 4446)
                val objectOutputStream = ObjectOutputStream(socket.getOutputStream())
                objectOutputStream.writeUTF(level)
                objectOutputStream.flush()
                map = getArrayMapFromServer(socket)

                println(map)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return map!!
    }

    private fun getArrayMapFromServer(socket: Socket): Array<IntArray> {
        val objectInputStream = ObjectInputStream(socket.getInputStream())
        val map: Array<IntArray> = objectInputStream.readObject() as Array<IntArray>
        return map
    }


    fun send(text: String): Array<IntArray> {
        var desktop: Array<IntArray>? = null
        try {
            println("Start send.")
            val echoSocket = Socket("194.152.37.7", 4446)
            val outputStream =
                ObjectOutputStream(BufferedOutputStream(echoSocket.getOutputStream()))
            outputStream.writeUTF(text)
            outputStream.flush()
            println("End send.")
            val inputStream =
                ObjectInputStream(echoSocket.getInputStream())
            desktop = inputStream.readObject() as Array<IntArray>
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return desktop!!
    }

}


