/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.util.ArrayList;

/**
 *
 * @author marcello
 */
public class Condivisa {
    FrameP2P frame;
    ArrayList<Messaggio> coda;

    public Condivisa(FrameP2P frame) {
        this.frame = frame;
        coda = new ArrayList<>();
    }
}
