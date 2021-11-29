/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author marcello
 */
public class Comunicazione {
    
    DatagramSocket server;
    
    InetAddress lastAddress = null;
    int lastPort = 0;
    
    public Comunicazione() throws SocketException {
        server = new DatagramSocket(2003);
    }
    
    public Messaggio Ricevi() throws IOException {
        byte[] buffer = new byte[1500];
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        server.receive(p);
        
        lastAddress = p.getAddress();
        lastPort = p.getPort();
        String ricevuto = new String(p.getData(), 0, p.getLength());
        return Messaggio.FromCSV(ricevuto);
    }
    
    public void Invia(Messaggio m, String ip) throws IOException {
        
        byte[] buffer = m.toCSV().getBytes();
        DatagramPacket p = new DatagramPacket(buffer, buffer.length);
        if ("n".equals(ip)) {
            p.setAddress(InetAddress.getByName(ip));
        } else {
            p.setAddress(lastAddress);
        }
        p.setPort(2003);
        
        server.send(p);
    }
}
