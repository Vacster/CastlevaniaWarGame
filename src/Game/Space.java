/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Tablero.*;
import javax.swing.JLabel;

/**
 *
 * @author Kamil
 */
public class Space extends JLabel{   
    
    int x,y;
    
    @SuppressWarnings("LeakingThisInConstructor")//Netbeans plz
    Space(int x, int y){
        this.x = x;
        this.y = y;
        if(fichitas[x][y] == null){
        setBounds((int)(100.0*x)+3, (int)(100.0*y)+8, 94, 94);
        setIcon(Space);
        panel1.add(this,Frame.getComponentCount()-1);
        espacios.add(this);
        }
    }
}
