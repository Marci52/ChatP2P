/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chat;

import java.io.IOException;
import java.net.SocketException;

/**
 *
 * @author marcello
 */
public class Chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, IOException, InterruptedException {
        
        ThreadServer thread1 = new ThreadServer("Utente1");
        // CLIENT
        // Richiesta di connessione        
//        com.Invia("c;Utente2");
//        Messaggio m = com.Ricevi();
//        com.Invia("y");
//        System.out.println("Connessione stabilita con: " + m.dato);
        // Invio di un messaggio
//        while (true) {
//
//            // SERVER
//            Messaggio m = com.Ricevi();
//
//            if (m.comando.equals("c")) {  // Ricezione richiesta di connessione
//                nickname = m.dato;
//                com.Invia("y;" + myNickname);
//                m = com.Ricevi();
//                if (m.comando.equals("y")) {
//                    System.out.println("connessione stabilita con: " + nickname);
//                    connesso = true;
//                }
//            }
//            if (m.comando.equals("m") && connesso) { // Ricezione di un messaggio
//
//                System.out.println(nickname + ": " + m.dato);
//
//            }
//            if (m.comando.equals("e") && connesso) {
//                System.out.println("Connessione interrotta con: " + nickname);
//                nickname = null;
//                connesso = false;
//            }
//        }

        thread1.start();
        thread1.join();        
    }

}
