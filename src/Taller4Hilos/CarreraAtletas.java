/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Taller4Hilos;

/**
 *
 * @author hunte
 */
public class CarreraAtletas {
    public static void main(String[] args) {
        // Crear 5 atletas
        Thread[] hilos = new Thread[5];
        String[] nombres = {"Atleta 1", "Atleta 2", "Atleta 3", "Atleta 4", "Atleta 5"};

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Atleta(nombres[i]));
        }

        // Iniciar hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Esperar a que los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("La carrera fue interrumpida.");
            }
        }

        System.out.println("¡Carrera finalizada!");
    }
}