/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.ElementsInteractius;

import java.awt.Color;
import java.awt.Font;
//import java.awt.SystemColor;
import javax.swing.JButton;
/**
 *
 * @author Administrador
 */
public class Botons extends JButton {
    
    // Característiques semblants dels botons a la nostra aplicació
    public Botons (String texto, int x, int y, int w, int h) {
        super(texto);
        this.setBackground(new Color(187,222,48));
        this.setForeground(new Color(107,175,1));
        this.setFont(new Font("Candara", Font.BOLD, 37));
        this.setBounds(x, y, w, h);
        this.setBorderPainted(false);
        this.setFocusPainted(false);     
    }
}
