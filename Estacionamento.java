import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    private List<Vaga> vagas;
    private List<Ticket> tickets;
    private int contadorTickets;
    private double tarifaPorHora;

    public Estacionamento(String nome, int quantidadeVagas, double tarifaPorHora) {
        this.nome = nome;
        this.vagas = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.contadorTickets = 1;
        this.tarifaPorHora = tarifaPorHora;
        
        // Inicializa as vagas
        for (int i = 1; i <= quantidadeVagas; i++) {
            String tipo = "comum";
            if (i <= 3) {
                tipo = "deficiente"; // Primeiras 3 vagas para deficientes
            } else if (i <= 6) {
                tipo = "idoso"; // Próximas 3 vagas para idosos
            }
            vagas.add(new Vaga(i, tipo));
        }
    }

    // Registra a entrada de um veículo
    public Ticket registrarEntrada(Veiculo veiculo) {
        Vaga vagaDisponivel = buscarVagaLivre();
        
        if (vagaDisponivel == null) {
            System.out.println("❌ Estacionamento lotado! Não há vagas disponíveis.");
            return null;
        }

        vagaDisponivel.setOcupada(true);
        LocalDateTime horaEntrada = LocalDateTime.now();
        Ticket ticket = new Ticket(contadorTickets++, veiculo, vagaDisponivel, horaEntrada);
        tickets.add(ticket);

        System.out.println("✅ Entrada registrada com sucesso!");
        System.out.println("Ticket #" + ticket.getId());
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Vaga: " + vagaDisponivel.getNumero() + " (" + vagaDisponivel.getTipoVaga() + ")");
        System.out.println("Horário de entrada: " + horaEntrada);
        
        return ticket;
    }

    // Registra a saída de um veículo
    public void registrarSaida(String placa) {
        Ticket ticket = buscarTicketAtivo(placa);
        
        if (ticket == null) {
            System.out.println("❌ Veículo não encontrado ou já saiu do estacionamento.");
            return;
        }

        LocalDateTime horaSaida = LocalDateTime.now();
        ticket.setHoraSaida(horaSaida);
        
        double horas = ticket.calcularHorasPermanencia();
        double valor = calcularValor(horas);
        ticket.setValorPago(valor);
        ticket.setAtivo(false);
        
        // Libera a vaga
        ticket.getVaga().setOcupada(false);

        System.out.println("\n✅ Saída registrada com sucesso!");
        System.out.println("Ticket #" + ticket.getId());
        System.out.println("Veículo: " + placa);
        System.out.println("Horário de entrada: " + ticket.getHoraEntrada());
        System.out.println("Horário de saída: " + horaSaida);
        System.out.println("Tempo de permanência: " + ticket.calcularMinutosPermanencia() + " minutos (" + horas + " horas)");
        System.out.println("Valor a pagar: R$ " + String.format("%.2f", valor));
    }

    // Busca uma vaga livre
    private Vaga buscarVagaLivre() {
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                return vaga;
            }
        }
        return null;
    }

    // Busca um ticket ativo pela placa do veículo
    private Ticket buscarTicketAtivo(String placa) {
        for (Ticket ticket : tickets) {
            if (ticket.isAtivo() && ticket.getVeiculo().getPlaca().equalsIgnoreCase(placa)) {
                return ticket;
            }
        }
        return null;
    }

    // Calcula o valor a ser pago
    private double calcularValor(double horas) {
        return horas * tarifaPorHora;
    }

    // Exibe o status das vagas
    public void exibirStatusVagas() {
        System.out.println("\n=== STATUS DAS VAGAS ===");
        int livres = 0;
        int ocupadas = 0;
        
        for (Vaga vaga : vagas) {
            String status = vaga.isOcupada() ? "🔴 OCUPADA" : "🟢 LIVRE";
            System.out.println("Vaga " + vaga.getNumero() + " (" + vaga.getTipoVaga() + "): " + status);
            
            if (vaga.isOcupada()) {
                ocupadas++;
            } else {
                livres++;
            }
        }
        
        System.out.println("\nTotal: " + vagas.size() + " vagas");
        System.out.println("Livres: " + livres + " | Ocupadas: " + ocupadas);
    }

    // Exibe todos os veículos atualmente no estacionamento
    public void exibirVeiculosEstacionados() {
        System.out.println("\n=== VEÍCULOS NO ESTACIONAMENTO ===");
        boolean temVeiculos = false;
        
        for (Ticket ticket : tickets) {
            if (ticket.isAtivo()) {
                temVeiculos = true;
                System.out.println("\nTicket #" + ticket.getId());
                System.out.println("Placa: " + ticket.getVeiculo().getPlaca());
                System.out.println("Modelo: " + ticket.getVeiculo().getModelo());
                System.out.println("Vaga: " + ticket.getVaga().getNumero());
                System.out.println("Entrada: " + ticket.getHoraEntrada());
                System.out.println("Tempo decorrido: " + ticket.calcularMinutosPermanencia() + " minutos");
            }
        }
        
        if (!temVeiculos) {
            System.out.println("Nenhum veículo no estacionamento no momento.");
        }
    }

    // Exibe o histórico de todos os tickets
    public void exibirHistorico() {
        System.out.println("\n=== HISTÓRICO DE TICKETS ===");
        
        if (tickets.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }
        
        for (Ticket ticket : tickets) {
            System.out.println("\n" + ticket);
            System.out.println("----------------------------");
        }
    }

    // Calcula o total arrecadado
    public void exibirRelatorioFinanceiro() {
        System.out.println("\n=== RELATÓRIO FINANCEIRO ===");
        double totalArrecadado = 0.0;
        int ticketsFinalizados = 0;
        
        for (Ticket ticket : tickets) {
            if (!ticket.isAtivo()) {
                totalArrecadado += ticket.getValorPago();
                ticketsFinalizados++;
            }
        }
        
        System.out.println("Total de tickets finalizados: " + ticketsFinalizados);
        System.out.println("Total arrecadado: R$ " + String.format("%.2f", totalArrecadado));
        System.out.println("Tarifa por hora: R$ " + String.format("%.2f", tarifaPorHora));
    }

    public String getNome() {
        return nome;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }
}
