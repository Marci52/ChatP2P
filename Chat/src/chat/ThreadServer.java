/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcello
 */
public class ThreadServer extends Thread {

    Condivisa c;
    ServerSocket serverSocket;
    Socket clientSocket;
    ThreadComunicazione client;

    public ThreadServer(Condivisa c) throws SocketException, IOException {
        this.c = c;
        serverSocket = new ServerSocket(2003);
    }

    @Override
    public void run() {
        try {
            clientSocket = serverSocket.accept();
            c.setServer(clientSocket);
            client = new ThreadComunicazione(clientSocket, c);
            client.start();

        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
