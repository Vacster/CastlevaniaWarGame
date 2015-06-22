/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Codigo;

import static Game.Codigo.Tablero.fichitas;
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
            if(columna < 4 && fichitas[columna+1][fila] == null
                    && fichitas[columna+2][fila] == null)
                new Space(columna+2, fila);
            if(columna < 4 && fila < 4 && fichitas[columna+1][fila+1] == null
                    && fichitas[columna+2][fila+2] == null)
                new Space(columna+2,fila+2);
            if(fila < 4 && fichitas[columna][fila+1] == null 
                    && fichitas[columna][fila+2] == null)
                new Space(columna, fila+2);
            if(columna > 1 && fichitas[columna-1][fila] == null 
                    && fichitas[columna-2][fila] == null)
                new Space(columna-2, fila);
            if(fila > 1 && columna > 1 && fichitas[columna-1][fila-1] == null
                    && fichitas[columna-2][fila-2] == null)
                new Space(columna-2, fila-2);
            if(fila > 1 && fichitas[columna][fila-1] == null
                    && fichitas[columna][fila-2] == null)
                new Space(columna, fila-2);
            if(fila > 1 && columna < 4 && fichitas[columna+1][fila-1] == null
                    && fichitas[columna+2][fila-2] == null)
                new Space(columna+2, fila-2);
            if(fila < 4 && columna > 1 && fichitas[columna-1][fila+1] == null
                    && fichitas[columna-2][fila+2] == null)
                new Space(columna-2, fila+2);
        }
}
