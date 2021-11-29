/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marcello
 */
public class ThreadServer extends Thread {

    Comunicazione com;
    String myNickname;
    Condivisa c;
    Object options[] = {"Si", "No"};
    String nicknamePeer;

    public ThreadServer(String myNickname, Condivisa c) throws SocketException {
        com = new Comunicazione();
        this.myNickname = myNickname;
        this.c = c;
    }

    public Comunicazione getCom() {
        return com;
    }

    @Override
    public void run() {
        Messaggio m = new Messaggio();

        while (true) {
            try {
                m = com.Ricevi();
            } catch (IOException ex) {
                Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (m.comando) {
                case "c":
                    nicknamePeer = m.dato;
                    int scelta = JOptionPane.showOptionDialog(c.frame, m.dato + " desidera instaurare una connessione, accettare?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (scelta == 0) {
                        m = new Messaggio("y", myNickname);
                        try {
                            com.Invia(m, "n");
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        c.setStato(true);
                        JOptionPane.showMessageDialog(c.frame, "Connessione avvenuta correttamente");
                        c.frame.setLabel1("Scrivere il messaggio da inviare: ");
                    } else {
                        m = new Messaggio("n");
                        try {
                            com.Invia(m, "n");
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(c.frame, "Connessione rifiutata");
                    }

                    break;
                case "m":
                    if (!c.stato) {
                        JOptionPane.showMessageDialog(c.frame, "Errore: dispositivo non connesso", "Errore", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(c.frame, nicknamePeer + ": " + m.dato);
                    }
                    break;
                case "e":
                    if (c.stato) {
                        c.setStato(false);
                        c.frame.setLabel1("Inserisci l'indirizzo del peer con cui vuoi comunicare: ");
                        JOptionPane.showMessageDialog(c.frame, "Disconnessione avvenuta correttamente");
                    }
                    break;
                case "y":
                    c.setStato(true);
                    JOptionPane.showMessageDialog(c.frame, "Connessione avvenuta correttamente");
                    c.frame.setLabel1("Scrivere il messaggio da inviare: ");
                    break;
                case "n":
                    c.setStato(false);
                    JOptionPane.showMessageDialog(c.frame, "Connessione rifiutata");
                    c.frame.setLabel1("Inserisci l'indirizzo del peer con cui vuoi comunicare: ");
            }
        }
    }
}
