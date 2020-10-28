package com.bektursun.sokobanandroid;

import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Connect extends Thread {

    public int[][] send(String text) {
        int[][] desktop = new int[0][];
        try {
            System.out.println("Start send.");
            Socket echoSocket = new Socket("194.152.37.7", 4446);

            ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(echoSocket.getOutputStream()));
            outputStream.writeUTF(text);
            outputStream.flush();
            System.out.println("End send.");

            ObjectInputStream inputStream = new ObjectInputStream(echoSocket.getInputStream());
            desktop = (int[][]) inputStream.readObject();
            System.out.println("from Server: " + Arrays.deepToString(desktop));

            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return desktop;
    }


}
