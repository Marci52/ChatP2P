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
        this.indirizzoIP = indirizzoIP;
    }

    public Messaggio(String comando, String dato) {
        this.comando = comando;
        this.dato = dato;
    }

    public Messaggio(String comando) {
        this.comando = comando;
    }

    public static Messaggio FromCSV(String csv) {
        int i = 0;
        int j = 0;
        String com = "";
        String dat = "";
        String ip = "";
        if (csv.length() > 1) {
            i = csv.indexOf(";");
            com = csv.substring(0, i);
            j = com.indexOf(";");
            if (j != -1) {
                dat = csv.substring(i + 1, j);
                ip = csv.substring(j + 1, csv.length());
                return new Messaggio(com, dat, ip);
            } else {
                dat = csv.substring(i + 1, csv.length());
                return new Messaggio(com, dat);
            }
        } else {
            return new Messaggio(csv);
        }
    }

    @Override
    public String toString() {
        return this.comando + this.dato + this.indirizzoIP + "\n";
    }
}
