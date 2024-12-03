/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Taller4Hilos;

import java.util.Random;

/**
 *
 * @author hunte
 */
class Atleta implements Runnable {
    private String nombre;
    private int velocidad; // Velocidad en kms por segundo
    private int distanciaRecorrida;
    private static final int META = 10000; // Meta en kilómetros

    public Atleta(String nombre) {
        this.nombre = nombre;
        this.velocidad = new Random().nextInt(1000) + 1; // Velocidad random entre 1 y 1000
        this.distanciaRecorrida = 0;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (distanciaRecorrida < META) {
            distanciaRecorrida += velocidad;
            System.out.println(nombre + " ha recorrido: " + distanciaRecorrida + " kms (Velocidad: " + velocidad + " kms/s)");
            
            try {
                Thread.sleep(1000); // Esperar 1 segundo
            } catch (InterruptedException e) {
                System.out.println(nombre + " fue interrumpido!");
            }
        }
        System.out.println("¡" + nombre + " ha llegado a la meta!");
        System.out.println("-----------------------");
    }

    public boolean haTerminado() {
        return distanciaRecorrida >= META;
    }
}
