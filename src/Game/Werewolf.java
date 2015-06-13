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
public class Werewolf extends Ficha{
    final static ImageIcon WerewolfP1 = new ImageIcon("src\\Game\\Visual\\WerewolfP1.png");
    final static ImageIcon WerewolfP2 = new ImageIcon("src\\Game\\Visual\\WerewolfP2.png");
    

    public Werewolf(int x, int y, int jugador) {
        super(5, 5, 2, x, y, jugador);
        setIcon(jugador==1?WerewolfP1:WerewolfP2);
    }
}
