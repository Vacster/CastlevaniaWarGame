/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author Kamil
 */
public class Tablero extends JLayeredPane{
    static boolean fichaActiva = false, spinning = true;
    static int current = 1;
    static String pieza; //Pieza elejida por la ruleta
    static ArrayList<Ficha> fichas = new ArrayList<>();
    static ArrayList<Space> espacios = new ArrayList<>();
    static ArrayList<JLabel> highlights = new ArrayList<>();
    static Ficha[][] fichitas = new Ficha[6][6]; //Iniziar todas las imagenes usadas 
    static JLabel attack, hp, shield, Roullete;
    static JFrame Frame = new JFrame();
    static JLayeredPane panel1 = new JLayeredPane(); //idk
    static Ficha currentficha;
    static JButton Spin;
    boolean end = true; //z define si esta girando o no.
    ArrayList<ImageIcon> answers = new ArrayList<>();
    JLabel Board = new JLabel(); //Tablero es un jlabel pero solo se usa como icono
    ImageIcon BoardImage = new ImageIcon("src\\Game\\Visual\\Board.jpg");
    ImageIcon vampire = new ImageIcon(getClass().getResource("/Game/Visual/VampireChoosing.png"));
    ImageIcon werewolf = new ImageIcon(getClass().getResource("/Game/Visual/WereWolfChoosing.png"));
    ImageIcon death = new ImageIcon(getClass().getResource("/Game/Visual/DeathChoosing.png"));
    
    
    public Tablero(){
        Spin = new javax.swing.JButton();
        Roullete = new javax.swing.JLabel();
        answers.add(vampire);
        answers.add(werewolf);
        answers.add(death);
        
        Frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Frame.setPreferredSize(new java.awt.Dimension(844, 629));
        Frame.setResizable(false);
        Frame.getContentPane().setLayout(null);
        start();
        
        Board.setBounds(0,-5,600,610); //No quedaba exacto si y es 0
        Board.setIcon(BoardImage);
        Frame.add(Board);
        
        Spin.setText("Stop"); //Empieza girando
        Spin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                SpinMouseClicked(evt);
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
                        Spin.setVisible(false);
                        fichas.get(0).updateHighlights();
                    }else{
                        wait(5000);
                        fichas.get(0).turnPass();
                        if(current==1)//No servia el operador ternario por algun motivo
                            current=2;//Escrito como current==1?current=2:current=1;
                        else
                            current=1;
                    }
                }else{  //Gira giraaaa
                    Roullete.setIcon(new javax.swing.ImageIcon(getClass()
                            .getResource("/Game/Visual/MiniRoullete2.gif")));
                    Spin.setText("Stop");
                    spinning = true;
                }
            }
        });
        Frame.getContentPane().add(Spin);
        Spin.setBounds(660, 470, 120, 23);

        Roullete.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/Game/Visual/MiniRoullete2.gif")));
        Frame.getContentPane().add(Roullete);
        Roullete.setBounds(610, 160, 220, 310);

        attack = new JLabel();
        hp = new JLabel();
        shield = new JLabel();
        attack.setBounds(610, 10, 80, 10);
        hp.setBounds(610, 24, 80, 10);
        shield.setBounds(610, 38, 80, 10);
        
        
        Frame.add(attack);
        Frame.add(hp);
        Frame.add(shield);
        Frame.pack();
        Frame.setLocationRelativeTo(null);
        game();
    }

   private void start(){
        Frame.setVisible(true);
        Frame.setLocationRelativeTo(null); //Al medio de la pantalla
        
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
        
        for(Ficha f : fichas){ //Agrega las fichas al panel en el que esta el 
            panel1.add(f);      // tablero
        }
        panel1.setBounds(0, -5, 600, 610); //Tamano del panel igual al tablero
        Frame.add(panel1); //Se agregan las fichas al frame      
    }

   private void game(){
       while(end){
           
       }
   }

}
