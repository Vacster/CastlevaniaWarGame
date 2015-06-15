/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javax.swing.ImageIcon;

/**
 *
 * @author Kamil
 */
public class Vampire extends Ficha{

    final static ImageIcon VampireP1 = new ImageIcon("src\\Game\\Visual\\VampireP1.png");
    final static ImageIcon VampireP2 = new ImageIcon("src\\Game\\Visual\\VampireP2.png");
    public Vampire(int columna, int fila, int jugador) {
        super(3, 4, 5, columna, fila, jugador);
        setIcon(jugador==1?VampireP1:VampireP2);
    }

}
