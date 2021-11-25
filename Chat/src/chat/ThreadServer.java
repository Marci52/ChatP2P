/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcello
 */
public class ThreadServer extends Thread {

    Comunicazione com;
    boolean connessa;
    String myNickname;
    Condivisa c;
    String ip;

    public ThreadServer(String myNickname, Condivisa c) throws SocketException {
        com = new Comunicazione();
        connessa = false;
        this.myNickname = myNickname;
        this.c = c;
    }

    @Override
    public void run() {
        c.frame.setLabel2("prova");
        Messaggio m = new Messaggio();

        try {
            m = com.Ricevi();
            ip = m.indirizzoIP;
        } catch (IOException ex) {
            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (m.comando.equals("c")) {
            try {
                com.Invia("y;" + myNickname, ip);
            } catch (IOException ex) {
                Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            connessa = true;
        }
        if (m.comando.equals("m")) {
            if (!connessa) {
                c.frame.setLabel2("Err: peer non connesso");
            } else {
                c.frame.setLabel2(m.dato);
            }
        }

        if (m.comando.equals("e")) {
            connessa = false;
            ip = null;
        }

    }
}
