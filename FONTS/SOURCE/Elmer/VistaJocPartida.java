/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Partida;

import Domini.GetNumException;
import Domini.HidatoNoSolucionableException;
import Persistencia.AdjacenciaNoExisteixException;
import Persistencia.CelaNoExisteixException;
import Persistencia.FileAlreadyExistsException;
import Persistencia.FileNotExistsException;
import Persistencia.UsuariNoExisteixException;
import Vistas.CtVista;
import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Administrador
 */
public class VistaJocPartida extends VistaGenerica{
    
    private int N;
    private int M;
      
    public void MostrarJocPartida(final CtVista CVista, String[] aux, String [][] hr, int time_start,String s) { 
        
        N = hr.length;
        M = hr[0].length;        
        String[][] hi = hr;
        String[][] solucio = new String[N][M];        
        
        JPanel panel = new JPanel();
        panel.setBounds(150, 100, 475, 300);
        getContentPane().add(panel);

      
        panel.setLayout(new GridLayout(N,M));       
        
        JTextField [][] matrizText = new JTextField [N][M];                 

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                matrizText[x][y] = new JTextField();
                matrizText[x][y].setText(hr[x][y]);
                if ((hr[x][y]).equals("?")) matrizText[x][y].setEditable(true);
                else matrizText[x][y].setEditable(false);
                matrizText[x][y].setHorizontalAlignment(JTextField.CENTER);
                matrizText[x][y].setFont(new Font("Nyala", Font.PLAIN, 25));
                
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
                CVista.ActivarVTipusPartidaHidato(s);
                //Cerramos la principal
                Sortir();
            }
        });        
        getContentPane().add(b1);

        // Botó "Guardar" per guardar el joc
        JButton b2 = new JButton();
        b2.setText("Guardar Partida");
        b2.setBounds(200,440,210,70);
        b2.setBackground(new Color(0,0,0));
        b2.setForeground(new Color(107,175,1));
        b2.setFont(new Font("Candara", Font.BOLD, 25));                
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //CVista.desar_hidato(s,hr,aux[0], aux[1]);
                    CVista.desar_partida_hidato(s, 1,hr, aux[0],aux[1],1);
                    CVista.ActivarVMenuPrincipal(s);
                    //Cerramos la principal
                    Sortir();
                } catch (UsuariNoExisteixException ex) {
                    Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotExistsException ex) {
                    Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileAlreadyExistsException ex) {
                    Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AdjacenciaNoExisteixException ex) {
                    Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CelaNoExisteixException ex) {
                    Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });        
        getContentPane().add(b2);                   
        
        
        JTextPane TextError = new JTextPane();
        // Botó "Reiniciar" per reiniciar el joc        
        JButton b3 = new JButton();
        b3.setText("Reiniciar");
        b3.setBounds(440,440,150,70);
        b3.setBackground(new Color(0,0,0));
        b3.setForeground(new Color(107,175,1));
        b3.setFont(new Font("Candara", Font.BOLD, 25));                
         
        b3.addActionListener(new ActionListener() {
               
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.updateUI();
                panel.repaint();
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        matrizText[x][y] = new JTextField();
                        matrizText[x][y].setText(hi[x][y]);
                        matrizText[x][y].setHorizontalAlignment(JTextField.CENTER);
                        matrizText[x][y].setFont(new Font("Nyala", Font.PLAIN, 25));
                        if ((hi[x][y]).equals("?")) matrizText[x][y].setEditable(true);
                        else matrizText[x][y].setEditable(false);
                        panel.add(matrizText[x][y]);
                    }
               
                }                      
                validate();
               
            }
        });        
        getContentPane().add(b3);            
        
        
// Botó "Reiniciar" per reiniciar el joc        
        JButton b4 = new JButton();
        b4.setText("Sortir");
        b4.setBounds(650,30,150,70);
        b4.setBackground(new Color(0,0,0));
        b4.setForeground(new Color(107,175,1));
        b4.setFont(new Font("Candara", Font.BOLD, 25));                
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Cerramos la principal
                Sortir();
            }
        });        
        getContentPane().add(b4);        
        
        // Botó "Reiniciar" per reiniciar el joc        
        JButton b5 = new JButton();
        b5.setText("Comprova");
        b5.setBounds(620,440,150,70);
        b5.setBackground(new Color(255,255,255));
        b5.setForeground(new Color(95,160,0));
        b5.setFont(new Font("Candara", Font.BOLD, 25));                
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        if ((matrizText[x][y].getText()).equals("?")) solucio[x][y] = "-1";
                        solucio[x][y] = matrizText[x][y].getText();                    
                    }
                }
                if (CVista.es_solucio_hidato(solucio, aux)) { 
                    long time_end;
                    time_end = System.currentTimeMillis();
                    CVista.PartidaCorrecta(solucio, time_start, (int) time_end, s);
                    //Cerramos la principal
                    Sortir();
                } else {
                    System.out.println("Solucio incorrecta");
                    try {
                        String[][] solucio_correcta = CVista.get_solucio_hidato(hr,aux);
                        CVista.ActivarVJocPartidaSolucionada(solucio, solucio_correcta, s);
                        //Cerramos la principal
                        Sortir();                    
                    } catch (HidatoNoSolucionableException ex) {
                        Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (GetNumException ex) {
                        Logger.getLogger(VistaJocPartida.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });        
        getContentPane().add(b5);
    }    
    
    public VistaJocPartida (final CtVista CVista) {
        setTitol("Joc Hidato");
        
        Titol t = new Titol("Joc Hidato", 50,30,400,60);       
        getContentPane().add(t);    
        

        
        
        
    }            
}