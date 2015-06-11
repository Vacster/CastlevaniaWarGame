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
    
    boolean end = true, z = true; //z define si esta girando o no.
    static JFrame Frame = new JFrame();
    static JLayeredPane panel1 = new JLayeredPane(); //idk
    JLabel Board = new JLabel(); //Tablero es un jlabel pero solo se usa como icono
    Werewolf were1, were2, were3, were4; //Siempre seran 4 lobos
    Vampire vamp1, vamp2, vamp3, vamp4;
    Death death1, death2, death3, death4;
    ArrayList<ImageIcon> answers = new ArrayList<>();
    static ArrayList<Ficha> fichas = new ArrayList<>();
    static ArrayList<Space> espacios = new ArrayList<>();
    static Ficha[][] fichitas = new Ficha[6][6]; //Iniziar todas las imagenes usadas
    final static ImageIcon WerewolfP1 = new ImageIcon("src\\Game\\Visual\\WerewolfP1.png");
    final static ImageIcon WerewolfP2 = new ImageIcon("src\\Game\\Visual\\WerewolfP2.png");
    final static ImageIcon DeathP1 = new ImageIcon("src\\Game\\Visual\\DeathP1.png");
    final static ImageIcon DeathP2 = new ImageIcon("src\\Game\\Visual\\DeathP2.png");
    final static ImageIcon VampireP1 = new ImageIcon("src\\Game\\Visual\\VampireP1.png");
    final static ImageIcon VampireP2 = new ImageIcon("src\\Game\\Visual\\VampireP2.png");
    final static ImageIcon ZombieP1 = new ImageIcon("src\\Game\\Visual\\ZombieP1.png");
    final static ImageIcon ZombieP2 = new ImageIcon("src\\Game\\Visual\\ZombieP2.png");
    final static ImageIcon Space = new ImageIcon("src\\Game\\Visual\\Space.png");
    ImageIcon BoardImage = new ImageIcon("src\\Game\\Visual\\Board.jpg");
    ImageIcon vampire = new ImageIcon(getClass().getResource("/Game/Visual/VampireChoosing.png"));
    ImageIcon werewolf = new ImageIcon(getClass().getResource("/Game/Visual/WereWolfChoosing.png"));
    ImageIcon death = new ImageIcon(getClass().getResource("/Game/Visual/DeathChoosing.png"));
    
    public Tablero(){
        JButton Spin = new javax.swing.JButton();
        JLabel Roullete = new javax.swing.JLabel();
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SpinMouseClicked(evt);
            }

            private void SpinMouseClicked(MouseEvent evt) {
                if(z){
                    Collections.shuffle(answers);  //Mezcla el arraylist que contiene las imagenes
                    Roullete.setIcon(answers.get(0));   //elije la primera opcion del arreglo revuelto para simular un random choice.
                    Spin.setText("Spin");
                    z = false;
                }else{  //Gira giraaaa
                    Roullete.setIcon(new javax.swing.ImageIcon(getClass()
                            .getResource("/Game/Visual/MiniRoullete2.gif")));
                    Spin.setText("Stop");
                    z = true;
                }
            }
        });
        Frame.getContentPane().add(Spin);
        Spin.setBounds(660, 470, 120, 23);

        Roullete.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/Game/Visual/MiniRoullete2.gif")));
        Frame.getContentPane().add(Roullete);
        Roullete.setBounds(610, 160, 220, 310);

        Frame.pack();
        Frame.setLocationRelativeTo(null);
        game();
    }

   private void start(){
        Frame.setVisible(true);
        Frame.setLocationRelativeTo(null); //Al medio de la pantalla
        for(int x = 0; x<6;x++){//Inicializa las fichas
            for(int y = 0; y<6;y++){
                switch(x){
                    case 0: if(y==0){
                                were1 = new Werewolf(x,y,1);
                            }else if(y==5){
                                were3 = new Werewolf(x,y,2);
                            }break;
                    case 1: if(y==0){
                                vamp1 = new Vampire( x,y,1);
                            }else if(y==5){
                                vamp3 = new Vampire(x,y,2);
                            }break;   
                    case 4: if(y==0){
                                vamp2 = new Vampire(x,y,1);
                            }else if(y==5){
                                vamp4 = new Vampire(x,y,2);
                            }break;  
                    case 5: if(y==0){
                                were2 = new Werewolf(x,y,1);
                            }else if(y==5){
                                were4 = new Werewolf(x,y,2);
                            }break;
                    default: System.out.println("Nothing here."+x+y); break;
                }   //^opcional
            }
        }
        for(Ficha f : fichas){ //Agrega las fichas al panel en el que esta el 
            panel1.add(f);      // tablero
        }
        panel1.setBounds(0, -5, 600, 610); //Tamano del panel igual al tablero
        Frame.add(panel1); //Se agregan las fichas al frame
    }

   private void game(){
       while(end){
           //TODO logica del juego lel.
       }
   }

}
