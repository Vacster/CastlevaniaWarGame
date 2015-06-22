/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu.Codigo;

//import static Menu.Visual.MenuInicial.jugadores;
import Game.Codigo.Saves;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author juans_000
 */
public class Jugador implements Serializable{
    private String username;
    private String password;
    private int puntos;
    private File partidasSalvadas =  null;
    public Calendar calendar;
    public static ArrayList<Jugador> jugadores = new ArrayList<>();
    private ArrayList<Saves> saves = new ArrayList<>();
    private static Jugador usuarioActual = null;
    public static Jugador usuarioEnemigo = null;
    
    
    @SuppressWarnings("LeakingThisInConstructor")
    public Jugador(String u, String p){
        username = u;
        password = p;
        puntos = 0;
        partidasSalvadas = new File("src\\Menu\\Codigo\\" + u);
        //partidasSalvadas.mkdir();
        usuarioActual = this; // cambiar esta parte
        calendar = Calendar.getInstance();
    }
    
    public ArrayList getSaves()
    {
        return saves;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public int getPuntos()
    {
        return puntos;
    }
    
    public boolean setPassword(String p)
    {
        if( p.length() == 8)
        {
        password = p;
        Jugador.reWrite();
        return true;
        }
        return false;
    }

    
    public void addPuntos()
    {
        puntos += 3;
        Jugador.reWrite();
    }
    
    public static Jugador getUsuarioActual()
    {
        return usuarioActual;
    }
    
    public static void nullEnemigo()
    {
        usuarioEnemigo = null;
    }
    
    public static boolean crearPlayer(String u, String p)
    {
        if(search(u) == -1)
        {
            if( p.length() == 8 )
            {
                jugadores.add(new Jugador(u, p));
                Jugador.reWrite();
                return true;
            }
            else
            {
                //System.out.println("La contraseña debe tener exactamente 8 caracteres!");
                JOptionPane.showMessageDialog(null, "La contraseña debe tener exactamente 8 caracteres!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        else
        {
            //System.out.println("El usuario ya existe!");
            JOptionPane.showMessageDialog(null, "El usuario ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean loginPlayer(String u, String p)
    {
        int j = search(u);
        if( j != -1)
        {
            if(jugadores.get(j).getPassword().equals(p))
            {
                usuarioActual = jugadores.get(j);
                return true;
            }
            JOptionPane.showMessageDialog(null, "Contraseña Incorrecta!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //System.out.println("nop!");//quitar
        JOptionPane.showMessageDialog(null, "El usuario no existe!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public static boolean loginPlayerEnemigo(String u, String p)
    {
        int j = search(u);
        if (j != search(Jugador.getUsuarioActual().getUsername()))
        {
            if( j != -1)
            {
                if(jugadores.get(j).getPassword().equals(p))
                {
                    usuarioEnemigo = jugadores.get(j);
                    return true;
                }
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            //System.out.println("nop!");//quitar
            JOptionPane.showMessageDialog(null, "El usuario no existe!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null, "No puedes jugar contra ti mismo!", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public static boolean borrar(boolean si)
    {
            if(si && usuarioActual == jugadores.remove(search(usuarioActual.getUsername())))
            {
                usuarioActual = null;
                Jugador.reWrite();
                return true;
            }
            return false;
    }
    
    public static void reWrite(){
        File borrador = new File("src\\Menu\\Codigo\\players.vwg");
        borrador.delete();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\Menu\\Codigo\\players.vwg")))
        {
            oos.writeObject(jugadores);
            oos.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    //Funcion search no recursiva
//    public static int search(String u)
//    {
//        int i = 0;
//        for(Jugador j:jugadores)
//        {
//            if(j.getUsername().equalsIgnoreCase(u))
//                return i;
//        }
//        return -1;
//    }
    
    //Funcion search recursiva
    private final static int search(String u, int cont)
    {
        if(cont < jugadores.size())
        {
            if (jugadores.get(cont).getUsername().equalsIgnoreCase(u))
                return cont;
            return search(u, cont+1);
        }
        return -1;
    }
    
    //Funcion search para llamar a la otra :/
    //esque no le queria poner ceros a todas los search
    public final static int search(String u)
    {
        return search(u, 0);
    }
    
    public void borrarSave(String e)
    {
        int i = Integer.valueOf(Character.toString(e.charAt(0)));
        saves.remove(i);
    }
}
