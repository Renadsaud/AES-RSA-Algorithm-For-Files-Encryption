/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_is;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author razan
 */
public class Server {

    private ServerSocket serverSocket;
    private Socket socket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(5555);
    }

    public void startServer() throws IOException {

        socket = serverSocket.accept();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    while (true) {
                        socket = serverSocket.accept();
                        System.out.println("New Client Connected ..");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();

    }

    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.startServer();

        SendReciveController fileController = new SendReciveController();
        File file = fileController.reciveFile(server.socket);

        fileController.sendFile(file, server.socket);

    }

}