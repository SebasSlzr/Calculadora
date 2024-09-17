/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejemplo_JLabel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author hunte
 */
public class EjemploJLabel {
    
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Mi primera ventana");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setLayout(null);
        
        JLabel etiqueta = new JLabel("Mi calculadora");
        etiqueta.setBounds(350,10,150,50);
        ventana.add(etiqueta);
        ventana.setVisible(true);
        
        /*String informacionJLabel = etiqueta.getText();
        System.out.println(informacionJLabel);*/
        
        //EJEMPLO DE CREACION JLABEL, TEXTFIELD, BUTTON, COMBOBOX
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setText("Ingrese el nombre:");
        etiqueta2.setBounds(200, 100, 150, 30);
        ventana.add(etiqueta2);
        
        JTextField campo1 = new JTextField();
        campo1.setBounds(350, 100, 150, 30);
        campo1.setText("Sebastian Salazar");
        ventana.add(campo1);
        
        JButton boton1 = new JButton("AYUDA");
        boton1.setBounds(350, 150, 150, 30);
        ventana.add(boton1);
        
        String[] opciones = new String[4];
        opciones[0]= "SUMAR";
        opciones[1]= "RESTAR";
        opciones[2]= "MULTIPLICAR";
        opciones[3]= "DIVIDIR";
        
        JComboBox combo1 = new JComboBox(opciones);
        combo1.setBounds(300, 200, 150, 30);
        combo1.setSelectedIndex(2);
        ventana.add(combo1);
        
        
    }
}
