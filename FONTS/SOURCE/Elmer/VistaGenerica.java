/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author elmer.garcia
 */
public class VistaGenerica extends javax.swing.JFrame {
        protected JPanel contenidorPanel;
	protected static String Titol ="Hidato Game - ";
    /**
     * Creates new form VistaGenerica
     */
    public VistaGenerica() {
        // Configuració de la nostra vista
        super(Titol);
        setSize(800,550);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Atributs semblants a totes les vistes
        contenidorPanel = new JPanel();
        contenidorPanel.setLayout(null);
        contenidorPanel.setBackground(new Color(95,160,0));
        contenidorPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contenidorPanel);
    }
    
    // Afegim un titol a la nostra vista JFrame
    public void setTitol(String nouTitol) {
            super.setTitle(Titol+nouTitol);
    }
    
    // Tanca la visibilitat d'una vista determinada     
    public void Sortir(){
           setVisible(false);
    }    
    
}
