
package TallerHerencia;


public class Main {
    public static void main(String[] args) {
        // Crear varias empresas
        Empresa empresa1 = new Empresa();
        Empresa empresa2 = new Empresa();

        // Crear un menú para cada empresa y mostrarlo
        menu menu1 = new menu(empresa1);
        menu1.mostrar();

       
    }
    
}
