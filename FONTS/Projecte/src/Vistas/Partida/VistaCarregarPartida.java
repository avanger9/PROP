package Vistas.Partida;

import Vistas.CtVista;
import Vistas.ElementsInteractius.Botons;
import Vistas.ElementsInteractius.Titol;
import Vistas.VistaGenerica;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Administrador
 */
public class VistaCarregarPartida extends VistaGenerica{
    
    public VistaCarregarPartida (final CtVista CVista) {
       
        setTitol("Carregar Partida");
       
        Titol t = new Titol("Carregar Partida", 50,30,450,60);       
        getContentPane().add(t);    
       
        // Formulari Carregar Partida
        JLabel l1 = new JLabel(); 
        l1.setBounds(150, 200, 300, 35);
        l1.setText("Selecciona tu Hidato : ");
        l1.setFont(new Font("Serif", Font.BOLD, 28));
        l1.setForeground(Color.white);                   
        getContentPane().add(l1);  
        
        String[] hidatosPartides = { "Hidato1", "Hidato2", "Hidato3", "Hidato4" };
        JComboBox jcb = new JComboBox(hidatosPartides);
        jcb.setSelectedIndex(0);
        jcb.setBounds(450, 200, 200, 35);
        jcb.setForeground(new Color(95,160,0));        
        jcb.setBackground(Color.WHITE);
        jcb.setFont(new Font("Arial", Font.BOLD, 14));        
        getContentPane().add(jcb);               
        
        // Botó "Enrere" 
        Botons b1 = new Botons("Enrere",80,420,220,70);
        b1.setBackground(new Color(187,222,48));
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // CVista.ActivarVMenuPrincipal();
                //Cerramos la principal
                Sortir();
            }
        });           
        getContentPane().add(b1);        
        
        // Botó "Carregar" d'accés al joc de la partida carregada  
        Botons b2 = new Botons("Carregar",500,420,220,70);
        b2.setBackground(new Color(0,0,0));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // CVista.ActivarVJocPartida();
                //Cerramos la principal
                Sortir();
            }
        });        
        getContentPane().add(b2);                      
    }    
}