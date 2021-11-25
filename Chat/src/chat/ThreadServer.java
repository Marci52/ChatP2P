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
    String clientNickname;
    Scanner scan = new Scanner(System.in);
    String messaggio = "";

    public ThreadServer(String myNickname) throws SocketException {
        com = new Comunicazione();
        connessa = false;
        this.myNickname = myNickname;
        clientNickname = "";
        messaggio = "";
    }

    @Override
    public void run() {
        System.out.print("[" + myNickname + "]: Inserire un comando(c=connessione, m=messaggio, e=esci): ");
        String comando = scan.nextLine();
        if (comando.equals("c")) {
            System.out.print("[" + myNickname + "]: Inserire il proprio nickname: ");
            clientNickname = scan.nextLine();
            try {
                com.Invia(comando + ";" + clientNickname);
            } catch (IOException ex) {
                Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (comando.equals("m")) {
            if (!connessa) {
                System.out.println("[" + myNickname + "]: Errore: peer non connesso");
            } else {
                System.out.print("[" + myNickname + "]: Inserire messaggio da inviare: ");
                messaggio = scan.nextLine();
                try {
                    com.Invia(messaggio);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}
