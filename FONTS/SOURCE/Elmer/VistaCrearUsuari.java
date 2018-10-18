/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Usuari;

import Persistencia.FileAlreadyExistsException;
import Persistencia.FileNotExistsException;
import Persistencia.UsuariJaExisteixException;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Administrador
 */
public class VistaCrearUsuari extends VistaGenerica {
    
    public VistaCrearUsuari (final CtVista CVista) {
       
        setTitol("Crear Usuari");
        
        Titol t = new Titol("Crear Usuari", 50,30,400,60);       
        getContentPane().add(t);    
        
        // Sub-contenidor 
        JPanel contenidor = new JPanel();
        contenidor.setBounds(50, 125, 700, 350);
        contenidor.setLayout(null);
        contenidor.setBackground(new Color(219,239,165));
        contenidor.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        
        // Formulari Crear Usuari
        JLabel l1 = new JLabel(); 
        l1.setBounds(200, 200, 200, 35);
        l1.setText("Nombre : ");
        l1.setFont(new Font("Serif", Font.BOLD, 28));
        l1.setForeground(Color.white);                   
        getContentPane().add(l1);  
           
	JTextField tf = new JTextField();
        tf.setBounds(400, 200, 200, 35);   //7055         
        getContentPane().add(tf);
        tf.setColumns(10);        
        
       
        JTextPane TextError = new JTextPane();
        // Botó d'accés a la sessió
        Botons b2 = new Botons("Ok, Entrar",500,380,220,70);
        b2.setBackground(new Color(0,0,0));
        b2.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent e) {
                String usuariSeleccionat = tf.getText();
                try {
                    boolean aux = CVista.usuariExistent(usuariSeleccionat);
                 if (!usuariSeleccionat.equals("")&& !aux) {                     
                        try {
                            CVista.crear_usuari(usuariSeleccionat);
                        } catch (IOException ex) {
                            Logger.getLogger(VistaCrearUsuari.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UsuariJaExisteixException ex) {
                            Logger.getLogger(VistaCrearUsuari.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileAlreadyExistsException ex) {
                            Logger.getLogger(VistaCrearUsuari.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        tf.setText("");
                        TextError.setText("");
                         CVista.ActivarVMenuPrincipal(usuariSeleccionat);                     
                         //Cerramos la principal
                         Sortir();
                    } else if (!usuariSeleccionat.equals("")&& aux) { 
                        tf.setText("");
                        TextError.setBounds(400, 240, 240, 61);                
                        TextError.setText("L'usuari ja existeix, prova de nou.");
                        TextError.setFont(new Font("Nirmala UI", Font.BOLD, 12));		
                        TextError.setOpaque(false);
                        TextError.setForeground(Color.RED);


                    }                    
                } catch (IOException ex) {
                    Logger.getLogger(VistaCrearUsuari.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotExistsException ex) {
                    Logger.getLogger(VistaCrearUsuari.class.getName()).log(Level.SEVERE, null, ex);
                }                                
            }
        });       
         getContentPane().add(TextError);
        getContentPane().add(b2);
        
      
        
        // Botó "Enrere" 
        Botons b1 = new Botons("Enrere",80,380,220,70);
        b1.setBackground(new Color(187,222,48));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 tf.setText("");
                 TextError.setText("");
                 CVista.ActivarVVistaInici();
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b1);                
        
        getContentPane().add(contenidor);          
    }
}
