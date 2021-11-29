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

    public Messaggio(String comando) {
        this.comando = comando;
    }

    public Messaggio(String comando, String dato) {
        this.comando = comando;
        this.dato = dato;
    }

    public static Messaggio FromCSV(String csv) {
//        String[] campi = csv.split(";");
//        switch (campi.length) {
//            case 1:
//                return new Messaggio(campi[0]);
//            case 2:
//                return new Messaggio(campi[0], campi[1]);
//            default:
//                return null;
//        }

        int i = csv.indexOf(";");
        String comando;
        String dato;
        if (i == -1) {
            return new Messaggio(csv);
        } else {
            comando = csv.substring(0, i);
            dato = csv.substring(i+1, csv.length());
            return new Messaggio(comando, dato);
        }
    }

    public String toCSV() {
        if ("".equals(dato)) {
            return comando;
        } else if (!"".equals(comando) && !"".equals(dato)) {
            return comando + ";" + dato;
        } else {
            return "Ciao";
        }
    }

    @Override
    public String toString() {
        return this.comando + this.dato + "\n";
    }
}
