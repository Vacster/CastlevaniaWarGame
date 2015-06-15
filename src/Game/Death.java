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
public class Death extends Ficha{
    final static ImageIcon DeathP1 = new ImageIcon("src\\Game\\Visual\\DeathP1.png");
    final static ImageIcon DeathP2 = new ImageIcon("src\\Game\\Visual\\DeathP2.png");
    public Death(int columna, int fila, int jugador) {
        super(4, 3, 1, columna, fila, jugador);
        setIcon(jugador==1?DeathP1:DeathP2);
    }
    
}
