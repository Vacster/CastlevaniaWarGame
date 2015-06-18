/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Death.Zombies;
import static Game.Tablero.*; //#yolo
import java.awt.event.MouseEvent; //puntos extra por entender este relajo
import java.awt.event.MouseListener; //este tambien
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Kamil I, Jonathan H, Juan E
 */
public abstract class Ficha extends JLabel implements MouseListener{
    
    protected int vida, escudo, ataque, columna, fila, jugador;
    ImageIcon highlight = new ImageIcon(getClass().getResource("/Game/Visual/Highlight.png"));
   
    @SuppressWarnings("LeakingThisInConstructor") //Que no chingue netbeans #yolo
    public Ficha(int a, int v, int e, int columna, int fila, int jugador){
        ataque = a;
        vida = v;
        escudo = e;
        this.columna = columna;
        this.fila = fila;
        this.jugador = jugador;
        setBounds((int)(100.0*columna)+10, (int) (100.0*fila)+9, 85, 88);
        fichitas[this.columna][this.fila] = this;//Se que es peligroso usar this
        if(!(this instanceof Zombie))
            fichas.add(this); //en el constructor pero lo mas importante son los 
        
        addMouseListener(this); //valores definidos antes de usar los "this"
    }

    
    void movimiento(int columna, int fila){ //Cambia la ficha en el array
        if(fichitas[columna][fila] == null){ //Y las dibuja donde van
            fichitas[this.columna][this.fila] = null;
            this.columna = columna;
            this.fila = fila;
            fichitas[columna][fila] = this;
            setBounds((int)(100.0*this.columna)+10, (int)(100.0*this.fila)+8, 85,88);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if(spinning == false){
            if(current == this.jugador){
                if(!fichaActiva && ((this instanceof Vampire && "vampire".equals(pieza)) || 
                        (this instanceof Death && "death".equals(pieza)) || 
                        (this instanceof Zombie && "death".equals(pieza)) ||
                        (this instanceof Werewolf && "werewolf".equals(pieza)))){
                    fillSpaces();
                    currentficha = this;
                    fichaActiva=true;
                }else if(fichaActiva){
                    for(Space s : espacios){ //Quita los espacios del panel
                        panel1.remove(s);
                    }
                    espacios.clear(); //Quita los espacios de memoria

                    panel1.repaint();
                    currentficha = null;
                    fichaActiva = false;
                }
            }else if(fichaActiva){
                if(currentficha != this){
                    if(currentficha instanceof Vampire && e.getButton() == MouseEvent.BUTTON3){
                        currentficha.ataqueVampiro(this, e);
                    }else{
                        currentficha.ataque(this);
                    }
                }
                for(Space s : espacios){ //Quita los espacios del panel
                        panel1.remove(s);
                    }
                espacios.clear(); //Quita los espacios de memoria

                panel1.repaint();
                currentficha = null;
                fichaActiva = false;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { //Esto estaba asqueroso antes
    }

    @Override
    public void mouseEntered(MouseEvent e) {//TODO mostrar valores de la ficha
        attack.setText("Ataque: " + ataque);
        hp.setText("Vida: " + vida);
        shield.setText("Escudo: " + escudo);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        attack.setText("");
        hp.setText("");
        shield.setText("");
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored") //Que no chinge netbeans
    private void fillSpaces(){ //Llena espacios dentro del tablero
        if(!(this instanceof Zombie)){
        for(int x = -1; x<2;x++){
            for(int y = -1; y<2; y++){//IM SO GOOD AT THIS
                try{
                if(x>0?columna<5:columna>=0 && y>0?fila<5:fila>=0 && fichitas[columna+x][fila+y] == null){
                    new Space(columna+x, fila+y);
                }
                }catch(ArrayIndexOutOfBoundsException e){}
            }
        }}
        if(this instanceof Werewolf){ //Me cago en programacion / Temporal
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
        }else if(this instanceof Death){
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
 
    private void ataqueVampiro(Ficha ficha, MouseEvent e){
        if(jugador != ficha.jugador && (columna-ficha.columna<0?columna-ficha.columna>-2:columna-ficha.columna<2)
                && (fila-ficha.fila<0?fila-ficha.fila>-2:fila-ficha.fila<2)){
            ficha.vida -= 1;
            currentficha.vida += 1;
            if(ficha.vida <= 0){
                kill(ficha);
            } 
            updateHighlights();
            turnPass();
            attack.setText("Ataque: " + ficha.ataque);
            hp.setText("Vida: " + ficha.vida);
            shield.setText("Escudo: " + ficha.escudo);
            if(current==1)//No servia el operador ternario por algun motivo
                current=2;//Escrito como current==1?current=2:current=1;
            else
                current=1;
        }
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void ataque(Ficha ficha){ 
        boolean success = false;
        if(jugador != ficha.jugador && (columna-ficha.columna<0?columna-ficha.columna>-2:columna-ficha.columna<2)
                && (fila-ficha.fila<0?fila-ficha.fila>-2:fila-ficha.fila<2)){
            if(ficha.escudo >= ataque){ 
                ficha.escudo -= ataque;
            }else{
                ficha.vida -= (ataque-ficha.escudo);
                ficha.escudo = 0;
            }
           success = true;
        }
        else if(jugador != ficha.jugador && (columna-ficha.columna<0?columna-ficha.columna>-3:columna-ficha.columna<3)
                && (fila-ficha.fila<0?fila-ficha.fila>-3:fila-ficha.fila<3)){
            ficha.vida-=2;
            success = true;
        }
        
        if(success){
            if(ficha.vida <= 0){
                kill(ficha);
            } 
            updateHighlights();
            turnPass();
            attack.setText("Ataque: " + ficha.ataque);
            hp.setText("Vida: " + ficha.vida);
            shield.setText("Escudo: " + ficha.escudo);
            if(current==1)//No servia el operador ternario por algun motivo
                current=2;//Escrito como current==1?current=2:current=1;
            else
                current=1;
        }
    }
    
    @SuppressWarnings("element-type-mismatch")
    private void kill(Ficha f){
        fichitas[f.columna][f.fila] = null;
        f.setIcon(null);
        if(this instanceof Zombie)
            Zombies.remove(f);
        else
            fichas.remove(f);
        
        panel1.remove(f);
        panel1.repaint();
        attack.setText("");
        hp.setText("");
        shield.setText("");
    }
    
    void updateHighlights(){
        for(JLabel j : highlights){
            panel1.remove(j);
        }
        highlights.clear();
        panel1.repaint();
        if(spinning == false){
            for(Ficha f : fichas){
                if(!fichaActiva && f.jugador == current && ((f instanceof Vampire && "vampire".equals(pieza)) || 
                (f instanceof Death && "death".equals(pieza)) ||
                (f instanceof Werewolf && "werewolf".equals(pieza)))){
                    JLabel l = new JLabel(highlight);
                    l.setBounds((int)(f.columna*100)+3, (int)(100.0*f.fila)+6, 94,98);
                    highlights.add(l);
                    panel1.add(l, panel1.getComponentCount());
                }
            }
        }
    }
     
    void turnPass(){
        Roullete.setIcon(new javax.swing.ImageIcon(getClass()
                    .getResource("/Game/Visual/MiniRoullete2.gif")));
        Spin.setText("Stop");
        Spin.setVisible(true);
        spinning = true;
    }
}
