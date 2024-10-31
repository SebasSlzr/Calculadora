
package TallerHerencia;

public class Empleado {
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String numeroSeguroSocial;
    protected double salarioBase;
    protected double solidaridad;
    protected double retencionFuente;
    
    public Empleado(String cedula, String nombre, String apellido, String numeroSeguroSocial, double salarioBase, double solidaridad, double retencionFuente ) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroSeguroSocial = numeroSeguroSocial;
        this.salarioBase = salarioBase;
        this.solidaridad = solidaridad;
        this.retencionFuente = retencionFuente;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
    public double calcularSalarioDevengado() {
        return salarioBase;
    }
    public double calcularSalarioNeto() {
        double salarioDevengado = calcularSalarioDevengado();
        double deducciones = salarioDevengado * retencionFuente + solidaridad;
        return salarioDevengado - deducciones;
    }
    public double calcularDeducciones() {

        double deducciones = (salarioBase * retencionFuente / 100) + solidaridad;
        return deducciones;
    }
    public String toPrint() {
    return "Cedula: " + this.cedula + "\n" +
           "Nombre: " + this.nombre + "\n" +
           "Apellido: " + this.apellido + "\n" +
           "Número de Seguro Social: " + this.numeroSeguroSocial + "\n" +
           "Retención en la fuente: " + this.retencionFuente + "%\n" +
           "Salario Base: " + this.salarioBase + "\n" +
           "Solidaridad: " + this.solidaridad + "\n";
}

    
    
}
