/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Menu;

import Persistencia.AdjacenciaNoExisteixException;
import Persistencia.CelaNoExisteixException;
import Persistencia.FileAlreadyExistsException;
import Persistencia.FileNotExistsException;
import Vistas.CtVista;
import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Administrador
 */
public class VistaMenuPrincipal extends VistaGenerica {
    
    public void MostrarNomUsuari(final CtVista CVista, String s) {  
        JTextPane txtError = new JTextPane();
        txtError.setBounds(15, 190, 210, 120);                
        txtError.setText("Benvingut,\n"+s+"!");
        txtError.setFont(new Font("Nirmala UI", Font.BOLD, 40));		
        //txtError.setOpaque(false);
        txtError.setAlignmentX(CENTER_ALIGNMENT);
	txtError.setAlignmentY(CENTER_ALIGNMENT);
        txtError.setForeground(new Color(95,160,0));
        getContentPane().add(txtError);
        
        // Botó "Jugar" 
        Botons b1 = new Botons("Jugar",240,120,310,50);
        b1.setBackground(new Color(187,222,48));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 CVista.ActivarVTipusPartida(s);
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b1);
        
        // Botó "Carregar Partida" 
        Botons b2 = new Botons("Carregar Partida",240,175,310,50);
        b2.setBackground(new Color(187,222,48));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 CVista.ActivarVCarregarPartida();
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b2);
        
        // Botó "Afegir Hidato" 
        Botons b3 = new Botons("Afegir Hidato",240,230,310,50);
        b3.setBackground(new Color(187,222,48));
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CVista.ActivarVAfegirTipusHidato(s);
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b3);

        // Botó "Ranking" 
        Botons b4 = new Botons("Ranking",240,285,310,50);
        b4.setBackground(new Color(187,222,48));
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    CVista.ActivarVMostrarRanking(s);
                } catch (IOException ex) {
                    Logger.getLogger(VistaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotExistsException ex) {
                    Logger.getLogger(VistaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileAlreadyExistsException ex) {
                    Logger.getLogger(VistaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AdjacenciaNoExisteixException ex) {
                    Logger.getLogger(VistaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CelaNoExisteixException ex) {
                    Logger.getLogger(VistaMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b4);

        // Botó "Enrere" 
        Botons b5 = new Botons("Tancar Sessió",240,340,310,50);
        b5.setBackground(new Color(187,222,48));
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 CVista.ActivarVVistaInici();
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b5);
        
    }
    
    public VistaMenuPrincipal (final CtVista CVista) {
       
        setTitol("Menu Principal");
        
        Titol t = new Titol("Menu Principal", 50,30,400,60);       
        getContentPane().add(t);                    
                                   
        
        
        
        // Botó de sortida de l'aplicació
        Botons b6 = new Botons("Salir",550,420,220,70);
        b6.setBackground(new Color(0,0,0));
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Cerramos la principal
                Sortir();
            }
        });        
        getContentPane().add(b6);                             
    }
}