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

    public Messaggio(String comando, String dato) {
        this.comando = comando;
        this.dato = dato;
    }
    
    public Messaggio(String comando){
        this.comando = comando;
    }

    public static Messaggio FromCSV(String csv) {
        int index = 0;
        String com = "";
        String dat = "";
        if (csv.length() > 1) {
            index = csv.indexOf(";");
            com = csv.substring(0, index);
            dat = csv.substring(index + 1, csv.length());
            return new Messaggio(com, dat);
        } else {
            return new Messaggio(csv);
        }
    }
}