package TallerHerencia;

import java.util.ArrayList;

public class Empresa {

    private ArrayList<EmpleadoPorHoras> empleadoPorHora;
    private ArrayList<EmpleadoPorSueldo> empleadoPorSueldo;
    private ArrayList<EmpleadoGerente> empleadoGerente;

    public Empresa() {
        this.empleadoPorHora = new ArrayList<EmpleadoPorHoras>();
        this.empleadoPorSueldo = new ArrayList<EmpleadoPorSueldo>();
        this.empleadoGerente = new ArrayList<EmpleadoGerente>();
    }

    public ArrayList<EmpleadoPorHoras> getEmpleadoPorHora() {
        return empleadoPorHora;
    }

    public ArrayList<EmpleadoPorSueldo> getEmpleadoPorSueldo() {
        return empleadoPorSueldo;
    }

    public ArrayList<EmpleadoGerente> getEmpleadoGerente() {
        return empleadoGerente;
    }

    public boolean agregarEmpleadoPorHoras(String cedula, String nombre, String apellido, String numeroSeguroSocial, double salarioBase, double solidaridad, double retencionFuente, double horasTrabajadas, double tarifaPorHora) {

        EmpleadoPorHoras nuevoEmpleado = new EmpleadoPorHoras(cedula, nombre, apellido,
                numeroSeguroSocial, salarioBase,
                solidaridad, retencionFuente,
                horasTrabajadas, tarifaPorHora);

        empleadoPorHora.add(nuevoEmpleado); // Agrega a la lista de empleados por hora
        return true;
    }

    public boolean agregarEmpleadoPorSueldo(String cedula, String nombre, String apellido, String numeroSeguroSocial, double salarioBase, double solidaridad, double retencionFuente, double sueldoMensual, double salud, double pension) {
        EmpleadoPorSueldo nuevoEmpleado = new EmpleadoPorSueldo(cedula, nombre, apellido, numeroSeguroSocial, salarioBase, solidaridad, retencionFuente, sueldoMensual, salud, pension);
        empleadoPorSueldo.add(nuevoEmpleado); // Agrega a la lista de empleados por sueldo
        return true;
    }

    public boolean agregarEmpleadoGerente(String cedula, String nombre, String apellido,
            String numeroSeguroSocial, double salarioBase,
            double solidaridad, double retencionFuente,
            double sueldoMensual, double salud,
            double pension, double bonoMensual,
            double impuestoRiqueza) {
        EmpleadoGerente nuevoGerente = new EmpleadoGerente(cedula, nombre, apellido,
                numeroSeguroSocial, salarioBase,
                solidaridad, retencionFuente,
                sueldoMensual, salud, pension,
                bonoMensual, impuestoRiqueza);

        empleadoGerente.add(nuevoGerente); // Agrega a la lista de empleados gerentes
        return true;
    }

    public String[] listarInformacionPorHoras() {
        int count = 0;
        for (int i = 0; i < empleadoPorHora.size(); i++) {
            if (empleadoPorHora.get(i) != null) {
                count++;
            }
        }
        String[] resultado = new String[count];
        int j = 0;

        for (int i = 0; i < empleadoPorHora.size(); i++) {
            if (empleadoPorHora.get(i) != null) {
                resultado[j] = empleadoPorHora.get(i).toPrint();
                j++;
            }
        }
        return resultado;
    }

    public String[] listarInformacionPorSueldo() {
        int count = 0;
        for (int i = 0; i < empleadoPorSueldo.size(); i++) {
            if (empleadoPorSueldo.get(i) != null) {
                count++;
            }
        }
        String[] resultado = new String[count];
        int j = 0;

        for (int i = 0; i < empleadoPorSueldo.size(); i++) {
            if (empleadoPorSueldo.get(i) != null) {
                resultado[j] = empleadoPorSueldo.get(i).toPrint();
                j++;
            }
        }
        return resultado;
    }

    public String[] listarInformacionGerentes() {
        int count = 0;
        for (int i = 0; i < empleadoGerente.size(); i++) {
            if (empleadoGerente.get(i) != null) {
                count++;
            }
        }
        String[] resultado = new String[count];
        int j = 0;

        for (int i = 0; i < empleadoGerente.size(); i++) {
            if (empleadoGerente.get(i) != null) {
                resultado[j] = empleadoGerente.get(i).toPrint();
                j++;
            }
        }
        return resultado;
    }
     public double calcularSalarioBasePorHoras() {
        double totalSalario = 0.0;

        for (EmpleadoPorHoras empleado : empleadoPorHora) {
            totalSalario += empleado.calcularSalarioDevengado(); // Calcula el salario devengado de cada empleado
        }

        return totalSalario; 
    }
     public double calcularSalarioBaseGerente() {
        double totalSalario = 0.0;

        for (EmpleadoPorHoras empleado : empleadoPorHora) {
            totalSalario += empleado.calcularSalarioDevengado(); // Calcula el salario devengado de cada empleado
        }

        return totalSalario; 
    }

}
