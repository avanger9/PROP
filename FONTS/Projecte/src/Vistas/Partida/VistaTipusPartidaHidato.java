/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Partida;

import Domini.GetNumException;
import Vistas.CtVista;
import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Administrador
 */
public class VistaTipusPartidaHidato extends VistaGenerica{
    
    public void MostrarTipusPartidaHidato(final CtVista CVista, String s) { 
        // Formulari Tipus Partida Hidato
        JLabel l1 = new JLabel(); 
        l1.setBounds(150, 120, 300, 35);
        l1.setText("Tipus Cel-la : ");
        l1.setHorizontalAlignment(l1.RIGHT);
        l1.setFont(new Font("Serif", Font.BOLD, 28));
        l1.setForeground(Color.white);                   
        getContentPane().add(l1);  
        
        String[] tipusCella = { "Quadrat", "Hexàgon", "Triangle" };
        JComboBox jcb1 = new JComboBox(tipusCella);
        jcb1.setSelectedIndex(0);
        jcb1.setBounds(450, 120, 200, 35);
        jcb1.setForeground(new Color(95,160,0));        
        jcb1.setBackground(Color.WHITE);
        jcb1.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(jcb1);
        
        JLabel l2 = new JLabel(); 
        l2.setBounds(150, 170, 300, 35);
        l2.setText("Tipus Adjacència : ");
        l2.setHorizontalAlignment(l2.RIGHT);
        l2.setFont(new Font("Serif", Font.BOLD, 28));
        l2.setForeground(Color.white);
        getContentPane().add(l2);
        
        String[] tipusAdjacencia = { "Costats", "Costats + Angles " };
        JComboBox jcb2 = new JComboBox(tipusAdjacencia);
        jcb2.setSelectedIndex(0);
        jcb2.setBounds(450, 170, 200, 35);
        jcb2.setForeground(new Color(95,160,0));        
        jcb2.setBackground(Color.WHITE);
        jcb2.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(jcb2);

        JLabel l3 = new JLabel(); 
        l3.setBounds(150, 220, 300, 35);
        l3.setText("Nombre de files: ");
        l3.setHorizontalAlignment(l3.RIGHT);
        l3.setFont(new Font("Serif", Font.BOLD, 28));
        l3.setForeground(Color.white);                   
        getContentPane().add(l3);  
        
        JTextField tf1 = new JTextField();
        tf1.setBounds(450, 220, 200, 35);   //7055         
        getContentPane().add(tf1);
        tf1.setColumns(10); 
        
        JLabel l4 = new JLabel(); 
        l4.setBounds(150, 270, 300, 35);
        l4.setText("Nombre de columnes : ");
        l4.setHorizontalAlignment(l4.RIGHT);
        l4.setFont(new Font("Serif", Font.BOLD, 28));
        l4.setForeground(Color.white);                   
        getContentPane().add(l4);  
        
        JTextField tf2 = new JTextField();
        tf2.setBounds(450, 270, 200, 35);   //7055 
        getContentPane().add(tf2);
        tf2.setColumns(10);
        
        
        JLabel l5 = new JLabel(); 
        l5.setBounds(150, 320, 300, 35);
        l5.setText("Dificultat : ");
        l5.setHorizontalAlignment(l5.RIGHT);
        l5.setFont(new Font("Serif", Font.BOLD, 28));
        l5.setForeground(Color.white);                   
        getContentPane().add(l5);
        
        String[] tipusDificultat = { "Fàcil", "Intermedi", "Difícil", "Hardcore" };
        JComboBox jcb3 = new JComboBox(tipusDificultat);
        jcb3.setSelectedIndex(0);
        jcb3.setBounds(450, 320, 200, 35);
        jcb3.setForeground(new Color(95,160,0));        
        jcb3.setBackground(Color.WHITE);
        jcb3.setFont(new Font("Arial", Font.BOLD, 14));
        getContentPane().add(jcb3);
        
        // Botó "Enrere" 
        Botons b1 = new Botons("Enrere",80,420,220,70);
        b1.setBackground(new Color(187,222,48));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CVista.ActivarVTipusPartida(s);
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b1);
        
        
        // Botó "Jugar" d'accés al joc   
        Botons b2 = new Botons("Jugar",500,420,220,70);
        b2.setBackground(new Color(0,0,0));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indexCella = jcb1.getSelectedIndex();
                int indexAdjacencia = jcb2.getSelectedIndex();
                String nfilas = tf1.getText();
                String ncolumnes = tf2.getText();
                int dif;
                dif = jcb3.getSelectedIndex()+1;
                if (!nfilas.equals("") && !ncolumnes.equals("")) {                    
                    String aux[] = new String[4];
                    aux = CVista.FormatejarLinea(indexCella,indexAdjacencia,nfilas,ncolumnes);
                    try {
                        tf1.setText("");
                        tf2.setText("");
                        long time_start;
                        time_start = System.currentTimeMillis();
                        CVista.ActivarVJocPartida(s ,aux, dif, (int) time_start);
                        
                    } catch (GetNumException ex) {
                        Logger.getLogger(VistaTipusPartidaHidato.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
              
                //Cerramos la principal
                Sortir();
            }
        });        
        getContentPane().add(b2);          
        
    }
    
    public VistaTipusPartidaHidato (final CtVista CVista) {
        setTitol("Tipus Partida Hidato");
       
        Titol t = new Titol("Tipus Partida Hidato", 50,30,550,60);       
        getContentPane().add(t);    
       
        
    }
}