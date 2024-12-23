public class Presentador {
    private ConsolaVista vista;
    private Modelo modelo;

    public Presentador(ConsolaVista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void ejecutar() {
        boolean clienteValido = false;
        do {
            String nacimiento = vista.solicitarDato("Introduce el año de nacimiento del cliente:");
            String salario = vista.solicitarDato("Introduce el salario del cliente:");
            clienteValido = modelo.validarCliente(nacimiento, salario);
            if (!clienteValido) {
                vista.mostrarMensaje("Datos de cliente no válidos. Intente nuevamente.");
            }
        } while (!clienteValido);

        boolean bienValido = false;
        do {
            String tipoBien = vista.solicitarDato("Introduce el tipo de bien (vehículo/vivienda):");
            String valorBien = vista.solicitarDato("Introduce el valor del bien:");
            bienValido = modelo.validarBien(tipoBien, valorBien);
            if (!bienValido) {
                vista.mostrarMensaje("Datos del bien no válidos. Intente nuevamente.");
            }
        } while (!bienValido);

        Oferta mejorOferta = modelo.calcularMejorOferta();
        vista.mostrarMensaje("La mejor oferta es: " + mejorOferta);
    }
}
