/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CalculadoraUAM;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalculadoraUAM {
    
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Calculadora UAM");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setLayout(null);
        
        JLabel etiqueta = new JLabel("Calculadora-UAM");
        etiqueta.setBounds(330,10,150,50);
        ventana.add(etiqueta);
        
        
        JLabel etiquetaUsuario = new JLabel();
        etiquetaUsuario.setText("Usuario:");
        etiquetaUsuario.setBounds(290, 50, 150, 30);
        ventana.add(etiquetaUsuario);
        
        JTextField campo0 = new JTextField();
        campo0.setBounds(350, 50, 150, 30);
        campo0.setText("Sebastian Salazar");
        ventana.add(campo0);
        
        
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setText("N 1");
        etiqueta2.setBounds(150, 100, 150, 30);
        ventana.add(etiqueta2);
        
        JLabel etiqueta3 = new JLabel();
        etiqueta3.setText("N 2");
        etiqueta3.setBounds(620, 100, 150, 30);
        ventana.add(etiqueta3);
        
        JTextField campo1 = new JTextField();
        campo1.setBounds(90, 150, 150, 30);
        ventana.add(campo1);
        JTextField campo2 = new JTextField();
        campo2.setBounds(550, 150, 150, 30);
        ventana.add(campo2);
        
        String[] opciones = new String[4];
            opciones[0]= "SUMAR";
            opciones[1]= "RESTAR";
            opciones[2]= "MULTIPLICAR";
            opciones[3]= "DIVIDIR";
            
        JComboBox combo1 = new JComboBox(opciones);
        combo1.setBounds(310, 200, 150, 30);
        combo1.setSelectedIndex(0);
        ventana.add(combo1);
       
        JButton boton1 = new JButton("1");
        boton1.setBounds(250, 250, 70, 30);
        ventana.add(boton1);
        boton1.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero1 = "1";
            /*String datoNumero = campo1.getText();
            String acumulado = datoNumero+numero1;
            campo1.setText(acumulado);*/
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero1);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero1);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        
        
        JButton boton2 = new JButton("2");
        boton2.setBounds(350, 250, 70, 30);
        ventana.add(boton2);
        boton2.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero2 = "2";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero2);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero2);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton3 = new JButton("3");
        boton3.setBounds(450, 250, 70, 30);
        ventana.add(boton3);
        boton3.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero3 = "3";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero3);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero3);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton4 = new JButton("4");
        boton4.setBounds(250, 300, 70, 30);
        ventana.add(boton4);
        boton4.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero4 = "4";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero4);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero4);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton5 = new JButton("5");
        boton5.setBounds(350, 300, 70, 30);
        ventana.add(boton5);
        boton5.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero5 = "5";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero5);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero5);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton6 = new JButton("6");
        boton6.setBounds(450, 300, 70, 30);
        ventana.add(boton6);
        boton6.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero6 = "6";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero6);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero6);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton7 = new JButton("7");
        boton7.setBounds(250, 350, 70, 30);
        ventana.add(boton7);
        boton7.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero7 = "7";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero7);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero7);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton8 = new JButton("8");
        boton8.setBounds(350, 350, 70, 30);
        ventana.add(boton8);
        boton8.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero8 = "8";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero8);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero8);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton9 = new JButton("9");
        boton9.setBounds(450, 350, 70, 30);
        ventana.add(boton9);
        boton9.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero9 = "9";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero9);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero9);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        JButton boton0 = new JButton("0");
        boton0.setBounds(350, 400, 70, 30);
        ventana.add(boton0);
        boton0.addMouseListener(new MouseAdapter() { 
        public void mouseClicked (MouseEvent e) {
            String numero0 = "0";
            String primerNum = campo1.getText();
            String segundoNum = campo2.getText();
            if(primerNum.equals("")){
                campo1.setText(numero0);
            }
            else if(segundoNum.equals("")){
                campo2.setText(numero0);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede ingresar mas numeros", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });
        
        JButton igual = new JButton("=");
        igual.setBounds(310, 450, 150, 30);
        ventana.add(igual);
        
        
        
        igual.addMouseListener(new MouseAdapter() {
            // Acción a realizar cuando se hace clic 
            @Override
            public void mouseClicked(MouseEvent e) {
                double Numero1 = Integer.parseInt(campo1.getText());
                double Numero2 = Integer.parseInt(campo2.getText());

                String operaciones = combo1.getSelectedItem().toString();
                
                double resultado =0;
                
                switch(operaciones){
                    case "SUMAR":
                        resultado = Numero1 + Numero2;
                        break;
                    case "RESTAR":
                        resultado = Numero1 - Numero2;
                        break;
                    case "MULTIPLICAR":
                        resultado = Numero1 * Numero2;
                        break;
                      
                    case "DIVIDIR":
                        if(Numero2 == 0){
                            JOptionPane.showMessageDialog(null, "Operacion no valida (No se puede dividir entre 0)", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                        resultado = Numero1 / Numero2;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Operación no válida", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                        
                }

                JOptionPane.showMessageDialog(null, "El resultado es: " + resultado, "INFO", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        
        

        ventana.setVisible(true);
    }
}

    
