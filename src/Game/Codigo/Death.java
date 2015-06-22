/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Codigo;

import static Game.Codigo.Tablero.espacios;
import static Game.Codigo.Tablero.fichitas;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Kamil
 */
public class Death extends Ficha{
    
    final static ImageIcon DeathP1 = new ImageIcon("src\\Game\\Visual\\DeathP1.png");
    final static ImageIcon DeathP2 = new ImageIcon("src\\Game\\Visual\\DeathP2.png");

    ArrayList<Zombie> Zombies = new ArrayList<>();
    
    
    public Death(int columna, int fila, int jugador) {
        super(4, 3, 1, columna, fila, jugador);
        setIcon(jugador==1?DeathP1:DeathP2);
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    @Override
    public void fillSpaces(){ //Llena espacios dentro del tablero
        for(int x = -1; x<2;x++){
            for(int y = -1; y<2; y++){//IM SO GOOD AT THIS
                try{
                if(x>0?columna<5:columna>=0 && y>0?fila<5:fila>=0 && fichitas[columna+x][fila+y] == null){
                    new Space(columna+x, fila+y);
                }
                }catch(ArrayIndexOutOfBoundsException e){}
            }
        }
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++){
                boolean t = false;
                for(Space s : espacios){
                    if(s.x == x && s.y == y)
                        t = true;
                }
                if(fichitas[x][y] == null && !t)
                    new ZombieSpace(x,y);
            }
        }
    }
    
}
