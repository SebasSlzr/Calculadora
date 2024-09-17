/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplo_JLabel;
import javax.swing.*;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent;
/**
 *
 * @author hunte
 */
public class Eventos {
    
public static void main(String[] args) {
    JFrame ventana = new JFrame("Ejemplo MouseClicked"); 
    JButton boton = new JButton("Haz clic aquí");
// Crear el MouseListener y asociarlo al botón 
    boton.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
// Acción a realizar cuando se hace clic 
            JOptionPane.showMessageDialog(null, "¡Botón presionado!");
        }
    });
    
ventana.add(boton);
ventana.pack();
ventana.setVisible(true);

    }
}
