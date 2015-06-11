/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import static Game.Tablero.*; //#yolo
import java.awt.event.MouseEvent; //puntos extra por entender este relajo
import java.awt.event.MouseListener; //este tambien
import javax.swing.JLabel;

/**
 *
 * @author Kamil I
 */
public abstract class Ficha extends JLabel implements MouseListener{
    
    protected int vida, escudo, ataque, columna, fila;
    
    @SuppressWarnings("LeakingThisInConstructor") //Que no chingue netbeans #yolo
    public Ficha(int a, int v, int e, int columna, int fila, int jugador){
        ataque = a;
        vida = v;
        escudo = e;
        this.columna = columna;
        this.fila = fila;
        setBounds((int)(100.0*columna)+10, (int) (100.0*fila)+5, 85, 88);
        if(this instanceof Werewolf){
            setIcon(jugador==1?WerewolfP1:WerewolfP2);
        }else if(this instanceof Vampire){
            setIcon(jugador==1?VampireP1:VampireP2);
        }else if(this instanceof Death){
            setIcon(jugador==1?DeathP1:DeathP2);
        }else{
            setIcon(jugador==1?ZombieP1:ZombieP2);
        }
        fichitas[this.columna][this.fila] = this;//Se que es peligroso usar this
        fichas.add(this); //en el constructor pero lo mas importante son los 
        addMouseListener(this); //valores definidos antes de usar los "this"
    }
    
    /**
     * 
     * @param ficha Se entrega la ficha atacada
     */
    public void ataque(Ficha ficha){ 
        if(ficha.escudo >= ataque){ 
            ficha.escudo -= ataque;
        }else{
            ficha.vida -= (ataque-ficha.escudo);
            escudo = 0;
        }
    }
    
    void movimiento(int columna, int fila){ //Cambia la ficha en el array
        if(fichitas[columna][fila] == null){ //Y las dibuja donde van
            fichitas[this.columna][this.fila] = null;
            this.columna = columna;
            this.fila = fila;
            fichitas[columna][fila] = this;
            setBounds((int)(100.0*this.columna)+10, (int)(100.0*this.fila)+5, 85,88);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {//Basura
    }

    @Override
    public void mousePressed(MouseEvent e) {
        fillSpaces();
        System.out.println("click");
    }

    @Override
    public void mouseReleased(MouseEvent e) { //Esto estaba asqueroso antes
        if(e.getX()<200 && e.getX()>-100 && e.getY() > -100 && e.getY() < 200){ // Que no ponga el mouse afuera del rango.
            if(e.getX()>90){ // Derecha
                if(e.getY()>99){
                    movimiento(this.columna+1, this.fila+1); //Derecha Abajo
                }else if(e.getY()<0){
                    movimiento(this.columna+1, this.fila-1); //Derecha Arriba
                }else{
                    movimiento(this.columna+1, this.fila); //Derecha
                }
            }else if(e.getX()<0){ //Izquierda
                if(e.getY()>99){
                    movimiento(this.columna-1, this.fila+1); //Izquierda Abajo
                }else if(e.getY()<0){
                    movimiento(this.columna-1, this.fila-1); //Izquierda Arriba
                }else{
                    movimiento(this.columna-1, this.fila); //Izquierda
                }
            }else{
                 if(e.getY()>99){ //Medio
                    movimiento(this.columna, this.fila+1); //Abajo 
                }else if(e.getY()<0){
                    movimiento(this.columna, this.fila-1); //Arriba
                }
            }
        }
                
        for(Space s : espacios){ //Quita los espacios del panel
            panel1.remove(s);
        }
        espacios.clear(); //Quita los espacios de memoria
        
        panel1.repaint(); //Actualiza el panel
        
        System.out.println("release"); //Opcional
    }

    @Override
    public void mouseEntered(MouseEvent e) {//TODO mostrar valores de la ficha
        System.out.println("mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) { //TODO quitar los valores de la ficha
        System.out.println("mouse exited");
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
        
    }
    
    private boolean check(){ //Que carajos hace esta funcion?
        for(Ficha f : fichas){ //Yo la hize pero no me acuerdo para que pero
            if((f.fila == fila || f.columna == columna)//sin ella no funciona.
                    && (this.columna != f.columna && this.fila != f.fila)){
                    return false; //La entiendo mas o menos pero para que existe!?!?
            }
        } //Wat r u duin
        return true;//stahp
    }
}
