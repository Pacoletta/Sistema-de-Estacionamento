import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
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
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
            
            System.out.println();
            
            switch (opcao) {
                case 1:
                    registrarEntradaVeiculo(scanner, estacionamento);
                    break;
                    
                case 2:
                    registrarSaidaVeiculo(scanner, estacionamento);
                    break;
                    
                case 3:
                    estacionamento.exibirStatusVagas();
                    break;
                    
                case 4:
                    estacionamento.exibirVeiculosEstacionados();
                    break;
                    
                case 5:
                    estacionamento.exibirHistorico();
                    break;
                    
                case 6:
                    estacionamento.exibirRelatorioFinanceiro();
                    break;
                    
                case 7:
                    alterarTarifa(scanner, estacionamento);
                    break;
                    
                case 8:
                    executarTestes(estacionamento);
                    break;
                    
                case 0:
                    System.out.println("👋 Encerrando o sistema. Até logo!");
                    continuar = false;
                    break;
                    
                default:
                    System.out.println("❌ Opção inválida! Tente novamente.");
            }
            
            if (continuar) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("\n┌────────────────────────────────────┐");
        System.out.println("│           MENU PRINCIPAL           │");
        System.out.println("├────────────────────────────────────┤");
        System.out.println("│ 1 - Registrar Entrada de Veículo   │");
        System.out.println("│ 2 - Registrar Saída de Veículo     │");
        System.out.println("│ 3 - Ver Status das Vagas           │");
        System.out.println("│ 4 - Ver Veículos Estacionados      │");
        System.out.println("│ 5 - Ver Histórico Completo         │");
        System.out.println("│ 6 - Relatório Financeiro           │");
        System.out.println("│ 7 - Alterar Tarifa                 │");
        System.out.println("│ 8 - Executar Testes (Demo)         │");
        System.out.println("│ 0 - Sair                           │");
        System.out.println("└────────────────────────────────────┘");
    }
    
    private static void registrarEntradaVeiculo(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("=== REGISTRO DE ENTRADA ===");
        
        System.out.print("Tipo de veículo (1-Carro / 2-Moto): ");
        int tipoOpcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        String tipo = (tipoOpcao == 1) ? "Carro" : "Moto";
        
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine().toUpperCase();
        
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Digite a cor do veículo: ");
        String cor = scanner.nextLine();
        
        Veiculo veiculo = new Veiculo(placa, modelo, cor, tipo);
        estacionamento.registrarEntrada(veiculo);
    }
    
    private static void registrarSaidaVeiculo(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("=== REGISTRO DE SAÍDA ===");
        
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine().toUpperCase();
        
        estacionamento.registrarSaida(placa);
    }
    
    private static void alterarTarifa(Scanner scanner, Estacionamento estacionamento) {
        System.out.println("=== ALTERAR TARIFA ===");
        System.out.println("Tarifa atual: R$ " + String.format("%.2f", estacionamento.getTarifaPorHora()));
        
        System.out.print("Digite a nova tarifa por hora: R$ ");
        double novaTarifa = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer
        
        estacionamento.setTarifaPorHora(novaTarifa);
        System.out.println("✅ Tarifa alterada com sucesso para R$ " + String.format("%.2f", novaTarifa));
    }
    
    private static void executarTestes(Estacionamento estacionamento) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║ EXECUTANDO TESTES DE DEMONSTRAÇÃO          ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("\nEste modo demonstra o funcionamento do sistema com dados de exemplo.\n");
        
        // Teste 1: Registrar entradas
        System.out.println("--- TESTE 1: Registrando entradas de veículos ---");
        Veiculo v1 = new Veiculo("ABC1234", "Fiat Uno", "Branco", "Carro");
        Veiculo v2 = new Veiculo("XYZ5678", "Honda Civic", "Preto", "Carro");
        Veiculo v3 = new Veiculo("DEF9012", "Honda CG 160", "Vermelha", "Moto");
        
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
        
        System.out.println("\n✅ Testes concluídos!");
    }
}
