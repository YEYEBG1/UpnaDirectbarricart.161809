public class Oferta {
    private String aseguradora;
    private double importe;
    private int comision;

    public Oferta(String aseguradora, double importe, int comision) {
        this.aseguradora = aseguradora;
        this.importe = importe;
        this.comision = comision;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public double getImporte() {
        return importe;
    }

    public int getComision() {
        return comision;
    }

    @Override
    public String toString() {
        return aseguradora + " | " + (int) importe + " | " + comision;
    }
}


