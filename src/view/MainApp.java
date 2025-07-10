package view;

import dao.PersonaDAO;
import dao.MascotaDAO;
import model.Persona;
import model.Mascota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MainApp extends JFrame {

    // CAMPOS DE PERSONAS
    private JTextField txtDoc;
    private JTextField txtNombre;
    private JTextField txtTel;


    private JTextField docField, nombreField, telField;
    private JTable personaTable;
    private DefaultTableModel personaModel;

    private JTextField codField, nomMascotaField, tipoField, edadField, docDuenioField;
    private JTable mascotaTable;
    private DefaultTableModel mascotaModel;

    public MainApp() {
        setTitle("Clínica Veterinaria ABC");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Personas", crearPanelPersonas());
        tabs.add("Mascotas", crearPanelMascotas());

        add(tabs);
        setVisible(true);
    }

    private JPanel crearPanelPersonas() {
        JPanel panel = new JPanel(new BorderLayout());

        // FORMULARIO AJUSTADO
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Formulario de Persona"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblDoc = new JLabel("Documento:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblTel = new JLabel("Teléfono:");

        txtDoc = new JTextField(12);
        txtNombre = new JTextField(12);
        txtTel = new JTextField(12);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblDoc, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(txtDoc, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblTel, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(txtTel, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        btnPanel.add(btnGuardar);
        btnPanel.add(btnActualizar);
        btnPanel.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        form.add(btnPanel, gbc);

        // Eventos
        btnGuardar.addActionListener(e -> {
            Persona p = new Persona(txtDoc.getText(), txtNombre.getText(), txtTel.getText());
            JOptionPane.showMessageDialog(this, PersonaDAO.registrar(p));
            listarPersonas();
        });

        btnActualizar.addActionListener(e -> {
            Persona p = new Persona(txtDoc.getText(), txtNombre.getText(), txtTel.getText());
            JOptionPane.showMessageDialog(this, PersonaDAO.actualizar(p));
            listarPersonas();
        });

        btnEliminar.addActionListener(e -> {
            String doc = txtDoc.getText();
            JOptionPane.showMessageDialog(this, PersonaDAO.eliminar(doc));
            listarPersonas();
        });

        // Tabla
        personaModel = new DefaultTableModel(new String[]{"Documento", "Nombre", "Teléfono"}, 0);
        personaTable = new JTable(personaModel);
        personaTable.setRowHeight(24);
        personaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        personaTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = personaTable.getSelectedRow();
                txtDoc.setText(personaModel.getValueAt(row, 0).toString());
                txtNombre.setText(personaModel.getValueAt(row, 1).toString());
                txtTel.setText(personaModel.getValueAt(row, 2).toString());
            }
        });

        panel.add(form, BorderLayout.WEST);
        panel.add(new JScrollPane(personaTable), BorderLayout.CENTER);

        listarPersonas();
        return panel;
    }


    private JPanel crearPanelMascotas() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Formulario de Mascota"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCod = new JLabel("Código:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblTipo = new JLabel("Tipo:");
        JLabel lblEdad = new JLabel("Edad:");
        JLabel lblDocDuenio = new JLabel("Doc. Dueño:");

        codField = new JTextField(12);
        nomMascotaField = new JTextField(12);
        tipoField = new JTextField(12);
        edadField = new JTextField(12);
        docDuenioField = new JTextField(12);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblCod, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(codField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(nomMascotaField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblTipo, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(tipoField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblEdad, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(edadField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST;
        form.add(lblDocDuenio, gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        form.add(docDuenioField, gbc);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnPanel.add(btnGuardar);
        btnPanel.add(btnActualizar);
        btnPanel.add(btnEliminar);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        form.add(btnPanel, gbc);

        // Eventos
        btnGuardar.addActionListener(e -> {
            try {
                int edad = Integer.parseInt(edadField.getText());
                Mascota m = new Mascota(codField.getText(), nomMascotaField.getText(), tipoField.getText(), edad, docDuenioField.getText());
                JOptionPane.showMessageDialog(this, MascotaDAO.registrar(m));
                listarMascotas();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Edad inválida.");
            }
        });

        btnActualizar.addActionListener(e -> {
            try {
                int edad = Integer.parseInt(edadField.getText());
                Mascota m = new Mascota(codField.getText(), nomMascotaField.getText(), tipoField.getText(), edad, docDuenioField.getText());
                JOptionPane.showMessageDialog(this, MascotaDAO.actualizar(m));
                listarMascotas();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Edad inválida.");
            }
        });

        btnEliminar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, MascotaDAO.eliminar(codField.getText()));
            listarMascotas();
        });

        mascotaModel = new DefaultTableModel(new String[]{"Código", "Nombre", "Tipo", "Edad", "Dueño"}, 0);
        mascotaTable = new JTable(mascotaModel);
        mascotaTable.setRowHeight(24);
        mascotaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        mascotaTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = mascotaTable.getSelectedRow();
                codField.setText(mascotaModel.getValueAt(row, 0).toString());
                nomMascotaField.setText(mascotaModel.getValueAt(row, 1).toString());
                tipoField.setText(mascotaModel.getValueAt(row, 2).toString());
                edadField.setText(mascotaModel.getValueAt(row, 3).toString());
                docDuenioField.setText(mascotaModel.getValueAt(row, 4).toString());
            }
        });

        panel.add(form, BorderLayout.WEST);
        panel.add(new JScrollPane(mascotaTable), BorderLayout.CENTER);

        listarMascotas();
        return panel;
    }


    private void listarPersonas() {
        personaModel.setRowCount(0);
        for (Persona p : PersonaDAO.listar()) {
            personaModel.addRow(new Object[]{p.getDocumento(), p.getNombre(), p.getTelefono()});
        }
    }

    private void listarMascotas() {
        mascotaModel.setRowCount(0);
        for (Mascota m : MascotaDAO.listar()) {
            mascotaModel.addRow(new Object[]{m.getCodigo(), m.getNombre(), m.getTipo(), m.getEdad(), m.getDocumentoDuenio()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}