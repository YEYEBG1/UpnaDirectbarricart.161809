public class Modelo {
    private int edad;
    private double salario;
    private String tipoBien;
    private double valorBien;

    public boolean validarCliente(String nacimiento, String salario) {
        try {
            int año = Integer.parseInt(nacimiento);
            this.salario = Double.parseDouble(salario);
            int añoActual = java.time.Year.now().getValue();
            this.edad = añoActual - año;

            return año > 1900 && año <= añoActual && this.salario > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validarBien(String tipo, String valor) {
        try {
            this.tipoBien = tipo.toLowerCase();
            this.valorBien = Double.parseDouble(valor);

            if (tipoBien.equals("vehículo")) {
                return valorBien > 0 && valorBien <= 50000;
            } else if (tipoBien.equals("vivienda")) {
                return valorBien >= 50000;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Oferta calcularMejorOferta() {
        Oferta mafro = new Oferta("MAFRO", calcularImporteMafro(), calcularComision(calcularImporteMafro(), "MAFRO"));
        Oferta lineaIndirecta = new Oferta("LÍNEA INDIRECTA", calcularImporteLineaIndirecta(), calcularComision(calcularImporteLineaIndirecta(), "LÍNEA INDIRECTA"));
        Oferta adasles = new Oferta("ADASLES", calcularImporteAdasles(), calcularComision(calcularImporteAdasles(), "ADASLES"));

        Oferta mejor = mafro;
        if (lineaIndirecta.getImporte() < mejor.getImporte() || (lineaIndirecta.getImporte() == mejor.getImporte() && lineaIndirecta.getComision() > mejor.getComision())) {
            mejor = lineaIndirecta;
        }
        if (adasles.getImporte() < mejor.getImporte() || (adasles.getImporte() == mejor.getImporte() && adasles.getComision() > mejor.getComision())) {
            mejor = adasles;
        }
        return mejor;
    }

    private double calcularImporteMafro() {
        double porcentaje = 0.03;
        if (tipoBien.equals("vehículo") && edad < 20) {
            porcentaje = 0.05;
        } else if (tipoBien.equals("vivienda") && valorBien > 200000 && salario < 20000) {
            porcentaje = 0.02;
        }
        return valorBien * porcentaje;
    }

    private double calcularImporteLineaIndirecta() {
        double porcentaje = 0.03;
        if (tipoBien.equals("vehículo") && valorBien < 20000 || (tipoBien.equals("vivienda") && valorBien < 150000)) {
            porcentaje = 0.04;
        } else if (tipoBien.equals("vehículo") && valorBien >= 20000 && edad > 60) {
            porcentaje = 0.06;
        }
        return valorBien * porcentaje;
    }

    private double calcularImporteAdasles() {
        double porcentaje = 0.02;
        if (tipoBien.equals("vehículo") && (edad < 20 || edad > 60)) {
            porcentaje = 0.06;
        }
        return valorBien * porcentaje;
    }

    private int calcularComision(double importe, String aseguradora) {
        if (importe <= 1000) {
            return (int) (importe * 0.01);
        }
        switch (aseguradora) {
            case "MAFRO":
                return (int) (importe * 0.03);
            case "LÍNEA INDIRECTA":
                return (int) (importe * 0.04);
            case "ADASLES":
                return (int) (importe * 0.05);
            default:
                return 0;
        }
    }
}
