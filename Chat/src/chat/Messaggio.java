/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

/**
 *
 * @author marcello
 */
public class Messaggio {

    String comando;
    String dato;

    public Messaggio() {
    }

    public Messaggio(String comando, String dato) {
        this.comando = comando;
        this.dato = dato;
    }

    public Messaggio(String comando) {
        this.comando = comando;
    }

    public static Messaggio FromCSV(String csv) {
        String[] campi = csv.split(";");
        switch (campi.length) {
            case 1:
                return new Messaggio(campi[0]);
            case 2:
                return new Messaggio(campi[0], campi[1]);
            default:
                return null;
        }
    }

    public String toCSV() {
        if ("".equals(dato)) {
            return comando;
        } else {
            return comando+";"+dato;
        }
    }

    @Override
    public String toString() {
        return this.comando + this.dato + "\n";
    }
    public String getComando(){
        return comando;
    }
}
