package Parcial3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import com.google.gson.*;
import java.awt.*;
import java.io.*;

class CorreoInvalidoException extends Exception {
    public CorreoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

public class Parcial extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtNombre, txtCorreo;

    public Parcial() {
        setTitle("Gestión de Datos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Formulario de Personas"));
        txtId = new JTextField();
        txtNombre = new JTextField();
        txtCorreo = new JTextField();
        JButton btnAgregar = new JButton("Agregar");

        formPanel.add(new JLabel("Identificación:"));
        formPanel.add(txtId);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Correo:"));
        formPanel.add(txtCorreo);
        formPanel.add(new JLabel(""));
        formPanel.add(btnAgregar);
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Opciones de Archivo"));

        JButton btnGuardarPlano = new JButton("Guardar ArchPlano");
        JButton btnLeerPlano = new JButton("Leer ArchPlano");
        JButton btnGuardarXML = new JButton("Guardar XML");
        JButton btnLeerXML = new JButton("Leer XML");
        JButton btnGuardarJSON = new JButton("Guardar JSON");
        JButton btnLeerJSON = new JButton("Leer JSON");
        

        buttonPanel.add(btnGuardarPlano);
        buttonPanel.add(btnLeerPlano);
        buttonPanel.add(btnGuardarXML);
        buttonPanel.add(btnLeerXML);
        buttonPanel.add(btnGuardarJSON);
        buttonPanel.add(btnLeerJSON);

        
        tableModel = new DefaultTableModel(new String[]{"Identificación", "Nombre", "Correo"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        
        btnAgregar.addActionListener(e -> agregarDatos());
        btnGuardarPlano.addActionListener(e -> guardarPlano());
        btnLeerPlano.addActionListener(e -> leerPlano());
        btnGuardarXML.addActionListener(e -> guardarXML());
        btnLeerXML.addActionListener(e -> leerXML());
        btnGuardarJSON.addActionListener(e -> guardarJSON());
        btnLeerJSON.addActionListener(e -> leerJSON());
    }

    private void agregarDatos() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        try {
            validarCorreo(correo);
            if (!id.isEmpty() && !nombre.isEmpty() && !correo.isEmpty()) {
                tableModel.addRow(new Object[]{id, nombre, correo});
                txtId.setText("");
                txtNombre.setText("");
                txtCorreo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (CorreoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Correo inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validarCorreo(String correo) throws CorreoInvalidoException {
        if (!correo.contains("@") || !correo.endsWith(".com")) {
            throw new CorreoInvalidoException("El correo debe contener '@' y terminar con '.com'.");
        }
    }

    private void guardarPlano() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.write(tableModel.getValueAt(i, 0) + "," +
                        tableModel.getValueAt(i, 1) + "," +
                        tableModel.getValueAt(i, 2));
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Datos guardados en archivo plano.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar archivo plano.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leerPlano() {
        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            tableModel.setRowCount(0);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                tableModel.addRow(parts);
            }
            JOptionPane.showMessageDialog(this, "Datos leídos desde archivo plano.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo plano.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Personas");
            doc.appendChild(root);

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Element persona = doc.createElement("Persona");

                Element id = doc.createElement("Identificacion");
                id.appendChild(doc.createTextNode(tableModel.getValueAt(i, 0).toString()));
                persona.appendChild(id);

                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(tableModel.getValueAt(i, 1).toString()));
                persona.appendChild(nombre);

                Element correo = doc.createElement("Correo");
                correo.appendChild(doc.createTextNode(tableModel.getValueAt(i, 2).toString()));
                persona.appendChild(correo);

                root.appendChild(persona);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("datos.xml"));
            transformer.transform(source, result);

            JOptionPane.showMessageDialog(this, "Datos guardados en archivo XML.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar archivo XML.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leerXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("datos.xml"));

            tableModel.setRowCount(0);
            NodeList personas = doc.getElementsByTagName("Persona");
            for (int i = 0; i < personas.getLength(); i++) {
                Element persona = (Element) personas.item(i);
                String id = persona.getElementsByTagName("Identificacion").item(0).getTextContent();
                String nombre = persona.getElementsByTagName("Nombre").item(0).getTextContent();
                String correo = persona.getElementsByTagName("Correo").item(0).getTextContent();
                tableModel.addRow(new Object[]{id, nombre, correo});
            }
            JOptionPane.showMessageDialog(this, "Datos leídos desde archivo XML.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo XML.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarJSON() {
        try (Writer writer = new FileWriter("datos.json")) {
            JsonArray jsonArray = new JsonArray();

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                JsonObject persona = new JsonObject();
                persona.addProperty("Identificacion", tableModel.getValueAt(i, 0).toString());
                persona.addProperty("Nombre", tableModel.getValueAt(i, 1).toString());
                persona.addProperty("Correo", tableModel.getValueAt(i, 2).toString());
                jsonArray.add(persona);
            }

            Gson gson = new Gson();
            gson.toJson(jsonArray, writer);

            JOptionPane.showMessageDialog(this, "Datos guardados en archivo JSON.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void leerJSON() {
        try (Reader reader = new FileReader("datos.json")) {
            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            tableModel.setRowCount(0);
            for (JsonElement element : jsonArray) {
                JsonObject persona = element.getAsJsonObject();
                String id = persona.get("Identificacion").getAsString();
                String nombre = persona.get("Nombre").getAsString(); 
                String correo = persona.get("Correo").getAsString();
                tableModel.addRow(new Object[]{id, nombre, correo});
            }

            JOptionPane.showMessageDialog(this, "Datos leídos desde archivo JSON.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer archivo JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Parcial().setVisible(true));
    }
}

                
