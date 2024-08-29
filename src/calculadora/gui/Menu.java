/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora.gui;
import calculadora.operaciones.Calculadora;

 import java.util.Scanner;

public class Menu {
    private Scanner teclado;

 
    
    
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        Scanner entrada = new Scanner(System.in);
        String opcion = "";
        System.out.println("1. suma, 2. restar, 3. multiplicar, 4. dividir, 5. limpiar, 6. salir");
        opcion = entrada.nextLine();  
        double acomulado = 0;
        while(!opcion.equals("6")){
         
            
        if(opcion.equals("1")){

            System.out.println("Ingrese el numero 1: ");
            double numero1 = entrada.nextDouble();
            System.out.println("Ingrese el numero 2: ");
            double numero2 = entrada.nextDouble();
            double resultado = acomulado + calculadora.sumar(numero1, numero2);
            acomulado = resultado;
            System.out.println(resultado);
        }
        if(opcion.equals("2")){
            System.out.println("Ingrese el numero 1: ");
            double numero1 = entrada.nextDouble();
            System.out.println("Ingrese el numero 2: ");
            double numero2 = entrada.nextDouble();
            double resultado = acomulado + calculadora.restar(numero1, numero2);
            acomulado = resultado;
            System.out.println(resultado);
        }
        if(opcion.equals("3")){
            System.out.println("Ingrese el numero 1: ");
            double numero1 = entrada.nextDouble();
            System.out.println("Ingrese el numero 2: ");
            double numero2 = entrada.nextDouble();
            double resultado = acomulado + calculadora.multiplicar(numero1, numero2);
            acomulado = resultado;
            System.out.println(resultado);
        }
        if(opcion.equals("4")){
            System.out.println("Ingrese el numero 1: ");
            double numero1 = entrada.nextDouble();
            System.out.println("Ingrese el numero 2: ");
            double numero2 = entrada.nextDouble();
            double resultado = acomulado + calculadora.dividir(numero1, numero2);
            acomulado = resultado;
            System.out.println(resultado);
        }
     
        if(opcion.equals("5")){
            acomulado= 0;
        }
    }
        
        
        
    }
}
