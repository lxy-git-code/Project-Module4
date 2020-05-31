package com.cas;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LiXingyu
 * @date 2020-05-19 9:10 上午
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9080);
            Socket socket = serverSocket.accept();
            byte[] readline=new byte[20];
            while(true){
                InputStream inputStream = socket.getInputStream();
                inputStream.read(readline);
                System.out.println(new String(readline));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
