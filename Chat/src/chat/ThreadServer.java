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
import javax.swing.JOptionPane;

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
    Object options[] = {"Si", "No"};

    public ThreadServer(String myNickname, Condivisa c) throws SocketException {
        com = new Comunicazione();
        connessa = false;
        this.myNickname = myNickname;
        this.c = c;
    }

    @Override
    public void run() {
        Messaggio m = new Messaggio();

        while (true) {
            try {
                m = com.Ricevi();
                ip = m.indirizzoIP;
            } catch (IOException ex) {
                Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (m.comando.equals("c")) {

                int scelta = JOptionPane.showOptionDialog(c.frame, m.dato + " desidera instaurare una connessione, accettare?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (scelta == 0) {
                    try {
                        com.Invia("y;" + myNickname, ip);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    connessa = true;
                    JOptionPane.showMessageDialog(c.frame, "Connessione avvenuta correttamente");
                    c.frame.setLabel1("Scrivere il messaggio da inviare: ");
                } else {
                    JOptionPane.showMessageDialog(c.frame, "Connessione rifiutata");
                }

            } else if (m.comando.equals("m")) {
                if (!connessa) {
                    JOptionPane.showMessageDialog(c.frame, "Errore: dispositivo non connesso", "Error Message Box", JOptionPane.ERROR_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(c.frame, m.dato);
                }
            } else if (m.comando.equals("e")) {
                if (connessa) {
                    connessa = false;
                    ip = null;
                    JOptionPane.showMessageDialog(c.frame, "Disconnessione avvenuta correttamente");
                } else {
                    JOptionPane.showMessageDialog(c.frame, "Errore: dispositivo non connesso", "Error Message Box", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
