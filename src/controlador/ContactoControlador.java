package controlador;

import modelo.ContactoModelo;
import vista.ContactoVista;

public class ContactoControlador {
    private ContactoModelo modelo;
    private ContactoVista vista;

    public ContactoControlador(ContactoModelo modelo, ContactoVista vista) {
        this.modelo = modelo;
        this.vista = vista;

        //Añadir listener para los botones
        vista.getAddButton().addActionListener(e -> agregarContacto());
        vista.getUpdateButton().addActionListener(e -> actualizarContacto());
        vista.getDeleteButton().addActionListener(e -> eliminarContacto());
        vista.getListButton().addActionListener(e -> listarContactos());
    }

    private void agregarContacto() {
        String nombre = vista.getNombreField().getText();
        String telefono = vista.getTelefonoField().getText();
        String email = vista.getEmailField().getText();
        modelo.agregarContacto(nombre, telefono, email);
        listarContactos();
        vista.limpiarFormulario();
    }

    private void actualizarContacto() {
        int id = Integer.parseInt(vista.getIdField().getText());
        String nombre = vista.getNombreField().getText();
        String telefono = vista.getTelefonoField().getText();
        String email = vista.getEmailField().getText();
        modelo.actualizarContacto(id, nombre, telefono, email);
        listarContactos();
        vista.limpiarFormulario();
    }

    private void eliminarContacto() {
        int id = Integer.parseInt(vista.getIdField().getText());
        modelo.eliminarContacto(id);
        listarContactos();
        vista.limpiarFormulario();
    }

    private void listarContactos() {
        vista.mostrarContactos(modelo.listarContactos());
    }
}
