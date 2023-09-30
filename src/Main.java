public class Main {
    public static void main(String[] args) {
        //Crear el modelo, la vista y el controlador
        ContactoModelo modelo = new ContactoModelo();
        ContactoVista vista = new ContactoVista();
        ContactoControlador controlador = new ContactoControlador(modelo, vista);
    }
}