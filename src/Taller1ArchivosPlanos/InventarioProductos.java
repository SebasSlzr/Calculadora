/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Taller1ArchivosPlanos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InventarioProductos extends JFrame {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField codigoCampo, nombreCampo, precioCampo, categoriaCampo;
    private List<Producto> listaProductos;
    private int productoSeleccionado = -1;
    private static final String ARCHIVO_PRODUCTOS = "productos.txt";

    public InventarioProductos() {
    listaProductos = new ArrayList<>();
    
    setTitle("Inventario de Productos");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   
    String[] columnas = {"Código", "Nombre", "Precio", "Categoría"};
    modeloTabla = new DefaultTableModel(columnas, 0);
    tabla = new JTable(modeloTabla);
    tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    tabla.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            cargarProducto();
        }
    });

    cargarProductosDesdeArchivo(); 

    JScrollPane scrollTabla = new JScrollPane(tabla);
    add(scrollTabla, BorderLayout.CENTER);
    JPanel formularioPanel = crearFormularioPanel();
    add(formularioPanel, BorderLayout.WEST);

    setVisible(true);
}

    private JPanel crearFormularioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        codigoCampo = new JTextField(15);
        nombreCampo = new JTextField(15);
        precioCampo = new JTextField(15);
        categoriaCampo = new JTextField(15);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> guardarProducto());

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(e -> eliminarProducto());

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> limpiarFormulario());

        panel.add(new JLabel("Código:"));
        panel.add(codigoCampo);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreCampo);
        panel.add(new JLabel("Precio:"));
        panel.add(precioCampo);
        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaCampo);
        panel.add(guardarButton);
        panel.add(eliminarButton);
        panel.add(cancelarButton);

        return panel;
    }

    private void limpiarFormulario() {
        codigoCampo.setText("");
        nombreCampo.setText("");
        precioCampo.setText("");
        categoriaCampo.setText("");
        productoSeleccionado = -1;
    }

    private void guardarProducto() {
        String codigo = codigoCampo.getText();
        String nombre = nombreCampo.getText();
        String precio = precioCampo.getText();
        String categoria = categoriaCampo.getText();

        if (codigo.isEmpty() || nombre.isEmpty() || precio.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        if (productoSeleccionado == -1) { // Nuevo producto
            Producto nuevoProducto = new Producto(codigo, nombre, precio, categoria);
            listaProductos.add(nuevoProducto);
            modeloTabla.addRow(new Object[]{codigo, nombre, precio, categoria});
        } else { // Editar producto existente
            Producto producto = listaProductos.get(productoSeleccionado);
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCategoria(categoria);

            modeloTabla.setValueAt(codigo, productoSeleccionado, 0);
            modeloTabla.setValueAt(nombre, productoSeleccionado, 1);
            modeloTabla.setValueAt(precio, productoSeleccionado, 2);
            modeloTabla.setValueAt(categoria, productoSeleccionado, 3);
        }

        guardarProductosEnArchivo();
        limpiarFormulario();
    }

    private void eliminarProducto() {
        if (productoSeleccionado == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listaProductos.remove(productoSeleccionado);
            modeloTabla.removeRow(productoSeleccionado);
            guardarProductosEnArchivo();
            limpiarFormulario();
        }
    }

    private void cargarProducto() {
        productoSeleccionado = tabla.getSelectedRow();
        if (productoSeleccionado != -1) {
            Producto producto = listaProductos.get(productoSeleccionado);
            codigoCampo.setText(producto.getCodigo());
            nombreCampo.setText(producto.getNombre());
            precioCampo.setText(producto.getPrecio());
            categoriaCampo.setText(producto.getCategoria());
        }
    }

    private void cargarProductosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PRODUCTOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    Producto producto = new Producto(datos[0], datos[1], datos[2], datos[3]);
                    listaProductos.add(producto);
                    modeloTabla.addRow(datos);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void guardarProductosEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PRODUCTOS))) {
            for (Producto producto : listaProductos) {
                bw.write(String.join(",", producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getCategoria()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventarioProductos::new);
    }
}

class Producto {
    private String codigo;
    private String nombre;
    private String precio;
    private String categoria;

    public Producto(String codigo, String nombre, String precio, String categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
