/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Codigo;

import static Game.Codigo.Tablero.*; //#yolo
import Menu.Codigo.Jugador;
import Menu.Visual.MenuPrincipal;
import java.awt.event.MouseEvent; //puntos extra por entender este relajo
import java.awt.event.MouseListener; //este tambien
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
                        panel.remove(s);
                    }
                    espacios.clear(); //Quita los espacios de memoria

                    panel.repaint();
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
                        panel.remove(s);
                    }
                espacios.clear(); //Quita los espacios de memoria

                panel.repaint();
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
            current=(current==1?2:1);
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
        }
    }
    
    @SuppressWarnings({"element-type-mismatch", "ResultOfObjectAllocationIgnored"})
    private void kill(Ficha f){
        fichitas[f.columna][f.fila] = null;
        f.setIcon(null);
        
        if(f instanceof Death){
            for(Ficha fich : (((Death)f).Zombies)){
                panel.remove(fich);
            }
        }else if(f instanceof Zombie){
            ((Zombie)f).dad.Zombies.remove(this);
        }
        
        fichas.remove(f);
        
        panel.remove(f);
        panel.repaint();
        attack.setText("");
        hp.setText("");
        shield.setText("");
        
        if(!(f instanceof Zombie)){
            if(current==2){
                p1fichas -= 1;
                if(p1fichas<=2){
                    p1turnos=3;
                }else if(p1fichas<=4){
                    p1turnos=2;
                }
            }else{
                p2fichas -= 1;
                if(p2fichas<=2){
                    p2turnos=3;
                }else if(p2fichas<=4){
                    p2turnos=2;
                }
            }
        }
        
        
        
        if(p1fichas==0 || p2fichas==0){
            JOptionPane.showMessageDialog(null, current==1?"El Jugador 1 Ha Ganado!":"El Jugador 2 Ha Ganado!", "Victoria!", JOptionPane.INFORMATION_MESSAGE);
            if(current == 1){
                Jugador.getUsuarioActual().addPuntos();
            }else{
                Jugador.usuarioEnemigo.addPuntos();
            }
            if(JOptionPane.showConfirmDialog(null, "Quieres Publicarlo en Facebook?", "Victoria!", JOptionPane.INFORMATION_MESSAGE)==JOptionPane.YES_OPTION){
                FacebookConnector fb = new FacebookConnector();
                fb.makePost("El jugador " + (current==1?Jugador.usuarioEnemigo.getUsername():Jugador.getUsuarioActual().getUsername()) + " le ha ganado al jugador "
                        + (current==2?Jugador.usuarioEnemigo.getUsername():Jugador.getUsuarioActual().getUsername()) + " en Castlevania WarGame!");
            }
            Jugador.reWrite();
            new MenuPrincipal().setVisible(true);
            if(mainFrame != null){
                mainFrame.dispose();
            }if(loadFrame != null){
                loadFrame.dispose();
            }
        }
    }
    
    void updateHighlights(){
        for(JLabel j : highlights){
            panel.remove(j);
        }
        highlights.clear();
        panel.repaint();
        if(spinning == false){
            for(Ficha f : fichas){
                if(!fichaActiva && f.jugador == current && ((f instanceof Vampire && "vampire".equals(pieza)) || 
                (f instanceof Death && "death".equals(pieza)) ||
                (f instanceof Werewolf && "werewolf".equals(pieza)))){
                    JLabel l = new JLabel(highlight);
                    l.setBounds((int)(f.columna*100)+3, (int)(100.0*f.fila)+6, 94,98);
                    highlights.add(l);
                    panel.add(l, panel.getComponentCount()-1);
                }
            }
        }
    }
     
    void turnPass(){
        if(current == 1){
            if(p1turnos == p1t){
                current=2;
                p1t=1;
            }else{
                p1t+=1;
            }
        }else{
            if(p2turnos == p2t){
                current=1;
                p2t=1;
            }else{
                p2t+=1;
            }
        }
        Roullete.setIcon(new javax.swing.ImageIcon(getClass()
                    .getResource("/Game/Visual/MiniRoullete2.gif")));
        spin.setText("Stop");
        spin.setVisible(true);
        spinning = true;
    }

    public abstract void fillSpaces();
}
