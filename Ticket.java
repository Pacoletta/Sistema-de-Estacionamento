import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Ticket {
    private int id;
    private Veiculo veiculo;
    private Vaga vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private double valorPago;
    private boolean ativo;

    public Ticket(int id, Veiculo veiculo, Vaga vaga, LocalDateTime horaEntrada) {
        this.id = id;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
        this.ativo = true;
        this.valorPago = 0.0;
    }

    public int getId() {
        return id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Cálculo do tempo de permanência em minutos
    public long calcularMinutosPermanencia() {
        if (horaSaida != null) {
            return ChronoUnit.MINUTES.between(horaEntrada, horaSaida);
        }
        return ChronoUnit.MINUTES.between(horaEntrada, LocalDateTime.now());
    }

    // Calcula o tempo de permanência em horas (arredondado para cima)
    public double calcularHorasPermanencia() {
        long minutos = calcularMinutosPermanencia();
        return Math.ceil(minutos / 60.0);
    }

    @Override
    public String toString() {
        return "Ticket #" + id +
                "\n  Veículo: " + veiculo.getPlaca() +
                "\n  Vaga: " + vaga.getNumero() +
                "\n  Entrada: " + horaEntrada +
                "\n  Saída: " + (horaSaida != null ? horaSaida : "Em andamento") +
                "\n  Valor: R$ " + String.format("%.2f", valorPago) +
                "\n  Status: " + (ativo ? "Ativo" : "Finalizado");
    }
}
