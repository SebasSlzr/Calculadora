/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Taller2XML;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class InventarioProductosXML extends JFrame {
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField codigoCampo, nombreCampo, precioCampo, categoriaCampo;
    private static final String ARCHIVO_XML = "productosXML.xml";

    public InventarioProductosXML() {
        setTitle("Inventario de Productos - XML");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnas = {"Código", "Nombre", "Precio", "Categoría"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cargarProductoSeleccionado();
            }
        });
        JScrollPane scrollTabla = new JScrollPane(tabla);
        add(scrollTabla, BorderLayout.CENTER);

        JPanel formularioPanel = crearFormularioPanel();
        add(formularioPanel, BorderLayout.WEST);

        cargarProductosDesdeXML();

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

        JButton modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(e -> modificarProducto());

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
        panel.add(modificarButton);
        panel.add(eliminarButton);
        panel.add(cancelarButton);

        return panel;
    }

    private void limpiarFormulario() {
        codigoCampo.setText("");
        nombreCampo.setText("");
        precioCampo.setText("");
        categoriaCampo.setText("");
        tabla.clearSelection();
    }

    private void cargarProductosDesdeXML() {
        try {
            File archivo = new File(ARCHIVO_XML);
            if (!archivo.exists()) return; // Si no existe, no se carga nada

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList listaProductos = doc.getElementsByTagName("producto");

            for (int i = 0; i < listaProductos.getLength(); i++) {
                Node nodo = listaProductos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent();
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    String precio = elemento.getElementsByTagName("precio").item(0).getTextContent();
                    String categoria = elemento.getElementsByTagName("categoria").item(0).getTextContent();

                    modeloTabla.addRow(new Object[]{codigo, nombre, precio, categoria});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        modeloTabla.addRow(new Object[]{codigo, nombre, precio, categoria});
        guardarProductosEnXML();
        limpiarFormulario();
    }

    private void modificarProducto() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para modificar.");
            return;
        }

        String codigo = codigoCampo.getText();
        String nombre = nombreCampo.getText();
        String precio = precioCampo.getText();
        String categoria = categoriaCampo.getText();

        if (codigo.isEmpty() || nombre.isEmpty() || precio.isEmpty() || categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        modeloTabla.setValueAt(codigo, filaSeleccionada, 0);
        modeloTabla.setValueAt(nombre, filaSeleccionada, 1);
        modeloTabla.setValueAt(precio, filaSeleccionada, 2);
        modeloTabla.setValueAt(categoria, filaSeleccionada, 3);

        guardarProductosEnXML();
        limpiarFormulario();
    }

    private void eliminarProducto() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
            return;
        }

        modeloTabla.removeRow(filaSeleccionada);
        guardarProductosEnXML();
        limpiarFormulario();
    }

    private void cargarProductoSeleccionado() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            codigoCampo.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
            nombreCampo.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
            precioCampo.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
            categoriaCampo.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
        }
    }

    private void guardarProductosEnXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element rootElement = doc.createElement("productos");
            doc.appendChild(rootElement);

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                Element producto = doc.createElement("producto");

                Element codigo = doc.createElement("codigo");
                codigo.appendChild(doc.createTextNode(modeloTabla.getValueAt(i, 0).toString()));
                producto.appendChild(codigo);

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(modeloTabla.getValueAt(i, 1).toString()));
                producto.appendChild(nombre);

                Element precio = doc.createElement("precio");
                precio.appendChild(doc.createTextNode(modeloTabla.getValueAt(i, 2).toString()));
                producto.appendChild(precio);

                Element categoria = doc.createElement("categoria");
                categoria.appendChild(doc.createTextNode(modeloTabla.getValueAt(i, 3).toString()));
                producto.appendChild(categoria);

                rootElement.appendChild(producto);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ARCHIVO_XML));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventarioProductosXML::new);
    }
}

