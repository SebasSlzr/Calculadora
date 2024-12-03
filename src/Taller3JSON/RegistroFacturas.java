/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Taller3JSON;

/**
 *
 * @author hunte
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RegistroFacturas extends JFrame {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField codigoCampo, nombreCampo, cantidadCampo, precioCampo, impuestoCampo, totalCampo;
    private List<Factura> listaFacturas;
    private int facturaSeleccionada = -1;
    private final String ARCHIVO_JSON = "facturas.json";

    public RegistroFacturas() {
        listaFacturas = cargarFacturasDesdeJSON();

        setTitle("Registro de Facturas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnas = {"Código", "Nombre", "Cantidad", "Precio", "Impuesto", "Total"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cargarFacturaSeleccionada();
            }
        });
        JScrollPane scrollTabla = new JScrollPane(tabla);
        add(scrollTabla, BorderLayout.CENTER);

        JPanel formularioPanel = crearFormularioPanel();
        add(formularioPanel, BorderLayout.WEST);

        cargarFacturasEnTabla();
        setVisible(true);
    }

    private JPanel crearFormularioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        codigoCampo = new JTextField(15);
        nombreCampo = new JTextField(15);
        cantidadCampo = new JTextField(15);
        precioCampo = new JTextField(15);
        impuestoCampo = new JTextField(15);
        totalCampo = new JTextField(15);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> guardarFactura());

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(e -> eliminarFactura());

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> limpiarFormulario());

        panel.add(new JLabel("Código:"));
        panel.add(codigoCampo);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreCampo);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadCampo);
        panel.add(new JLabel("Precio:"));
        panel.add(precioCampo);
        panel.add(new JLabel("Impuesto:"));
        panel.add(impuestoCampo);
        panel.add(new JLabel("Total:"));
        panel.add(totalCampo);
        panel.add(guardarButton);
        panel.add(eliminarButton);
        panel.add(cancelarButton);

        return panel;
    }

    private void limpiarFormulario() {
        codigoCampo.setText("");
        nombreCampo.setText("");
        cantidadCampo.setText("");
        precioCampo.setText("");
        impuestoCampo.setText("");
        totalCampo.setText("");
        facturaSeleccionada = -1;
    }

    private void guardarFactura() {
        String codigo = codigoCampo.getText();
        String nombre = nombreCampo.getText();
        String cantidad = cantidadCampo.getText();
        String precio = precioCampo.getText();
        String impuesto = impuestoCampo.getText();
        String total = totalCampo.getText();

        if (codigo.isEmpty() || nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty() || impuesto.isEmpty() || total.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        Factura factura = new Factura(codigo, nombre, cantidad, precio, impuesto, total);
        if (facturaSeleccionada == -1) {
            listaFacturas.add(factura);
            modeloTabla.addRow(new Object[]{codigo, nombre, cantidad, precio, impuesto, total});
        } else {
            listaFacturas.set(facturaSeleccionada, factura);
            modeloTabla.setValueAt(codigo, facturaSeleccionada, 0);
            modeloTabla.setValueAt(nombre, facturaSeleccionada, 1);
            modeloTabla.setValueAt(cantidad, facturaSeleccionada, 2);
            modeloTabla.setValueAt(precio, facturaSeleccionada, 3);
            modeloTabla.setValueAt(impuesto, facturaSeleccionada, 4);
            modeloTabla.setValueAt(total, facturaSeleccionada, 5);
        }

        guardarFacturasEnJSON();
        limpiarFormulario();
    }

    private void eliminarFactura() {
        if (facturaSeleccionada == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar esta factura?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listaFacturas.remove(facturaSeleccionada);
            modeloTabla.removeRow(facturaSeleccionada);
            guardarFacturasEnJSON();
            limpiarFormulario();
        }
    }

    private void cargarFacturaSeleccionada() {
        facturaSeleccionada = tabla.getSelectedRow();
        if (facturaSeleccionada != -1) {
            Factura factura = listaFacturas.get(facturaSeleccionada);
            codigoCampo.setText(factura.getCodigo());
            nombreCampo.setText(factura.getNombre());
            cantidadCampo.setText(factura.getCantidad());
            precioCampo.setText(factura.getPrecio());
            impuestoCampo.setText(factura.getImpuesto());
            totalCampo.setText(factura.getTotal());
        }
    }

    private List<Factura> cargarFacturasDesdeJSON() {
        try (FileReader reader = new FileReader(ARCHIVO_JSON)) {
            java.lang.reflect.Type listType = new TypeToken<List<Factura>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void guardarFacturasEnJSON() {
        try (FileWriter writer = new FileWriter(ARCHIVO_JSON)) {
            new Gson().toJson(listaFacturas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarFacturasEnTabla() {
        for (Factura factura : listaFacturas) {
            modeloTabla.addRow(new Object[]{
                    factura.getCodigo(),
                    factura.getNombre(),
                    factura.getCantidad(),
                    factura.getPrecio(),
                    factura.getImpuesto(),
                    factura.getTotal()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RegistroFacturas::new);
    }
}

class Factura {
    private String codigo;
    private String nombre;
    private String cantidad;
    private String precio;
    private String impuesto;
    private String total;

    public Factura(String codigo, String nombre, String cantidad, String precio, String impuesto, String total) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.impuesto = impuesto;
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public String getTotal() {
        return total;
    }
}

