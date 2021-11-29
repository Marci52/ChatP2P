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
    String indirizzoIP;

    public Messaggio() {
    }

    public Messaggio(String comando, String dato, String indirizzoIP) {
        this.comando = comando;
        this.dato = dato;
//        if (indirizzoIP != "") {
//            this.indirizzoIP = indirizzoIP;
//        }else
//        {
//            this.indirizzoIP = "ciao";
//        }
            this.indirizzoIP = indirizzoIP;

    }

    public Messaggio(String comando, String dato) {
        this.comando = comando;
        this.dato = dato;
        this.indirizzoIP = null;
    }

    public Messaggio(String comando) {
        this.comando = comando;
        this.dato = null;
        this.indirizzoIP = null;
    }

    public static Messaggio FromCSV(String csv) {
        String[] campi = csv.split(";");
        if ("".equals(campi[1]) && "".equals(campi[2])) {
            return new Messaggio(campi[0]);
        } else if ("".equals(campi[2])) {
            return new Messaggio(campi[0], campi[1]);
        } else {
            return new Messaggio(campi[0], campi[1], campi[2]);
        }
    }

    public String toCSV() {
        if ("".equals(dato) && "".equals(indirizzoIP)) {
            return comando;
        }  else if ("".equals(indirizzoIP)) {
            return this.comando + ";" + this.dato;
        }  else if (!"".equals(comando) && !"".equals(dato) && !"".equals(indirizzoIP)) {
            return this.comando + ";" + this.dato + ";" + this.indirizzoIP;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return this.comando + this.dato + this.indirizzoIP + "\n";
    }
}
