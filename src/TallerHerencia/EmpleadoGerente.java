package TallerHerencia;

public class EmpleadoGerente extends EmpleadoPorSueldo {

    protected double bonoMensual;
    protected double impuestoRiqueza;

    public EmpleadoGerente(String cedula, String nombre, String apellido, String numeroSeguroSocial, double salarioBase, double solidaridad, double retencionFuente, double sueldoMensual, double salud, double pension, double bonoMensual, double impuestoRiqueza) {
        super(cedula, nombre, apellido, numeroSeguroSocial, salarioBase, solidaridad, retencionFuente, sueldoMensual, salud, pension);
        this.bonoMensual = bonoMensual;
        this.impuestoRiqueza = impuestoRiqueza;

    }

    public double getBonoMensual() {
        return bonoMensual;
    }

    public double getImpuestoRiqueza() {
        return impuestoRiqueza;
    }

    @Override
    public double calcularSalarioDevengado() {
        // Salario devengado es el sueldo mensual más el bono mensual
        return sueldoMensual + bonoMensual;
    }

    @Override
    public double calcularDeducciones() {
        // Las deducciones incluyen salud, pensión, retención de fuente y el impuesto sobre la riqueza
        double deduccionesFijas = salud + pension;
        double deduccionesAdicionales = (calcularSalarioDevengado() * retencionFuente) + solidaridad; // Retención de fuente y solidaridad
        double impuestoTotal = calcularSalarioDevengado() * (impuestoRiqueza / 100); // Calcula el impuesto sobre la riqueza
        return deduccionesFijas + deduccionesAdicionales + impuestoTotal; // Suma todas las deducciones
    }

    @Override
    public double calcularSalarioNeto() {
        // Calcula el salario neto utilizando el salario devengado y las deducciones
        double salarioDevengado = calcularSalarioDevengado();
        double deducciones = calcularDeducciones(); // Obtiene las deducciones
        return salarioDevengado - deducciones; // Salario neto
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
                + "Sueldo Mensual: " + this.sueldoMensual + "\n"
                + "Salud: " + this.salud + "%\n"
                + "Pensión: " + this.pension + "%\n"
                + "Bono Mensual: " + this.bonoMensual + "\n"
                + "Impuesto a la Riqueza: " + this.impuestoRiqueza + "%\n";
    }

}
