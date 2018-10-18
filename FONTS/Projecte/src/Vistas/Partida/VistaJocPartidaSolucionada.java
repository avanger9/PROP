/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Partida;

import Persistencia.FileAlreadyExistsException;
import Vistas.CtVista;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Administrador
 */
public class VistaJocPartidaSolucionada extends VistaGenerica{
        private int N;
        private int M;        
        
        public void MostrarJocPartidaSolucionadaOK (final CtVista CVista, String[][] sol, int ts, int te, String s) {            
            N = sol.length;
            M = sol[0].length;
            
            JTextPane text = new JTextPane();
            text.setBounds(150, 105, 500, 55);
            text.setText(" La teva solució és correcta!");            
            text.setFont(new Font("Nirmala UI", Font.BOLD, 35));            
            text.setBackground(Color.WHITE);
            text.setForeground(new Color(95,160,0));
            getContentPane().add(text);
            
            JTextField [][] matrizText = new JTextField [N][M]; 
            JPanel panel = new JPanel();
            panel.setBounds(180, 170, 425, 250);
            getContentPane().add(panel);

            panel.setLayout(new GridLayout(N,M));       
      
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {                                                
                    matrizText[x][y] = new JTextField();
                    matrizText[x][y].setText(sol[x][y]);
                    matrizText[x][y].setHorizontalAlignment(JTextField.CENTER);
                    matrizText[x][y].setFont(new Font("Nyala", Font.PLAIN, 25));
                    matrizText[x][y].setEditable(false);
                    panel.add(matrizText[x][y]);

                }
            }
            
            // Botó "Enrere" 
            JButton b1 = new JButton();
            b1.setText("Enrere");
            b1.setBounds(20,440,150,70);
            b1.setBackground(new Color(0,0,0));
            b1.setForeground(new Color(107,175,1));
            b1.setFont(new Font("Candara", Font.BOLD, 25));
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        matrizText[x][y] = new JTextField();
                        matrizText[x][y].setText("");
                    }
                }                        
                    CVista.ActivarVMenuPrincipal(s);
                    //Cerramos la principal
                    Sortir();
                }
            });        
            getContentPane().add(b1);           
            
            JTextPane text2 = new JTextPane();
            text2.setBounds(240, 450, 400, 50);
            text2.setText("Vols guardar al rànquing?");
            text2.setFont(new Font("Nirmala UI", Font.BOLD, 30));
            text2.setOpaque(false);
            text2.setForeground(Color.WHITE);
            getContentPane().add(text2);
            
            // Botó "Reiniciar" per reiniciar el joc        
            JButton b5 = new JButton();
            b5.setText("Guardar");
            b5.setBounds(620,440,150,70);
            b5.setBackground(new Color(255,255,255));
            b5.setForeground(new Color(95,160,0));
            b5.setFont(new Font("Candara", Font.BOLD, 25));                
            b5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int ttotal = (te - ts)/1000;
                        System.out.println(Integer.toString(ttotal)+" segons");
                        CVista.desar_ranking(s, ttotal);
                        CVista.ActivarVMenuPrincipal(s);
                        //Cerramos la principal
                        Sortir();
                    } catch (IOException ex) {
                        Logger.getLogger(VistaJocPartidaSolucionada.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileAlreadyExistsException ex) {
                        Logger.getLogger(VistaJocPartidaSolucionada.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            });        
            getContentPane().add(b5);
                
        }
        
        public void MostrarJocPartidaSolucionadaERROR (final CtVista CVista, String[][] sol, String[][] sol_correcta, String s) {
            N = sol.length;
            M = sol[0].length;
            
            JTextPane text = new JTextPane();
            text.setBounds(150, 105, 500, 55);
            text.setText(" La teva solució és incorrecta!");
            text.setFont(new Font("Nirmala UI", Font.BOLD, 35));            
            text.setBackground(Color.WHITE);
            text.setForeground(new Color(95,160,0));
            getContentPane().add(text);
            
            JTextPane text2 = new JTextPane();
            text2.setBounds(120, 155, 600, 50);
            text2.setText("Tu solució:");
            text2.setFont(new Font("Nirmala UI", Font.BOLD, 30));
            text2.setOpaque(false);
            text2.setForeground(Color.WHITE);
            getContentPane().add(text2);
            
            JTextPane text3 = new JTextPane();
            text3.setBounds(460, 155, 600, 50);
            text3.setText("Solució correcte:");
            text3.setFont(new Font("Nirmala UI", Font.BOLD, 30));
            text3.setOpaque(false);
            text3.setForeground(Color.WHITE);
            getContentPane().add(text3);
            
            JTextField [][] matrizText = new JTextField [N][M]; 
            JPanel panel = new JPanel();
            panel.setBounds(10, 205, 380, 230);
            getContentPane().add(panel);

            panel.setLayout(new GridLayout(N,M));       
      
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {                                                
                    matrizText[x][y] = new JTextField();
                    matrizText[x][y].setText(sol[x][y]);
                    matrizText[x][y].setHorizontalAlignment(JTextField.CENTER);
                    matrizText[x][y].setFont(new Font("Nyala", Font.PLAIN, 25));
                    matrizText[x][y].setEditable(false);
                    panel.add(matrizText[x][y]);

                }
            }
            
            N = sol_correcta.length;
            M = sol_correcta[0].length;
            
            JTextField [][] matrizText2 = new JTextField [N][M]; 
            JPanel panel2 = new JPanel();
            panel2.setBounds(400, 205, 380, 230);
            getContentPane().add(panel2);

            panel2.setLayout(new GridLayout(N,M));       
      
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {                                                
                    matrizText2[x][y] = new JTextField();
                    matrizText2[x][y].setText(sol_correcta[x][y]);
                    matrizText2[x][y].setHorizontalAlignment(JTextField.CENTER);
                    matrizText2[x][y].setFont(new Font("Nyala", Font.PLAIN, 25));
                    matrizText2[x][y].setEditable(false);
                    panel2.add(matrizText2[x][y]);

                }
            }
            
            // Botó "Enrere" 
            JButton b1 = new JButton();
            b1.setText("Enrere");
            b1.setBounds(20,440,150,70);
            b1.setBackground(new Color(0,0,0));
            b1.setForeground(new Color(107,175,1));
            b1.setFont(new Font("Candara", Font.BOLD, 25));
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        matrizText[x][y] = new JTextField();
                        matrizText[x][y].setText("");
                    }
                }                        
                    CVista.ActivarVMenuPrincipal(s);
                    //Cerramos la principal
                    Sortir();
                }
            });        
            getContentPane().add(b1);
        }    
    
    
        public VistaJocPartidaSolucionada (final CtVista CVista) {
            setTitol("Joc Hidato Solucionat");
        
            Titol t = new Titol("Joc Hidato Solucionat", 50,30,400,60);       
            getContentPane().add(t);
            
            // Botó "Reiniciar" per reiniciar el joc        
            JButton b2 = new JButton();
            b2.setText("Sortir");
            b2.setBounds(650,30,150,70);
            b2.setBackground(new Color(0,0,0));
            b2.setForeground(new Color(107,175,1));
            b2.setFont(new Font("Candara", Font.BOLD, 25));                
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Cerramos la principal
                    Sortir();
                }
            });        
            getContentPane().add(b2);
    }       
}
