package TallerHerencia;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class menu {
    

    private Empresa empresa;
    private Scanner teclado;

    public menu(Empresa empresa) {
        this.empresa = empresa;
        this.teclado = new Scanner(System.in);
    }

    public void mostrar() {
        JFrame ventana = new JFrame("Agregar empleados");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setLayout(null); 

        // Crear los JLabels del menú
        JLabel titulo = new JLabel("REGISTRO EMPLEADOS:");
        JButton opcion1 = new JButton("1. Nuevo empleado");
        JButton opcion2 = new JButton("2. Listar empleaod (Horas)");
        JButton opcion3 = new JButton("3. Listar empleados(Sueldo)");
        JButton opcion4 = new JButton("4. Listar gerentes");

        JButton opcionSalir = new JButton("s. Salir");
        

        titulo.setBounds(300, 20, 300, 30);
        opcion1.setBounds(200, 60, 350, 30);
        opcion2.setBounds(200, 100, 350, 30);
        opcion3.setBounds(200, 140, 350, 30);
        opcion4.setBounds(200, 180, 350, 30);
        opcionSalir.setBounds(250, 220, 250, 30);
        

        ventana.add(titulo);
        ventana.add(opcion1);
        ventana.add(opcion2);
        ventana.add(opcion3);
        ventana.add(opcion4);
        ventana.add(opcionSalir);
       

        // Añadir el ventana a la ventana
        opcion1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicitar información básica
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
                String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
                String cedula = JOptionPane.showInputDialog("Ingrese su cédula:");
                String numeroSeguroSocial = JOptionPane.showInputDialog("Ingrese el número de seguro social:");
                String categoria = JOptionPane.showInputDialog("Ingrese la categoría (Por Horas/Sueldo/Gerente):");
                
                String solidaridadStr = JOptionPane.showInputDialog("Ingrese la solidaridad:");
                String retencionFuenteStr = JOptionPane.showInputDialog("Ingrese la retención en la fuente (porcentaje):");

                
                double solidaridad = Double.parseDouble(solidaridadStr);
                double retencionFuente = Double.parseDouble(retencionFuenteStr);

                boolean agregadoCorrectamente = false;

                // Lógica según la categoría
                if ("Por Horas".equalsIgnoreCase(categoria)) {
                    // Pedir campos adicionales para empleado por horas
                    String horasTrabajadasStr = JOptionPane.showInputDialog("Ingrese las horas trabajadas:");
                    String tarifaPorHoraStr = JOptionPane.showInputDialog("Ingrese la tarifa por hora:");
                    double horasTrabajadas = Double.parseDouble(horasTrabajadasStr);
                    double tarifaPorHora = Double.parseDouble(tarifaPorHoraStr);
                    double salarioBase = tarifaPorHora*horasTrabajadas;

                    // Llamar a la función que agrega el empleado por horas
                    agregadoCorrectamente = empresa.agregarEmpleadoPorHoras(
                            cedula, nombre, apellido, numeroSeguroSocial, salarioBase, solidaridad, retencionFuente, horasTrabajadas, tarifaPorHora
                    );

                } else if ("Sueldo".equalsIgnoreCase(categoria)) {
                    // Pedir campos adicionales para empleado por sueldo
                    String sueldoMensualStr = JOptionPane.showInputDialog("Ingrese el sueldo mensual:");
                    String saludStr = JOptionPane.showInputDialog("Ingrese el porcentaje de salud:");
                    String pensionStr = JOptionPane.showInputDialog("Ingrese el porcentaje de pensión:");
                    double sueldoMensual = Double.parseDouble(sueldoMensualStr);
                    double salud = Double.parseDouble(saludStr);
                    double pension = Double.parseDouble(pensionStr);
                    

                    // Llamar a la función que agrega el empleado por sueldo
                    agregadoCorrectamente = empresa.agregarEmpleadoPorSueldo(
                            cedula, nombre, apellido, numeroSeguroSocial, sueldoMensual, solidaridad, retencionFuente, sueldoMensual, salud, pension
                    );

                } else if ("Gerente".equalsIgnoreCase(categoria)) {
                    // Pedir campos adicionales para empleado gerente
                    String sueldoMensualStr = JOptionPane.showInputDialog("Ingrese el sueldo mensual:");
                    String saludStr = JOptionPane.showInputDialog("Ingrese el porcentaje de salud:");
                    String pensionStr = JOptionPane.showInputDialog("Ingrese el porcentaje de pensión:");
                    String bonoMensualStr = JOptionPane.showInputDialog("Ingrese el bono mensual:");
                    String impuestoRiquezaStr = JOptionPane.showInputDialog("Ingrese el impuesto a la riqueza:");
                    double sueldoMensual = Double.parseDouble(sueldoMensualStr);
                    double salud = Double.parseDouble(saludStr);
                    double pension = Double.parseDouble(pensionStr);
                    double bonoMensual = Double.parseDouble(bonoMensualStr);
                    double impuestoRiqueza = Double.parseDouble(impuestoRiquezaStr);
                    double salarioBase = empresa.calcularSalarioBaseGerente();
                    

                    // Llamar a la función que agrega el empleado gerente
                    agregadoCorrectamente = empresa.agregarEmpleadoGerente(
                            cedula, nombre, apellido, numeroSeguroSocial, salarioBase, solidaridad, retencionFuente, sueldoMensual, salud, pension, bonoMensual, impuestoRiqueza
                    );
                } else {
                    JOptionPane.showMessageDialog(null, "Categoría desconocida.");
                    return; // Salir si la categoría no es válida
                }

                // Mostrar mensaje de confirmación
                if (agregadoCorrectamente) {
                    JOptionPane.showMessageDialog(null, "El empleado se agregó exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo agregar el empleado.");
                }
            }
        });

        opcion2.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        // Crear la ventana nueva
        JFrame ventanaLista = new JFrame("Lista de empleados por horas");
        ventanaLista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaLista.setSize(800, 600);
        ventanaLista.setLayout(new BorderLayout());

        String[] columnas = {"Cédula", "Nombre", "Apellido", "Horas Trabajadas", "Tarifa por Hora", "Salario Base"};
        List<EmpleadoPorHoras> empleadosPorHoras = empresa.getEmpleadoPorHora(); // Obtener la lista de empleados

        // Crear una matriz para almacenar los datos en filas
        String[][] datos = new String[empleadosPorHoras.size()][6];

        for (int i = 0; i < empleadosPorHoras.size(); i++) {
            EmpleadoPorHoras empleado = empleadosPorHoras.get(i);
            datos[i][0] = empleado.getCedula();
            datos[i][1] = empleado.getNombre();
            datos[i][2] = empleado.getApellido();
            datos[i][3] = String.valueOf(empleado.getHorasTrabajadas());
            datos[i][4] = String.valueOf(empleado.getTarifaPorHora());
            datos[i][5] = String.valueOf(empleado.getSalarioBase());
        }

        // Crear el JTable con las columnas y datos
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setFillsViewportHeight(true);

        // Agregar el JScrollPane a la ventana
        ventanaLista.add(scrollPane, BorderLayout.CENTER);

        // Hacer visible la ventana
        ventanaLista.setVisible(true);
    }
});


        opcion3.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        // Crear la ventana nueva
        JFrame ventanaLista = new JFrame("Lista de empleados por sueldo");
        ventanaLista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaLista.setSize(800, 600);
        ventanaLista.setLayout(new BorderLayout());

        String[] columnas = {"Cédula", "Nombre", "Apellido", "Sueldo Mensual", "Salud", "Pensión"};
        List<EmpleadoPorSueldo> empleadosPorSueldo = empresa.getEmpleadoPorSueldo(); // Obtener la lista de empleados

        // Crear una matriz para almacenar los datos en filas
        String[][] datos = new String[empleadosPorSueldo.size()][6];

        for (int i = 0; i < empleadosPorSueldo.size(); i++) {
            EmpleadoPorSueldo empleado = empleadosPorSueldo.get(i);
            datos[i][0] = empleado.getCedula();
            datos[i][1] = empleado.getNombre();
            datos[i][2] = empleado.getApellido();
            datos[i][3] = String.valueOf(empleado.getSueldoMensual());
            datos[i][4] = String.valueOf(empleado.getSalud());
            datos[i][5] = String.valueOf(empleado.getPension());
        }

        // Crear el JTable con las columnas y datos
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setFillsViewportHeight(true);

        // Agregar el JScrollPane a la ventana
        ventanaLista.add(scrollPane, BorderLayout.CENTER);

        // Hacer visible la ventana
        ventanaLista.setVisible(true);
    }
});


        opcion4.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        // Crear la ventana nueva
        JFrame ventanaLista = new JFrame("Lista de empleados gerentes");
        ventanaLista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaLista.setSize(800, 600);
        ventanaLista.setLayout(new BorderLayout());

        String[] columnas = {"Cédula", "Nombre", "Apellido", "Sueldo Mensual", "Salud", "Pensión", "Bono Mensual", "Impuesto a la Riqueza"};
        List<EmpleadoGerente> empleadosGerente = empresa.getEmpleadoGerente(); // Obtener la lista de empleados

        // Crear una matriz para almacenar los datos en filas
        String[][] datos = new String[empleadosGerente.size()][8];

        for (int i = 0; i < empleadosGerente.size(); i++) {
            EmpleadoGerente empleado = empleadosGerente.get(i);
            datos[i][0] = empleado.getCedula();
            datos[i][1] = empleado.getNombre();
            datos[i][2] = empleado.getApellido();
            datos[i][3] = String.valueOf(empleado.getSueldoMensual());
            datos[i][4] = String.valueOf(empleado.getSalud());
            datos[i][5] = String.valueOf(empleado.getPension());
            datos[i][6] = String.valueOf(empleado.getBonoMensual());
            datos[i][7] = String.valueOf(empleado.getImpuestoRiqueza());
        }

        // Crear el JTable con las columnas y datos
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setFillsViewportHeight(true);

        // Agregar el JScrollPane a la ventana
        ventanaLista.add(scrollPane, BorderLayout.CENTER);

        // Hacer visible la ventana
        ventanaLista.setVisible(true);
    }
});


        opcionSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir del programa
            }
        });
        ventana.setVisible(true);
    }

   

}
