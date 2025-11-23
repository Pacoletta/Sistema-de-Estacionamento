public class Vaga {
    private int numero;
    private boolean ocupada;
    private String tipoVaga; // "comum", "idoso", "deficiente"

    public Vaga(int numero, String tipoVaga) {
        this.numero = numero;
        this.tipoVaga = tipoVaga;
        this.ocupada = false;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public String getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(String tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "numero=" + numero +
                ", ocupada=" + ocupada +
                ", tipo='" + tipoVaga + '\'' +
                '}';
    }
}
