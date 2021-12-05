/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author marcello
 */
public class Condivisa {

    FrameP2P frame;
    boolean stato;
    String nickname;
    Socket client;

    PrintWriter out;
    BufferedReader in;

    public Condivisa(FrameP2P frame, String nickname) {
        this.frame = frame;
        stato = false;
        this.nickname = nickname;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public void setServer(Socket client) throws IOException {
        this.client = client;
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
    
    public Socket getClient(){
        return client;
    }
    
    public PrintWriter getOut(){
        return out;
    }
    
    public BufferedReader getIn(){
        return in;
    }
}
