/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Codigo;


import Menu.Codigo.Jugador;
import Menu.Visual.MenuPrincipal;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
/**
 *
 * @author Kamil
 */
public class Tablero extends JLayeredPane{
    static boolean fichaActiva = false, spinning = true;
    static int current = 1, p1fichas, p2fichas, p1turnos = 1, p2turnos = 1, p1t=1, p2t=1;
    static String pieza; //Pieza elejida por la ruleta
    static ArrayList<Ficha> fichas = new ArrayList<>();
    static ArrayList<Space> espacios = new ArrayList<>();
    static ArrayList<JLabel> highlights = new ArrayList<>();
    static Ficha[][] fichitas = new Ficha[6][6];
    static JLabel attack, hp, shield, Roullete;
    static JFrame mainFrame = new JFrame();
    static JFrame loadFrame; //Habian varios errores raros si no hacia un nuevo frame.
    static JLayeredPane panel = new JLayeredPane(); //idk
    static Ficha currentficha;
    static JButton spin, save, retirar;
    ArrayList<ImageIcon> answers = new ArrayList<>();
    JLabel Board = new JLabel(); //Tablero es un jlabel pero solo se usa como icono
    ImageIcon BoardImage = new ImageIcon("src\\Game\\Visual\\Board.jpg");
    ImageIcon vampire = new ImageIcon(getClass().getResource("/Game/Visual/VampireChoosing.png"));
    ImageIcon werewolf = new ImageIcon(getClass().getResource("/Game/Visual/WereWolfChoosing.png"));
    ImageIcon death = new ImageIcon(getClass().getResource("/Game/Visual/DeathChoosing.png"));
    Saves info;
    
    public Tablero(Saves info){
        spinning = info.spinning;
        fichas = info.fichas;
        current = info.current;
        p1fichas = info.p1fichas;
        p2fichas = info.p2fichas;
        loadBoard();
        load();
        spinner();
    }
      
    public Tablero(){
        startNew();
        initBoard();
        spinner();
    }

    private void initBoard(){
        spin = new javax.swing.JButton();
        save = new javax.swing.JButton();
        retirar = new javax.swing.JButton();
        Roullete = new javax.swing.JLabel();
        
        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new java.awt.Dimension(844, 629));
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setLayout(null);
        
        Board.setBounds(0,0,600,610); 
        Board.setIcon(BoardImage);
        mainFrame.getContentPane().add(spin);
        spin.setBounds(660, 390, 120, 23);
        save.setBounds(660, 420, 120, 23);
        save.setText("Salvar");
        retirar.setBounds(660, 450, 120, 23);
        retirar.setText("Retirar");
        
        Roullete.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/Game/Visual/MiniRoullete2.gif")));
        mainFrame.getContentPane().add(Roullete);
        Roullete.setBounds(610, 80, 220, 310);

        attack = new JLabel();
        hp = new JLabel();
        shield = new JLabel();
        attack.setBounds(610, 10, 80, 10);
        hp.setBounds(610, 24, 80, 10);
        shield.setBounds(610, 38, 80, 10);
        
        panel.setBounds(0, -5, 600, 610); //Tamano del panel igual al tablero
        mainFrame.add(save);
        mainFrame.add(retirar);
        mainFrame.add(attack);
        mainFrame.add(hp);
        mainFrame.add(shield);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null); //Al medio de la pantalla
        
        panel.add(Board);
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private void startNew(){
        
        new Werewolf(0,0,1);
        new Werewolf(5,0,1);
        new Werewolf(0,5,2);
        new Werewolf(5,5,2);
        
        new Vampire(1,0,1);
        new Vampire(4,0,1);
        new Vampire(1,5,2);
        new Vampire(4,5,2);

        new Death(2,0,1);
        new Death(3,0,1);
        new Death(2,5,2);
        new Death(3,5,2);
        
        p1fichas = 6;
        p2fichas = 6;
        for(Ficha f : fichas){ //Agrega las fichas al panel en el que esta el 
            panel.add(f);      // tablero
        }
        mainFrame.add(panel); //Se agregan las fichas al frame  
        
    }

    private void load(){
        for(Ficha f : fichas){
            panel.add(f, panel.getComponentCount()-1);
        }
        loadFrame.add(panel);
    }
    
    private void loadBoard() { //Poco de problema
        panel = new JLayeredPane();
        loadFrame = new JFrame();
        
        spin = new javax.swing.JButton();
        save = new javax.swing.JButton();
        retirar = new javax.swing.JButton();
        Roullete = new javax.swing.JLabel();
        
        loadFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        loadFrame.setPreferredSize(new java.awt.Dimension(844, 629));
        loadFrame.setResizable(false);
        loadFrame.getContentPane().setLayout(null);
        
        Board.setBounds(0,0,600,610); 
        Board.setIcon(BoardImage);
        loadFrame.getContentPane().add(spin);
        spin.setBounds(660, 470, 120, 23);
        save.setBounds(660, 500, 120, 23);
        save.setText("Salvar");
        retirar.setBounds(660, 530, 120, 23);
        retirar.setText("Retirar");

        Roullete.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/Game/Visual/MiniRoullete2.gif")));
        loadFrame.getContentPane().add(Roullete);
        Roullete.setBounds(610, 160, 220, 310);

        attack = new JLabel();
        hp = new JLabel();
        shield = new JLabel();
        attack.setBounds(610, 10, 80, 10);
        hp.setBounds(610, 24, 80, 10);
        shield.setBounds(610, 38, 80, 10);
        
        panel.setBounds(0, -5, 600, 610); //Tamano del panel igual al tablero
        loadFrame.add(save);
        loadFrame.add(retirar);
        loadFrame.add(attack);
        loadFrame.add(hp);
        loadFrame.add(shield);
        loadFrame.pack();
        loadFrame.setVisible(true);
        loadFrame.setLocationRelativeTo(null); //Al medio de la pantalla
        panel.add(Board);
    }
     
    private void spinner(){
        spin.setText("Stop"); //Empieza girando
        answers.add(vampire);
        answers.add(werewolf);
        answers.add(death);
        spin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    SpinMouseClicked(evt);
                } catch (InterruptedException ex) {}
            }

            public void SpinMouseClicked(MouseEvent evt) throws InterruptedException {
                if(spinning){
                    Collections.shuffle(answers);  //Mezcla el arraylist que contiene las imagenes
                    Roullete.setIcon(answers.get(0));   //elije la primera opcion del arreglo revuelto para simular un random choice.
                    if(answers.get(0).equals(vampire)){
                        pieza = "vampire";
                    }else if(answers.get(0).equals(werewolf)){
                        pieza = "werewolf";
                    }else{
                        pieza = "death";
                    }
                    boolean b = false;
                    for(Ficha f : fichas){
                        if(((f instanceof Vampire && "vampire".equals(pieza)) || 
                        (f instanceof Death && "death".equals(pieza)) || 
                        (f instanceof Zombie && "death".equals(pieza)) ||
                        (f instanceof Werewolf && "werewolf".equals(pieza)))){
                            if(f.jugador == current){
                                b = true;
                            }
                        }
                    }
                    if(b){
                        spinning = false;
                        fichaActiva = false;
                        spin.setVisible(false);
                        fichas.get(0).updateHighlights();
                    }else{
                        fichas.get(0).turnPass();
                        JOptionPane.showMessageDialog(null, "No le quedan fichas de ese tipo, pasando al siguiente turno...");
                    }
                }else{  //Gira giraaaa
                    Roullete.setIcon(new javax.swing.ImageIcon(getClass()
                            .getResource("/Game/Visual/MiniRoullete2.gif")));
                    spin.setText("Stop");
                    spinning = true;
                }
            }
        });
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                Jugador.getUsuarioActual().getSaves().add(new Saves(Menu.Visual.NuevaPartida.tablero));
                JOptionPane.showMessageDialog(null, "Juego Salvado!", "Notificacion!", JOptionPane.INFORMATION_MESSAGE);
                Jugador.reWrite();
            }
        });
        
        retirar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                JOptionPane.showMessageDialog(null, current==2?"El Jugador 1 Ha Ganado!":"El Jugador 2 Ha Ganado!", "Victoria!", JOptionPane.INFORMATION_MESSAGE);
            if(current == 1){
                Jugador.usuarioEnemigo.addPuntos();
            }else{
                Jugador.getUsuarioActual().addPuntos();
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
        });
   }
    
}
