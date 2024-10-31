package TallerHerencia;

public class EmpleadoPorHoras extends Empleado {

    protected double horasTrabajadas;
    protected double tarifaPorHora;

    public EmpleadoPorHoras(String cedula, String nombre, String apellido, String numeroSeguroSocial, double salarioBase, double solidaridad, double retencionFuente, double horasTrabajadas, double tarifaPorhora) {
        super(cedula, nombre, apellido, numeroSeguroSocial, salarioBase, solidaridad, retencionFuente);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorhora;

    }

    public double getSolidaridad() {
        return solidaridad;
    }

    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public double getRetencionFuente() {
        return retencionFuente;
    }

    @Override
    public double calcularSalarioDevengado() {
        return horasTrabajadas * tarifaPorHora;
    }

    @Override
    public String toPrint() {
        return "Cedula: " + this.cedula + "\n"
                + "Nombre: " + this.nombre + "\n"
                + "Apellido: " + this.apellido + "\n"
                + "Número de Seguro Social: " + this.numeroSeguroSocial + "\n"
                + "Retención en la fuente: " + this.retencionFuente + "%\n"
                + "Salario Base: " + this.salarioBase + "\n"
                + "Solidaridad: " + this.solidaridad + "\n"
                + "Horas trabajadas: " + this.horasTrabajadas + "\n"
                + "Tarifa por hora: " + this.tarifaPorHora + "\n";
    }

}
