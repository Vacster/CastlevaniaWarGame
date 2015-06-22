/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Codigo;

import static Game.Codigo.Tablero.*;
import static Game.Codigo.ZombieSpace.ZombieSpace;
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
            panel.add(this,mainFrame.getComponentCount()-1);
            espacios.add(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if((me.getButton() == 3 && currentficha instanceof Death)|| this instanceof ZombieSpace){
            Zombie zombie = new Zombie(x,y, current);
            fichas.add(zombie);
            panel.add(zombie, panel.getComponentCount()-1);
        }else{
            currentficha.movimiento(x,y);
        }
        
        for(Space s : espacios){ //Quita los espacios del panel
            panel.remove(s);
        }
        espacios.clear(); //Quita los espacios de memoria
                
        panel.repaint();
        fichaActiva = false;
        
        fichaActiva = false;
        spin.setVisible(true);
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
