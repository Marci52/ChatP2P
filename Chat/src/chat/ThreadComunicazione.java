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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marcello
 */
public class ThreadComunicazione extends Thread {

    PrintWriter out;
    BufferedReader in;
    Socket clientSocket;
    String inputLine;
    Condivisa c;
    String nicknamePeer;
    Object options[] = {"Si", "No"};
    char comando;
    String messaggio;

    public ThreadComunicazione() {
    }

    public ThreadComunicazione(Socket clientSocket, Condivisa c) throws IOException {
        this.clientSocket = clientSocket;
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.c = c;
    }

    @Override
    public void run() {

        try {
            while (!"".equals(inputLine = in.readLine())) {
                comando = inputLine.charAt(0);
                messaggio = inputLine.substring(1, inputLine.length());
                switch (comando) {
                    case 'c':
                        if (!c.stato) {
                            nicknamePeer = messaggio;
                            int scelta = JOptionPane.showOptionDialog(c.frame, messaggio + " desidera instaurare una connessione, accettare?", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                            if (scelta == 0) {
                                out.println("y" + messaggio);
                                c.setStato(true);
                                JOptionPane.showMessageDialog(c.frame, "Connessione avvenuta correttamente");
                                c.frame.setLabel1("Inserire il messaggio da inviare: ");
                            } else {
                                out.println("n");
                                JOptionPane.showMessageDialog(c.frame, "Connessione rifiutata");
                            }
                        }
                        break;
                    case 'm':
                        if (!c.stato) {
                            JOptionPane.showMessageDialog(c.frame, "Errore: dispositivo non connesso", "Errore", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(c.frame, nicknamePeer + ": " + messaggio);
                        }
                        break;
                    case 'e':
                        c.setStato(false);
                        JOptionPane.showMessageDialog(c.frame, "Disconnessione avvenuta correttamente");
                        c.frame.setLabel1("Inserisci l'indirizzo del peer con cui vuoi comunicare: ");
                        break;
                    case 'y':
                        c.setStato(true);
                        JOptionPane.showMessageDialog(c.frame, "Connessione avvenuta correttamente");
                        c.frame.setLabel1("Scrivere il messaggio da inviare: ");
                        break;
                    case 'n':
                        c.setStato(false);
                        JOptionPane.showMessageDialog(c.frame, "Connessione rifiutata");
                        c.frame.setLabel1("Inserisci l'indirizzo del peer con cui vuoi comunicare: ");
                        break;
                    default:
                        break;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ThreadComunicazione.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
