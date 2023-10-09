package vista;

import modelo.Contacto;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ContactoVista {
    private JFrame frame;
    private JPanel panel;
    private JTextField nombreField, telefonoField, emailField, idField;
    private JButton addButton, updateButton, deleteButton, listButton;
    private JTable contactosTable;
    private DefaultTableModel tableModel;

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getListButton() {
        return listButton;
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getTelefonoField() {
        return telefonoField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTable getContactosTable() {
        return  contactosTable;
    }

    public ContactoVista() {
        frame = new JFrame("Contactos CRUD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);

        frame.setLayout(new BorderLayout());

        //Panel Norte con Grid Layout para los campos
        JPanel northPanel = new JPanel(new GridLayout(4,2));

        //ID
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(10);
        northPanel.add(idLabel);
        northPanel.add(idField);

        //Nombre
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(10);
        northPanel.add(nombreLabel);
        northPanel.add(nombreField);

        //Telefono
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(10);
        northPanel.add(telefonoLabel);
        northPanel.add(telefonoField);

        //Email
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(10);
        northPanel.add(emailLabel);
        northPanel.add(emailField);

        frame.add(northPanel, BorderLayout.NORTH);

        //Panel sur con Grid Layout para los botones
        JPanel southPanel = new JPanel(new GridLayout(1,4));

        addButton = new JButton("Agregar");
        updateButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");
        listButton = new JButton("Listar");
        southPanel.add(addButton);
        southPanel.add(updateButton);
        southPanel.add(deleteButton);
        southPanel.add(listButton);

        frame.add(southPanel, BorderLayout.SOUTH);

        //Tabla en el centro
        String[] columnNames = {"ID", "Nombre", "Teléfono", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        contactosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactosTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        //Listener para la tabla
        contactosTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            /*Se agrega un ListSelectionListener al modelo de seleccion de la tabla contactosTable.
            Esto significa que el cÃ³digo dentro del valueChanged se ejecutara cada vez que cambie la seleccion en la tabla.*/
            @Override
            public void valueChanged(ListSelectionEvent e) {//metodo que se ejecuta cuendo cambia la seleccion de la tabla
                // Verifica si la seleccion no esta cambiando y si hay una fila seleccionada en la tabla.
                if (!e.getValueIsAdjusting() && contactosTable.getSelectedRow() != -1) {//se utiliza para verificar si ha ocurrido un evento de selecciÃ³n vÃ¡lida en una tabla
                int selectedRow = contactosTable.getSelectedRow();//Obtiene el Ã­ndice de la fila seleccionada en la tabla.
                idField.setText(contactosTable.getValueAt(selectedRow, 0).toString());//Obtiene el valor de la columna 0 (ID) de la fila seleccionada y lo establece en el campo de texto idField.
                nombreField.setText(contactosTable.getValueAt(selectedRow, 1).toString());//Obtiene el valor de la columna 1 (Nombre) de la fila seleccionada y lo establece en el campo de texto nombreField.
                telefonoField.setText(contactosTable.getValueAt(selectedRow, 2).toString());//Obtiene el valor de la columna 2 (TelÃ©fono) de la fila seleccionada y lo establece en el campo de texto telefonoField.
                emailField.setText(contactosTable.getValueAt(selectedRow, 3).toString());// Obtiene el valor de la columna 3 (Email) de la fila seleccionada y lo establece en el campo de texto emailField.
                }
            }
        });

        frame.setVisible(true);
    }

    public void mostrarContactos(List<Contacto> contactos) {
        tableModel.setRowCount(0); //Limpiar las filas existentes
        for (Contacto contacto : contactos) {
            Object[] rowData = {contacto.getId(), contacto.getNombre(), contacto.getTelefono(), contacto.getEmail()};
            tableModel.addRow(rowData);
        }
    }

    public void limpiarFormulario() {
        idField.setText("");
        nombreField.setText("");
        telefonoField.setText("");
        emailField.setText("");
    }
}
