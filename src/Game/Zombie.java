/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Death.Zombies;
import javax.swing.ImageIcon;

/**
 *
 * @author Kamil
 */
public class Zombie extends Ficha{
    final static ImageIcon ZombieP1 = new ImageIcon("src\\Game\\Visual\\ZombieP1.png");
    final static ImageIcon ZombieP2 = new ImageIcon("src\\Game\\Visual\\ZombieP2.png");
    
    public Zombie(int columna, int fila, int jugador) {
        super(1, 1, 0, columna, fila, jugador);
        setIcon(jugador==1?ZombieP1:ZombieP2);
        Zombies.add(this);
    }
}
