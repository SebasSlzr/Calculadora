/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora.gui;
import calculadora.operaciones.Calculadora;


import javax.swing.JOptionPane;

public class Menu {
     public static void main(String[] args) {
        /*JFrame ventana = new JFrame("Mi primera ventana");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setLayout(null);
        
        JLabel etiqueta = new JLabel("Hola Mundo!", SwingConstants.CENTER);
        etiqueta.setBounds(350,10,150,50);
        ventana.add(etiqueta);
        
        ventana.setVisible(true);*/
        
///////////////////////////////////////////////////////////////////////////////////

        /*JOptionPane.showInternalMessageDialog(null, "Bienvenidos a mi calculadora");
        String nombre = JOptionPane.showInputDialog("Ingrese Su Nombre:");
        System.out.println(nombre);
        JOptionPane.showMessageDialog(null, "Hola: "+ nombre + " Estas en la calculadorra");
        
        int opcion = JOptionPane.showConfirmDialog(null, "Desea continuar?");
        if(opcion == JOptionPane.YES_OPTION){
            System.out.println("El usuario dijo que si continua");
        }
        if(opcion == JOptionPane.NO_OPTION){
            System.out.println("El usuario dijo que no continua");
        }
        if(opcion == JOptionPane.CANCEL_OPTION){
            System.out.println("El usuario decidio cancelar la operacion");
        }*/
        
    Calculadora calculadora = new Calculadora();
         
        String opcion = JOptionPane.showInputDialog("1. suma, 2. restar, 3. multiplicar, 4. dividir");
        if(opcion.equals("1")){
            
            double numero1 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 1: "));
            double numero2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 2: "));
            double resultado = calculadora.sumar(numero1, numero2);
            JOptionPane.showMessageDialog(null, "Resultado: "+ resultado);
            
        }
        if(opcion.equals("2")){
            double numero1 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 1: "));
            double numero2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 2: "));
            double resultado = calculadora.restar(numero1, numero2);
            JOptionPane.showMessageDialog(null, "Resultado: "+ resultado);
        }
        if(opcion.equals("3")){
            double numero1 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 1: "));
            double numero2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 2: "));
            double resultado = calculadora.multiplicar(numero1, numero2);
            JOptionPane.showMessageDialog(null, "Resultado: "+ resultado);
        }
        if(opcion.equals("4")){
            double numero1 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 1: "));
            double numero2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 2: "));
            double resultado = calculadora.dividir(numero1, numero2);
            JOptionPane.showMessageDialog(null, "Resultado: "+ resultado);
        }
    
        
    }
    
}
