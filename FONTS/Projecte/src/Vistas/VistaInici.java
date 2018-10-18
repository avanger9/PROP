/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.CtVista;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
//import static javafx.scene.paint.Color.color;

/**
 *
 * @author elmer.garcia
 */
public class VistaInici extends VistaGenerica {
        /**
     * Creates new form VistaInici (Vista Principal)
     */
    public VistaInici(final CtVista CVista){ 
        setTitol("Inici");       
        contenidorPanel.setLayout(null);
        
        // Títol
        Titol t = new Titol("Your Game Hidato", 162,75,500,87);       
        getContentPane().add(t);
        
        // Imatge de l'aplicació
        JLabel l1 = new JLabel();    
        l1.setBounds(325, 100, 245, 250);
        String path = "./hidato_image.png";  
        URL url = this.getClass().getResource(path);  
        ImageIcon icon = new ImageIcon(url);  
        l1.setIcon(icon);        
        getContentPane().add(l1);      
        
        // Botó "Entrar" 
        Botons b1 = new Botons("Entrar",110,320,220,70);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    CVista.ActivarVEscollirUsuari();       
                    System.out.println("Entra alli:VistaInici");
                } catch (Exception ex) {
                    System.out.println(ex.getClass());
                    System.out.println("Entra aqui:VIstaInici");
                }                  
               
                
                //Cerramos la principal
                Sortir();
            }
        });          
        getContentPane().add(b1);        
        
        // Botó Crear Usuari        
        Botons b2 = new Botons("Crear Usuari",420,320,250,70);
        b2.setAlignmentX((float) 1.5);
        b2.setAlignmentY((float) 0.0); 
        b2.setLocation(420, 320);
        b2.setBackground(new Color(0,0,0));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CVista.ActivarVCrearUsuari();
                //Cerramos la principal
                Sortir();
            }
        });        
        getContentPane().add(b2);
                
        // Botó "Sortir"
        Botons b3 = new Botons("Sortir",315,425,200,70);
        b3.setBackground(Color.WHITE);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                               
                Sortir();
            }
        });        
        getContentPane().add(b3);
    }
}