/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MiniProyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorContactos extends JFrame {
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private JTextField buscarTexto;
    private JTextField nombreCampo, apellidoCampo, telefonoCampo, emailCampo, direccionCampo;
    private JRadioButton solteroRadio, casadoRadio, unionLibreRadio, divorciadoRadio;
    private JProgressBar barraProgreso;
    private JLabel imagenLabel;
    private File imagenSeleccionada;
    private List<Contacto> listaContactos;
    private int contactoSeleccionado = -1;

    public GestorContactos() {
        listaContactos = new ArrayList<>();

      
        setTitle("Gestor de Contactos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem nuevoItem = new JMenuItem("Nuevo");
        nuevoItem.addActionListener(e -> limpiarFormulario());
        JMenuItem guardarItem = new JMenuItem("Guardar");
        guardarItem.addActionListener(e -> guardarContacto());
        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(e -> System.exit(0));
        menuArchivo.add(nuevoItem);
        menuArchivo.add(guardarItem);
        menuArchivo.add(salirItem);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem acercaDeItem = new JMenuItem("Acerca de");
        acercaDeItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Gestor de Contactos v1.0"));
        menuAyuda.add(acercaDeItem);

        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);
        setJMenuBar(menuBar);

        
        String[] columnas = {"Nombre", "Apellido", "Teléfono", "Email"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaContactos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cargarContactoSeleccionado();
            }
        });
        JScrollPane scrollTabla = new JScrollPane(tablaContactos);
        add(scrollTabla, BorderLayout.CENTER);

        
        JPanel panelBusqueda = new JPanel();
        buscarTexto = new JTextField(20);
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.addActionListener(e -> buscarContacto());
        panelBusqueda.add(buscarTexto);
        panelBusqueda.add(botonBuscar);
        add(panelBusqueda, BorderLayout.NORTH);

        
        JPanel formularioPanel = crearFormularioPanel();
        add(formularioPanel, BorderLayout.EAST);

        
        barraProgreso = new JProgressBar(0, 100);
        add(barraProgreso, BorderLayout.SOUTH);

        setKeyBindings();

        setVisible(true);
    }

    private JPanel crearFormularioPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        nombreCampo = new JTextField(15);
        apellidoCampo = new JTextField(15);
        telefonoCampo = new JTextField(15);
        emailCampo = new JTextField(15);
        direccionCampo = new JTextField(15);

        
        telefonoCampo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) e.consume(); // Solo dígitos
            }
        });
        
        emailCampo.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (!emailCampo.getText().matches("^[\\w-.]+@[\\w-]+\\.com$")) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un correo válido.");
                    emailCampo.requestFocus();
                }
            }
        });

        
        JPanel estadoCivilPanel = new JPanel();
        ButtonGroup estadoCivilGroup = new ButtonGroup();
        solteroRadio = new JRadioButton("Soltero");
        casadoRadio = new JRadioButton("Casado");
        unionLibreRadio = new JRadioButton("Unión Libre");
        divorciadoRadio = new JRadioButton("Divorciado");
        
        estadoCivilGroup.add(solteroRadio);
        estadoCivilGroup.add(casadoRadio);
        estadoCivilGroup.add(unionLibreRadio);
        estadoCivilGroup.add(divorciadoRadio);
        
        estadoCivilPanel.add(solteroRadio);
        estadoCivilPanel.add(casadoRadio);
        estadoCivilPanel.add(unionLibreRadio);
        estadoCivilPanel.add(divorciadoRadio);
        
        
        imagenLabel = new JLabel();
        imagenLabel.setPreferredSize(new Dimension(100, 100));
        JButton seleccionarImagenButton = new JButton("Seleccionar Imagen");
        seleccionarImagenButton.addActionListener(e -> seleccionarImagen());

        
        
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> guardarContacto());
        
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(e -> eliminarContacto());

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> limpiarFormulario());

        
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreCampo);
        panel.add(new JLabel("Apellido:"));
        panel.add(apellidoCampo);
        panel.add(new JLabel("Teléfono:"));
        panel.add(telefonoCampo);
        panel.add(new JLabel("Email:"));
        panel.add(emailCampo);
        panel.add(new JLabel("Dirección:"));
        panel.add(direccionCampo);
        panel.add(new JLabel("Estado Civil:"));
        panel.add(estadoCivilPanel);
        panel.add(new JLabel("Imagen:"));
        panel.add(imagenLabel);
        panel.add(seleccionarImagenButton);
        panel.add(guardarButton);
        panel.add(eliminarButton);
        panel.add(cancelarButton);

        return panel;
    }

        private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            imagenSeleccionada = fileChooser.getSelectedFile();
            ImageIcon icono = new ImageIcon(imagenSeleccionada.getAbsolutePath());
            Image imagenEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        }
    }

    
    
    private void limpiarFormulario() {
        nombreCampo.setText("");
        apellidoCampo.setText("");
        telefonoCampo.setText("");
        emailCampo.setText("");
        direccionCampo.setText("");
        solteroRadio.setSelected(true);
        imagenLabel.setIcon(null);
        imagenSeleccionada = null;
        contactoSeleccionado = -1;
        actualizarProgreso();
    }

    private void actualizarProgreso() {
        int progreso = 0;
        if (!nombreCampo.getText().isEmpty()) progreso += 20;
        if (!apellidoCampo.getText().isEmpty()) progreso += 20;
        if (!telefonoCampo.getText().isEmpty()) progreso += 20;
        if (!emailCampo.getText().isEmpty()) progreso += 20;
        if (!direccionCampo.getText().isEmpty()) progreso += 20;
        barraProgreso.setValue(progreso);
    }

    private boolean existeContacto(String nombre, String telefono) {
        return listaContactos.stream().anyMatch(c -> c.getNombre().equals(nombre) || c.getTelefono().equals(telefono));
    }

    private void guardarContacto() {
        String nombre = nombreCampo.getText();
        String telefono = telefonoCampo.getText();
        
        if (existeContacto(nombre, telefono)) {
            JOptionPane.showMessageDialog(this, "Ya existe un contacto con el mismo nombre o número.");
            return;
        }

        if (nombre.isEmpty() || apellidoCampo.getText().isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.");
            return;
        }
        
        ImageIcon imagenIcono = null;
        if (imagenSeleccionada != null) {
            imagenIcono = new ImageIcon(new ImageIcon(imagenSeleccionada.getAbsolutePath()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        }


        if (contactoSeleccionado == -1) { // Nuevo contacto
            Contacto nuevoContacto = new Contacto(nombre, apellidoCampo.getText(), telefono, emailCampo.getText(), direccionCampo.getText(), obtenerEstadoCivil(), imagenIcono);
            listaContactos.add(nuevoContacto);
            modeloTabla.addRow(new Object[]{nombre, apellidoCampo.getText(), telefono, emailCampo.getText()});
        } else { // Editar contacto existente
            Contacto contacto = listaContactos.get(contactoSeleccionado);
            contacto.setNombre(nombre);
            contacto.setTelefono(telefono);
            modeloTabla.setValueAt(nombre, contactoSeleccionado, 0);
            modeloTabla.setValueAt(telefono, contactoSeleccionado, 2);
        }

        limpiarFormulario();
    }

    private void eliminarContacto() {
        if (contactoSeleccionado == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este contacto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listaContactos.remove(contactoSeleccionado);
            modeloTabla.removeRow(contactoSeleccionado);
            limpiarFormulario();
        }
    }

    private void setKeyBindings() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "nuevo");
        actionMap.put("nuevo", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "editar");
        actionMap.put("editar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cargarContactoSeleccionado();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "eliminar");
        actionMap.put("eliminar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                eliminarContacto();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "guardar");
        actionMap.put("guardar", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                guardarContacto();
            }
        });
    }

    private void cargarContactoSeleccionado() {
        contactoSeleccionado = tablaContactos.getSelectedRow();
        if (contactoSeleccionado != -1) {
            Contacto contacto = listaContactos.get(contactoSeleccionado);
            nombreCampo.setText(contacto.getNombre());
            apellidoCampo.setText(contacto.getApellido());
            telefonoCampo.setText(contacto.getTelefono());
            emailCampo.setText(contacto.getEmail());
            direccionCampo.setText(contacto.getDireccion());
            seleccionarEstadoCivil(contacto.getEstadoCivil());
        }
    }

    private String obtenerEstadoCivil() {
        if (solteroRadio.isSelected()) return "Soltero";
        if (casadoRadio.isSelected()) return "Casado";
        if (unionLibreRadio.isSelected()) return "Unión Libre";
        return "Divorciado";
    }

    private void seleccionarEstadoCivil(String estadoCivil) {
        switch (estadoCivil) {
            case "Soltero" -> solteroRadio.setSelected(true);
            case "Casado" -> casadoRadio.setSelected(true);
            case "Unión Libre" -> unionLibreRadio.setSelected(true);
            case "Divorciado" -> divorciadoRadio.setSelected(true);
        }
    }

    private void buscarContacto() {
        String texto = buscarTexto.getText().toLowerCase();
        modeloTabla.setRowCount(0);
        for (Contacto contacto : listaContactos) {
            if (contacto.getNombre().toLowerCase().contains(texto) || contacto.getTelefono().contains(texto)) {
                modeloTabla.addRow(new Object[]{contacto.getNombre(), contacto.getApellido(), contacto.getTelefono(), contacto.getEmail()});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestorContactos::new);
    }
}

class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;
    private String estadoCivil;
    private ImageIcon imagen;

    public Contacto(String nombre, String apellido, String telefono, String email, String direccion, String estadoCivil, ImageIcon imagenIcono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.estadoCivil = estadoCivil;
        this.imagen = imagen;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    public String getEstadoCivil() { return estadoCivil; }
    public ImageIcon getImagen() {return imagen;}
    

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public void setImagen(ImageIcon imagen) {this.imagen = imagen;}
    
}
