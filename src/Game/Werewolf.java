/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Tablero.*;

/**
 *
 * @author Kamil
 */
public class Werewolf extends Ficha{
    
    /**
     * @param a
     * @param x columna
     * @param e
     * @param v
     * @param y fila
     * @param jugador
     */
    public Werewolf(int x, int y, int jugador) {
        super(5, 5, 2, x, y, jugador);
    }
}
