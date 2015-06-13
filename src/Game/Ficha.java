/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Tablero.*; //#yolo
import java.awt.event.MouseEvent; //puntos extra por entender este relajo
import java.awt.event.MouseListener; //este tambien
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Kamil I
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
        fichas.add(this); //en el constructor pero lo mas importante son los 
        addMouseListener(this); //valores definidos antes de usar los "this"
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void ataque(Ficha ficha){ 
        if(jugador != ficha.jugador && (columna-ficha.columna<0?columna-ficha.columna>-2:columna-ficha.columna<2)
                && (fila-ficha.fila<0?fila-ficha.fila>-2:fila-ficha.fila<2)){
            if(ficha.escudo >= ataque){ 
                ficha.escudo -= ataque;
            }else{
                ficha.vida -= (ataque-ficha.escudo);
                ficha.escudo = 0;
            }
        }else{
            System.out.println("fuck you");
        }
        if(ficha.vida <= 0){
            kill(ficha);
            if(current==1)//No servia el operador ternario por algun motivo
                current=2;//Escrito como current==1?current=2:current=1;
            else
                current=1;
            
            updateHighlights();
        }
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
    public void mouseClicked(MouseEvent e) {//Basura
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(current == this.jugador){
            if(fichaActiva && ((this instanceof Vampire && "vampire".equals(pieza)) || 
                    (this instanceof Death && "death".equals(pieza)) || 
                    (this instanceof Werewolf && "werewolf".equals(pieza)))){
                fillSpaces();
                currentficha = this;
                fichaActiva=!fichaActiva;
            }else if(!fichaActiva){
                if(currentficha != this){
                    currentficha.ataque(this);
                }
                for(Space s : espacios){ //Quita los espacios del panel
                    panel1.remove(s);
                }
                espacios.clear(); //Quita los espacios de memoria

                panel1.repaint();
                fichaActiva=!fichaActiva;
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
    public void mouseExited(MouseEvent e) { //TODO quitar los valores de la ficha
        attack.setText("");
        hp.setText("");
        shield.setText("");
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored") //Que no chinge netbeans
    private void fillSpaces(){ //Llena espacios dentro del tablero
        if(columna<5){ // Si esta fuera, nisiquiera lo dibuja.
            if(check()){
                new Space(columna+1, fila);
            }
        }
        if(columna>0){
            if(check()){
                new Space(columna-1, fila);
            }
        }
        if(fila<5){
           if(check()){
               new Space(columna, fila+1);
           }
        }
        if(fila>0){
            if(check()){
                new Space(columna, fila-1);
            }
        }
        if(columna<5&&fila<5){
            if(check()){
                new Space(columna+1, fila+1);
            }
        }
        if(columna>0&&fila>0){
            if(check()){
                new Space(columna-1, fila-1);
            }
        }
        if(columna>0&&fila<5){
            if(check()){
                new Space(columna-1, fila+1);
            }
        }
        if(columna<5&&fila>0){
           if(check()){
                new Space(columna+1, fila-1);
            } 
        }
        if(this instanceof Werewolf){
             if(columna<4){ // Si esta fuera, nisiquiera lo dibuja.
            if(check()){
                new Space(columna+2, fila);
            }
        }
        if(columna>1){
            if(check()){
                new Space(columna-2, fila);
            }
        }
        if(fila<4){
           if(check()){
               new Space(columna, fila+2);
           }
        }
        if(fila>1){
            if(check()){
                new Space(columna, fila-2);
            }
        }
        if(columna<4&&fila<4){
            if(check()){
                new Space(columna+2, fila+2);
            }
        }
        if(columna>1&&fila>1){
            if(check()){
                new Space(columna-2, fila-2);
            }
        }
        if(columna>1&&fila<4){
            if(check()){
                new Space(columna-2, fila+2);
            }
        }
        if(columna<4&&fila>1){
           if(check()){
                new Space(columna+2, fila-2);
            } 
        }
        }
    }
    
    private boolean check(){ //Que carajos hace esta funcion?
        for(Ficha f : fichas){ //Yo (k) la hize pero no me acuerdo para que pero
            if((f.fila == fila || f.columna == columna)//sin ella no funciona.
                    && (this.columna != f.columna && this.fila != f.fila)){
                    return false; //La entiendo mas o menos pero para que existe!?!?
            }
        } //Wat r u duin
        return true;//stahp
    }
    
    private void kill(Ficha f){
        fichitas[f.columna][f.fila] = null;
        f.setIcon(null);
        panel1.remove(f);
        panel1.repaint();
        attack.setText("");
        hp.setText("");
        shield.setText("");
        f = null;
    }
    
     void updateHighlights(){
        for(JLabel j : highlights){
            panel1.remove(j);
        }
            highlights.clear();
            panel1.repaint();
        for(Ficha f : fichas){
            if(fichaActiva && f.jugador == current && ((f instanceof Vampire && "vampire".equals(pieza)) || 
            (f instanceof Death && "death".equals(pieza)) || 
            (f instanceof Werewolf && "werewolf".equals(pieza)))){
                JLabel l = new JLabel(highlight);
                l.setBounds((int)(f.columna*100)+3, (int)(100.0*f.fila)+6, 94,98);
                highlights.add(l);
            }
        }
        for(JLabel j : highlights){
            panel1.add(j, panel1.getComponentCount());
        }
    }
}
