/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Tablero.*;
import static Game.ZombieSpace.ZombieSpace;
import javax.swing.JLabel;
import java.awt.event.MouseEvent; //puntos extra por entender este relajo
import java.awt.event.MouseListener; 
import javax.swing.ImageIcon;

/**
 *
 * @author Kamil
 */
public class Space extends JLabel implements MouseListener{   
    
    int x,y;
    final static ImageIcon Space = new ImageIcon("src\\Game\\Visual\\Space.png");
    
    @SuppressWarnings("LeakingThisInConstructor")//Netbeans plz
    Space(int x, int y){
        this.x = x;
        this.y = y;
        if(fichitas[x][y] == null){
            setBounds((int)(100.0*x)+3, (int)(100.0*y)+8, 94, 94);
            setIcon(this instanceof ZombieSpace?ZombieSpace:Space);
            addMouseListener(this);
            panel1.add(this,Frame.getComponentCount()-1);
            espacios.add(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton() == 3 || this instanceof ZombieSpace){
            Zombie zombie = new Zombie(x,y, current);
            fichas.add(zombie);
            panel1.add(zombie);
        }else{
            currentficha.movimiento(x,y);
        }
        
        for(Space s : espacios){ //Quita los espacios del panel
            panel1.remove(s);
        }
        espacios.clear(); //Quita los espacios de memoria
                
        panel1.repaint();
        fichaActiva = false;
        
        if(current==1)//No servia el operador ternario por algun motivo
            current=2;//Escrito como current==1?current=2:current=1;
        else
            current=1;
        
        fichaActiva = false;
        Spin.setVisible(true);
        currentficha.turnPass();
        currentficha.updateHighlights();
        currentficha = null;
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
