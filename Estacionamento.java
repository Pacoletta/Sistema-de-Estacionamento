import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class Estacionamento {
    private String nome;
    private List<Vaga> vagas;
    private List<Ticket> tickets;
    private int contadorTickets;
    private double tarifaPorHora;
    private Queue<Veiculo> filaEspera; // Fila de espera quando estacionamento está cheio
    private Map<LocalDate, Double> arrecadacaoPorDia; // Total arrecadado por dia

    public Estacionamento(String nome, int quantidadeVagas, double tarifaPorHora) {
        this.nome = nome;
        this.vagas = new ArrayList<>();
        this.tickets = new ArrayList<>();
        this.contadorTickets = 1;
        this.tarifaPorHora = tarifaPorHora;
        this.filaEspera = new LinkedList<>(); // Inicializa a fila de espera
        this.arrecadacaoPorDia = new HashMap<>(); // Inicializa mapa de arrecadação por dia
        
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
        // Busca vaga adequada ao perfil do condutor
        Vaga vagaDisponivel = buscarVagaPorPerfil(veiculo.getPerfilCondutor());
        
        if (vagaDisponivel == null) {
            // Estacionamento lotado - adiciona na fila de espera
            filaEspera.add(veiculo);
            System.out.println("⚠️ Estacionamento lotado! Não há vagas disponíveis.");
            System.out.println("📋 Veículo " + veiculo.getPlaca() + " adicionado à fila de espera.");
            System.out.println("Posição na fila: " + filaEspera.size());
            return null;
        }

        vagaDisponivel.setOcupada(true);
        LocalDateTime horaEntrada = LocalDateTime.now();
        Ticket ticket = new Ticket(contadorTickets++, veiculo, vagaDisponivel, horaEntrada);
        tickets.add(ticket);

        System.out.println("\u2705 Entrada registrada com sucesso!");
        System.out.println("Ticket #" + ticket.getId());
        System.out.println("Perfil: " + veiculo.getPerfilCondutor());
        System.out.println("Tipo: " + veiculo.getTipo());
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Vaga: " + vagaDisponivel.getNumero() + " (" + vagaDisponivel.getTipoVaga() + ")");
        System.out.println("Horário de entrada: " + horaEntrada);
        
        return ticket;
    }

    // Registra a entrada de um veículo com horário manual
    public Ticket registrarEntradaManual(Veiculo veiculo, int ano, int mes, int dia, int hora, int minuto) {
        // Busca vaga adequada ao perfil do condutor
        Vaga vagaDisponivel = buscarVagaPorPerfil(veiculo.getPerfilCondutor());
        
        if (vagaDisponivel == null) {
            // Estacionamento lotado - adiciona na fila de espera
            filaEspera.add(veiculo);
            System.out.println("⚠️ Estacionamento lotado! Não há vagas disponíveis.");
            System.out.println("📋 Veículo " + veiculo.getPlaca() + " adicionado à fila de espera.");
            System.out.println("Posição na fila: " + filaEspera.size());
            return null;
        }

        vagaDisponivel.setOcupada(true);
        LocalDateTime horaEntrada = LocalDateTime.of(ano, mes, dia, hora, minuto);
        Ticket ticket = new Ticket(contadorTickets++, veiculo, vagaDisponivel, horaEntrada);
        tickets.add(ticket);

        System.out.println("\u2705 Entrada registrada com sucesso!");
        System.out.println("Ticket #" + ticket.getId());
        System.out.println("Perfil: " + veiculo.getPerfilCondutor());
        System.out.println("Tipo: " + veiculo.getTipo());
        System.out.println("Veículo: " + veiculo.getPlaca());
        System.out.println("Vaga: " + vagaDisponivel.getNumero() + " (" + vagaDisponivel.getTipoVaga() + ")");
        System.out.println("Horário de entrada (INFORMADO): " + horaEntrada);
        
        return ticket;
    }

    // Registra a saída de um veículo
    public void registrarSaida(String placa) {
        Ticket ticket = buscarTicketAtivo(placa);
        
        if (ticket == null) {
            System.out.println("Veículo não encontrado ou já saiu do estacionamento.");
            return;
        }

        LocalDateTime horaSaida = LocalDateTime.now();
        ticket.setHoraSaida(horaSaida);
        
        double horas = ticket.calcularHorasPermanencia();
        double valor = calcularValor(horas, ticket.getVeiculo().getTipo());
        ticket.setValorPago(valor);
        ticket.setAtivo(false);
        
        // Registra arrecadação do dia
        LocalDate dataSaida = horaSaida.toLocalDate();
        arrecadacaoPorDia.put(dataSaida, arrecadacaoPorDia.getOrDefault(dataSaida, 0.0) + valor);
        
        // Libera a vaga
        Vaga vagaLiberada = ticket.getVaga();
        vagaLiberada.setOcupada(false);

        System.out.println("\n\u2705 Saída registrada com sucesso!");
        System.out.println("Ticket #" + ticket.getId());
        System.out.println("Tipo: " + ticket.getVeiculo().getTipo());
        System.out.println("Veículo: " + placa);
        System.out.println("Horário de entrada: " + ticket.getHoraEntrada());
        System.out.println("Horário de saída: " + horaSaida);
        System.out.println("Tempo de permanência: " + ticket.calcularMinutosPermanencia() + " minutos (" + horas + " horas)");
        System.out.println("Valor a pagar: R$ " + String.format("%.2f", valor));
        
        // Processa fila de espera - entrada automática do próximo da fila
        if (!filaEspera.isEmpty()) {
            System.out.println("\n🔄 Processando fila de espera...");
            Veiculo proximoVeiculo = filaEspera.poll(); // Remove o primeiro da fila
            
            // Busca vaga adequada ao perfil do próximo veículo
            Vaga vagaParaProximo = buscarVagaPorPerfil(proximoVeiculo.getPerfilCondutor());
            
            if (vagaParaProximo != null) {
                vagaParaProximo.setOcupada(true);
                LocalDateTime horaEntradaFila = LocalDateTime.now();
                Ticket novoTicket = new Ticket(contadorTickets++, proximoVeiculo, vagaParaProximo, horaEntradaFila);
                tickets.add(novoTicket);
                
                System.out.println("✅ Veículo da fila entrou automaticamente!");
                System.out.println("Ticket #" + novoTicket.getId());
                System.out.println("Perfil: " + proximoVeiculo.getPerfilCondutor());
                System.out.println("Tipo: " + proximoVeiculo.getTipo());
                System.out.println("Veículo: " + proximoVeiculo.getPlaca());
                System.out.println("Vaga: " + vagaParaProximo.getNumero() + " (" + vagaParaProximo.getTipoVaga() + ")");
                System.out.println("Veículos restantes na fila: " + filaEspera.size());
            } else {
                // Se não houver vaga adequada, retorna veículo para a fila
                filaEspera.add(proximoVeiculo);
                System.out.println("⚠️ Nenhuma vaga adequada disponível. Veículo permanece na fila.");
            }
        }
    }

    // Busca uma vaga adequada ao perfil do condutor
    // Prioriza vagas específicas (deficiente, idoso) mas permite uso de vagas comuns se necessário
    private Vaga buscarVagaPorPerfil(String perfilCondutor) {
        // 1. Primeiro, tenta encontrar uma vaga do tipo específico
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada() && vaga.getTipoVaga().equalsIgnoreCase(perfilCondutor)) {
                return vaga;
            }
        }
        
        // 2. Se não encontrar vaga específica, permite uso de vaga comum
        // (deficientes e idosos podem usar vagas comuns se as deles estiverem ocupadas)
        if (!perfilCondutor.equalsIgnoreCase("comum")) {
            for (Vaga vaga : vagas) {
                if (!vaga.isOcupada() && vaga.getTipoVaga().equalsIgnoreCase("comum")) {
                    System.out.println("⚠️ Vagas preferenciais ocupadas. Alocando vaga comum.");
                    return vaga;
                }
            }
        }
        
        return null; // Nenhuma vaga disponível
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

    // Calcula o valor a ser pago baseado no tipo de veículo
    // Carro: primeira hora R$12,00 + R$8,00 por hora adicional
    // Moto: primeira hora R$8,00 + R$5,00 por hora adicional
    private double calcularValor(double horas, String tipoVeiculo) {
        if (horas <= 0) return 0.0;
        
        if (tipoVeiculo.equalsIgnoreCase("Carro")) {
            // Primeira hora: R$12,00, adicional: R$8,00
            if (horas <= 1) {
                return 12.00;
            } else {
                return 12.00 + ((horas - 1) * 8.00);
            }
        } else { // Moto
            // Primeira hora: R$8,00, adicional: R$5,00
            if (horas <= 1) {
                return 8.00;
            } else {
                return 8.00 + ((horas - 1) * 5.00);
            }
        }
    }

    // Pesquisa veículo por placa
    public void pesquisarVeiculoPorPlaca(String placa) {
        System.out.println("\n=== RESULTADO DA PESQUISA ===");
        boolean encontrado = false;
        
        for (Ticket ticket : tickets) {
            if (ticket.getVeiculo().getPlaca().equalsIgnoreCase(placa)) {
                encontrado = true;
                
                if (ticket.isAtivo()) {
                    System.out.println("\u2705 VEÍCULO ENCONTRADO NO ESTACIONAMENTO");
                    System.out.println("\nTicket #" + ticket.getId());
                    System.out.println("Tipo: " + ticket.getVeiculo().getTipo());
                    System.out.println("Placa: " + ticket.getVeiculo().getPlaca());
                    System.out.println("Modelo: " + ticket.getVeiculo().getModelo());
                    System.out.println("Cor: " + ticket.getVeiculo().getCor());
                    System.out.println("Vaga: " + ticket.getVaga().getNumero());
                    System.out.println("Horário de entrada: " + ticket.getHoraEntrada());
                    System.out.println("Tempo de permanência: " + ticket.calcularMinutosPermanencia() + " minutos");
                    return; // Veículo ativo encontrado
                }
            }
        }
        
        if (encontrado) {
            System.out.println("⚠️ Veículo com placa " + placa + " já saiu do estacionamento.");
            System.out.println("Consulte o histórico completo para mais detalhes.");
        } else {
            System.out.println("❌ Veículo com placa " + placa + " não encontrado.");
        }
    }

    // Exibe o status das vagas
    public void exibirStatusVagas() {
        System.out.println("\n=== STATUS DAS VAGAS ===");
        int livres = 0;
        int ocupadas = 0;
        
        for (Vaga vaga : vagas) {
            String status = vaga.isOcupada() ? "OCUPADA" : "LIVRE";
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
                System.out.println("Tipo: " + ticket.getVeiculo().getTipo());
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

    // Exibe veículos por tipo (Carro ou Moto)
    public void exibirVeiculosPorTipo(String tipo) {
        System.out.println("\n=== VEÍCULOS DO TIPO: " + tipo.toUpperCase() + " ===");
        boolean encontrou = false;
        int totalAtivos = 0;
        int totalFinalizados = 0;
        
        for (Ticket ticket : tickets) {
            if (ticket.getVeiculo().getTipo().equalsIgnoreCase(tipo)) {
                encontrou = true;
                
                if (ticket.isAtivo()) {
                    totalAtivos++;
                    System.out.println("\nATIVO - Ticket #" + ticket.getId());
                    System.out.println("Placa: " + ticket.getVeiculo().getPlaca());
                    System.out.println("Modelo: " + ticket.getVeiculo().getModelo());
                    System.out.println("Cor: " + ticket.getVeiculo().getCor());
                    System.out.println("Vaga: " + ticket.getVaga().getNumero());
                    System.out.println("Entrada: " + ticket.getHoraEntrada());
                    System.out.println("Tempo decorrido: " + ticket.calcularMinutosPermanencia() + " minutos");
                } else {
                    totalFinalizados++;
                }
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhum registro encontrado para o tipo: " + tipo);
        } else {
            System.out.println("\n--- RESUMO ---");
            System.out.println(tipo + "s ativos no estacionamento: " + totalAtivos);
            System.out.println(tipo + "s já finalizados (histórico): " + totalFinalizados);
            System.out.println("Total de " + tipo + "s: " + (totalAtivos + totalFinalizados));
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
        System.out.println("Tarifa - Carro: R$12 (1ª hora) + R$8 (adicional)");
        System.out.println("Tarifa - Moto: R$8 (1ª hora) + R$5 (adicional)");
    }

    // Exibe relatório de arrecadação por dia
    public void exibirRelatorioArrecadacaoPorDia() {
        System.out.println("\n=== RELATÓRIO DE ARRECADAÇÃO POR DIA ===");
        
        if (arrecadacaoPorDia.isEmpty()) {
            System.out.println("Nenhuma arrecadação registrada ainda.");
            return;
        }
        
        double totalGeral = 0.0;
        for (Map.Entry<LocalDate, Double> entrada : arrecadacaoPorDia.entrySet()) {
            System.out.println("\nData: " + entrada.getKey());
            System.out.println("Total arrecadado: R$ " + String.format("%.2f", entrada.getValue()));
            totalGeral += entrada.getValue();
        }
        
        System.out.println("\n--- TOTAL GERAL ---");
        System.out.println("Arrecadação total de todos os dias: R$ " + String.format("%.2f", totalGeral));
    }

    // Exibe a fila de espera
    public void exibirFilaEspera() {
        System.out.println("\n=== FILA DE ESPERA ===");
        
        if (filaEspera.isEmpty()) {
            System.out.println("Não há veículos na fila de espera.");
            return;
        }
        
        System.out.println("Total de veículos aguardando: " + filaEspera.size());
        System.out.println("\nVeículos na fila (ordem de chegada):");
        
        int posicao = 1;
        for (Veiculo veiculo : filaEspera) {
            System.out.println("\n" + posicao + "ª posição:");
            System.out.println("  Tipo: " + veiculo.getTipo());
            System.out.println("  Placa: " + veiculo.getPlaca());
            System.out.println("  Modelo: " + veiculo.getModelo());
            System.out.println("  Cor: " + veiculo.getCor());
            posicao++;
        }
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
