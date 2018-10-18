/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Hidato;

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
public class VistaAfegirHidato extends VistaGenerica{
    private int N;
    private int M;
    
    public void MostrarAfegirHidato(final CtVista CVista, String s, String aux[], int tD) {
        
        N = Integer.parseInt(aux[2]);
        M = Integer.parseInt(aux[3]);
        
        String[][] mihidato = new String[N][M];
        
        String[][] hr = new String[N][M];
        JPanel panel = new JPanel();
        panel.setBounds(150, 100, 475, 300);
        getContentPane().add(panel);

      
        panel.setLayout(new GridLayout(N,M));       
        
        JTextField [][] matrizText = new JTextField [N][M];                 

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                matrizText[x][y] = new JTextField();
                matrizText[x][y].setText(hr[x][y]);
                matrizText[x][y].setEditable(true);                
                matrizText[x][y].setHorizontalAlignment(JTextField.CENTER);
                matrizText[x][y].setFont(new Font("Nyala", Font.PLAIN, 25));
                
                panel.add(matrizText[x][y]);
            }
        }        
        
        
        JTextPane TextError = new JTextPane();   
        
        
        
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
                        TextError.setText("");                  
                    }
                }                
                CVista.ActivarVAfegirTipusHidato(s);
                //Cerramos la principal
                Sortir();
            }
        });        
        getContentPane().add(b1);
        

        
        // Botó "Reiniciar" per reiniciar el joc
        JButton b2 = new JButton();
        b2.setText("Reiniciar");        
        b2.setBounds(300,440,210,70);
        b2.setBackground(new Color(0,0,0));
        b2.setForeground(new Color(107,175,1));
        b2.setFont(new Font("Candara", Font.BOLD, 25)); 
        b2.addActionListener(new ActionListener() {
               
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.updateUI();
                panel.repaint();
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {
                        matrizText[x][y] = new JTextField();
                        matrizText[x][y].setText("");
                        matrizText[x][y].setHorizontalAlignment(JTextField.CENTER);
                        matrizText[x][y].setFont(new Font("Nyala", Font.PLAIN, 25));
                        matrizText[x][y].setEditable(true);                        
                        panel.add(matrizText[x][y]);
                    }
               
                }                      
                validate();
               
            }
        });        
        getContentPane().add(b2); 

        // Botó "Guardar" per guardar el joc
        JButton b3 = new JButton();
        b3.setText("Guardar");        
        b3.setBounds(620,440,150,70);
        b3.setBackground(new Color(0,0,0));
        b3.setForeground(new Color(107,175,1));
        b3.setFont(new Font("Candara", Font.BOLD, 25));    
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < M; y++) {                        
                        mihidato[x][y] = matrizText[x][y].getText();                    
                    }
                }
              
                    if (CVista.es_solucio_hidato(mihidato,aux)) {                        
                        try {
                            CVista.desar_hidato(s,mihidato,aux[0],aux[1]);
                            TextError.setBounds(620,410, 240, 61);                
                            TextError.setText("Guardat correctament.");
                            TextError.setFont(new Font("Nirmala UI", Font.BOLD, 15));		
                            TextError.setOpaque(false);
                            TextError.setForeground(Color.WHITE); 
                            getContentPane().add(TextError);
                         
                        } catch (UsuariNoExisteixException ex) {
                            Logger.getLogger(VistaAfegirHidato.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotExistsException ex) {
                            Logger.getLogger(VistaAfegirHidato.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(VistaAfegirHidato.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileAlreadyExistsException ex) {
                            Logger.getLogger(VistaAfegirHidato.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AdjacenciaNoExisteixException ex) {
                            Logger.getLogger(VistaAfegirHidato.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CelaNoExisteixException ex) {
                            Logger.getLogger(VistaAfegirHidato.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        TextError.setBounds(630,410, 240, 61);                
                        TextError.setText("No té solució.");
                        TextError.setFont(new Font("Nirmala UI", Font.BOLD, 15));		
                        TextError.setOpaque(false);
                        TextError.setForeground(Color.RED); 
                        getContentPane().add(TextError);  
                        
                    }
                    

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
    }
    
    public VistaAfegirHidato (final CtVista CVista){
        setTitol("Afegir Hidato");
        
        Titol t = new Titol("Afegir Hidato", 50,30,400,60);       
        getContentPane().add(t);    
        
        
    }
}