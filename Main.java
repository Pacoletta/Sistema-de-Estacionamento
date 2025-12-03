import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        
        // Criação do estacionamento com 20 vagas e tarifa de R$ 5,00 por hora
        Estacionamento estacionamento = new Estacionamento("Estacionamento Central", 20, 5.00);
        
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║ SISTEMA DE GERENCIAMENTO DE ESTACIONAMENTO ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("Bem-vindo ao " + estacionamento.getNome());
        System.out.println("Tarifa: R$ " + String.format("%.2f", estacionamento.getTarifaPorHora()) + " por hora\n");
        
        boolean continuar = true;
        
        while (continuar) {
            exibirMenu();
            
            System.out.print("\nEscolha uma opção: ");
            int opcao = kb.nextInt();
            kb.nextLine(); // Limpa o buffer
            
            System.out.println();
            
            switch (opcao) {
                case 1:
                    registrarEntradaVeiculo(kb, estacionamento);
                    break;
                    
                case 2:
                    registrarSaidaVeiculo(kb, estacionamento);
                    break;
                    
                case 3:
                    pesquisarVeiculoPorPlaca(kb, estacionamento);
                    break;
                    
                case 4:
                    estacionamento.exibirStatusVagas();
                    break;
                    
                case 5:
                    estacionamento.exibirVeiculosEstacionados();
                    break;
                    
                case 6:
                    consultarPorTipo(kb, estacionamento);
                    break;
                    
                case 7:
                    estacionamento.exibirHistorico();
                    break;
                    
                case 8:
                    estacionamento.exibirRelatorioFinanceiro();
                    break;
                    
                case 9:
                    estacionamento.exibirFilaEspera();
                    break;
                    
                case 10:
                    estacionamento.exibirRelatorioArrecadacaoPorDia();
                    break;
                    
                case 11:
                    executarTestes(estacionamento);
                    break;
                    
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            if (continuar) {
                System.out.println("\nPressione ENTER para continuar...");
                kb.nextLine();
            }
        }
        
        kb.close();
    }
    // Feita por AI
    private static void exibirMenu() {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│           MENU PRINCIPAL               │");
        System.out.println("├────────────────────────────────────────┤");
        System.out.println("│ 1  - Registrar Entrada de Veículo      │");
        System.out.println("│ 2  - Registrar Saída de Veículo        │");
        System.out.println("│ 3  - Pesquisar Veículo por Placa       │");
        System.out.println("│ 4  - Ver Status das Vagas              │");
        System.out.println("│ 5  - Ver Veículos Estacionados         │");
        System.out.println("│ 6  - Consultar por Tipo (Carro/Moto)   │");
        System.out.println("│ 7  - Ver Histórico Completo            │");
        System.out.println("│ 8  - Relatório Financeiro              │");
        System.out.println("│ 9  - Ver Fila de Espera                │");
        System.out.println("│ 10 - Relatório Arrecadação por Dia     │");
        System.out.println("│ 11 - Executar Testes (Demo)            │");
        System.out.println("│ 0  - Sair                              │");
        System.out.println("└────────────────────────────────────────┘");
    }
    
    private static void registrarEntradaVeiculo(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("=== REGISTRO DE ENTRADA ===");
        
        System.out.println("\nSelecione o tipo de veículo:");
        System.out.println("  1 - Carro");
        System.out.println("  2 - Moto");
        System.out.print("Opção: ");
        int tipoOpcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o Scanner
        String tipo = (tipoOpcao == 1) ? "Carro" : "Moto";
        
        System.out.println("\nSelecione o perfil do condutor:");
        System.out.println("  1 - Comum");
        System.out.println("  2 - Idoso");
        System.out.println("  3 - Deficiente (PCD)");
        System.out.print("Opção: ");
        int perfilOpcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o Scanner
        String perfilCondutor;
        if (perfilOpcao == 2) {
            perfilCondutor = "idoso";
        } else if (perfilOpcao == 3) {
            perfilCondutor = "deficiente";
        } else {
            perfilCondutor = "comum";
        }
        
        System.out.println("\n--- Dados do " + tipo + " ---");
        
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine().toUpperCase();
        
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Digite a cor do veículo: ");
        String cor = scanner.nextLine();
        
        // Feita por AI
        System.out.println("\n--- Horário de Entrada ---");
        System.out.println("  1 - Usar data/hora ATUAL (automático)");
        System.out.println("  2 - Informar DATA manualmente (horário atual)");
        System.out.print("Opção: ");
        int horaOpcao = scanner.nextInt();
        scanner.nextLine();
        
        Veiculo veiculo = new Veiculo(placa, modelo, cor, tipo, perfilCondutor);
        
        if (horaOpcao == 2) {
            // Entrada com data manual e hora atual
            System.out.println("\n--- Informe a DATA de entrada ---");
            System.out.print("Digite o ano: ");
            int ano = scanner.nextInt();
            System.out.print("Digite o mês (1-12): ");
            int mes = scanner.nextInt();
            System.out.print("Digite o dia: ");
            int dia = scanner.nextInt();
            scanner.nextLine();
            
            // Pega horario atual
            java.time.LocalTime horaAtual = java.time.LocalTime.now();
            int hora = horaAtual.getHour();
            int minuto = horaAtual.getMinute();
            
            System.out.println("Horário capturado automaticamente: " + hora + ":" + String.format("%02d", minuto));
            
            estacionamento.registrarEntradaManual(veiculo, ano, mes, dia, hora, minuto);
        } else {
            // Entrada automática (data e hora atuais)
            estacionamento.registrarEntrada(veiculo);
        }
    }
    
    private static void registrarSaidaVeiculo(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("=== REGISTRO DE SAÍDA ===");
        
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine().toUpperCase();
        
        estacionamento.registrarSaida(placa);
    }
    
    private static void pesquisarVeiculoPorPlaca(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("=== PESQUISAR VEÍCULO POR PLACA ===");
        
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine().toUpperCase();
        
        estacionamento.pesquisarVeiculoPorPlaca(placa);
    }
    
    private static void consultarPorTipo(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("=== CONSULTAR POR TIPO ===");
        System.out.println("\nSelecione o tipo:");
        System.out.println("  1 - Carros");
        System.out.println("  2 - Motos");
        System.out.print("Opção: ");
        int tipoOpcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        String tipo = (tipoOpcao == 1) ? "Carro" : "Moto";
        
        estacionamento.exibirVeiculosPorTipo(tipo);
    }
    
    private static void executarTestes(Estacionamento estacionamento) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║ EXECUTANDO TESTES DE DEMONSTRAÇÃO          ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("\nEste modo demonstra o funcionamento do sistema com dados de exemplo.\n");
        
        // Teste 1: Registrar entradas
        System.out.println("--- TESTE 1: Registrando entradas de veículos ---");
        Veiculo v1 = new Veiculo("ABC1234", "Fiat Uno", "Branco", "Carro", "comum");
        Veiculo v2 = new Veiculo("XYZ5678", "Honda Civic", "Preto", "Carro", "idoso");
        Veiculo v3 = new Veiculo("DEF9012", "Honda CG 160", "Vermelha", "Moto", "deficiente");
        
        estacionamento.registrarEntrada(v1);
        System.out.println();
        
        estacionamento.registrarEntrada(v2);
        System.out.println();
        
        estacionamento.registrarEntrada(v3);
        System.out.println();
        
        // Teste 2: Visualizar status
        estacionamento.exibirStatusVagas();
        
        // Teste 3: Visualizar veículos estacionados
        estacionamento.exibirVeiculosEstacionados();
        
        // Teste 4: Simular passagem de tempo e registrar saída
        System.out.println("\n--- TESTE 2: Simulando saída após 5 segundos ---");
        System.out.println("Aguardando 5 segundos para simular permanência...");
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        estacionamento.registrarSaida("ABC1234");
        
        // Teste 5: Relatório financeiro
        estacionamento.exibirRelatorioFinanceiro();
        
        System.out.println("\nTestes concluídos!");
    }
}
