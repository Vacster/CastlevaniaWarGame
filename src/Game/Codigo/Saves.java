/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Codigo;

import Menu.Codigo.Jugador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Kamil
 */
public class Saves implements Serializable, Saved{
    
    ArrayList<Ficha> fichas = new ArrayList<>();
    boolean spinning;
    int current, p1fichas, p2fichas;
    Ficha[][] fichitas;
    public Jugador enemigo;
    private Calendar fecha;
    
   @SuppressWarnings("static-access")
    public Saves(Tablero t){
        for(Ficha f : t.fichas){
            fichas.add(f);
        }
        current = t.current;
        p1fichas = t.p1fichas;
        p2fichas = t.p2fichas;
        fichitas = t.fichitas;
        enemigo = Jugador.usuarioEnemigo;
        fecha = Calendar.getInstance();
    }

    @Override
    public String toString() {
        return " VS " + enemigo.getUsername() + " - " + fecha.getTime();
    }
    
    
}
