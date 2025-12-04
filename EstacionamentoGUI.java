import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EstacionamentoGUI extends JFrame {
    private Estacionamento estacionamento;
    private JTextArea outputArea;
    private DefaultTableModel vagasTableModel;
    private DefaultTableModel veiculosTableModel;
    
    public EstacionamentoGUI() {
        estacionamento = new Estacionamento("Estacionamento Central", 20, 5.00);
        
        setTitle("Sistema de Gerenciamento de Estacionamento");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout principal
        setLayout(new BorderLayout(10, 10));
        
        // Painel superior com título
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Painel central com abas
        JTabbedPane tabbedPane = createTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        
        // Painel de saída/log
        JPanel outputPanel = createOutputPanel();
        add(outputPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(41, 128, 185));
        panel.setPreferredSize(new Dimension(getWidth(), 80));
        
        JLabel titleLabel = new JLabel("🚗 SISTEMA DE ESTACIONAMENTO 🚗");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        panel.add(titleLabel);
        return panel;
    }
    
    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Aba 1: Entrada/Saída
        JPanel entradaSaidaPanel = createEntradaSaidaPanel();
        tabbedPane.addTab("📥 Entrada/Saída", entradaSaidaPanel);
        
        // Aba 2: Vagas
        JPanel vagasPanel = createVagasPanel();
        tabbedPane.addTab("🅿️ Status das Vagas", vagasPanel);
        
        // Aba 3: Veículos
        JPanel veiculosPanel = createVeiculosPanel();
        tabbedPane.addTab("🚙 Veículos Estacionados", veiculosPanel);
        
        // Aba 4: Relatórios
        JPanel relatoriosPanel = createRelatoriosPanel();
        tabbedPane.addTab("📊 Relatórios", relatoriosPanel);
        
        // Aba 5: Pesquisa
        JPanel pesquisaPanel = createPesquisaPanel();
        tabbedPane.addTab("🔍 Pesquisar", pesquisaPanel);
        
        return tabbedPane;
    }
    
    private JPanel createEntradaSaidaPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Seção de Entrada
        JPanel entradaPanel = new JPanel(new GridBagLayout());
        entradaPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(46, 204, 113), 2),
            "Registrar Entrada",
            0, 0, new Font("Arial", Font.BOLD, 16), new Color(46, 204, 113)
        ));
        
        GridBagConstraints entradaGbc = new GridBagConstraints();
        entradaGbc.insets = new Insets(5, 5, 5, 5);
        entradaGbc.anchor = GridBagConstraints.WEST;
        
        JTextField placaEntradaField = new JTextField(15);
        JTextField modeloField = new JTextField(15);
        JTextField corField = new JTextField(15);
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Carro", "Moto"});
        JComboBox<String> perfilCombo = new JComboBox<>(new String[]{"Comum", "Idoso", "Deficiente"});
        JCheckBox chkDataManual = new JCheckBox("Informar horário manualmente");
        
        // Campos de hora/minuto manual (inicialmente invisíveis)
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JSpinner spinnerHora = new JSpinner(new SpinnerNumberModel(java.time.LocalTime.now().getHour(), 0, 23, 1));
        JSpinner spinnerMinuto = new JSpinner(new SpinnerNumberModel(java.time.LocalTime.now().getMinute(), 0, 59, 1));
        
        dataPanel.add(new JLabel("Hora:"));
        dataPanel.add(spinnerHora);
        dataPanel.add(new JLabel("Minuto:"));
        dataPanel.add(spinnerMinuto);
        dataPanel.setVisible(false);
        
        chkDataManual.addActionListener(e -> dataPanel.setVisible(chkDataManual.isSelected()));
        
        // Adicionar campos de entrada
        entradaGbc.gridx = 0; entradaGbc.gridy = 0;
        entradaPanel.add(new JLabel("Placa:"), entradaGbc);
        entradaGbc.gridx = 1;
        entradaPanel.add(placaEntradaField, entradaGbc);
        
        entradaGbc.gridx = 0; entradaGbc.gridy = 1;
        entradaPanel.add(new JLabel("Modelo:"), entradaGbc);
        entradaGbc.gridx = 1;
        entradaPanel.add(modeloField, entradaGbc);
        
        entradaGbc.gridx = 0; entradaGbc.gridy = 2;
        entradaPanel.add(new JLabel("Cor:"), entradaGbc);
        entradaGbc.gridx = 1;
        entradaPanel.add(corField, entradaGbc);
        
        entradaGbc.gridx = 0; entradaGbc.gridy = 3;
        entradaPanel.add(new JLabel("Tipo:"), entradaGbc);
        entradaGbc.gridx = 1;
        entradaPanel.add(tipoCombo, entradaGbc);
        
        entradaGbc.gridx = 0; entradaGbc.gridy = 4;
        entradaPanel.add(new JLabel("Perfil:"), entradaGbc);
        entradaGbc.gridx = 1;
        entradaPanel.add(perfilCombo, entradaGbc);
        
        entradaGbc.gridx = 0; entradaGbc.gridy = 5;
        entradaGbc.gridwidth = 2;
        entradaPanel.add(chkDataManual, entradaGbc);
        
        entradaGbc.gridx = 0; entradaGbc.gridy = 6;
        entradaGbc.gridwidth = 2;
        entradaPanel.add(dataPanel, entradaGbc);
        
        JButton btnEntrada = new JButton("✅ Registrar Entrada");
        btnEntrada.setBackground(new Color(46, 204, 113));
        btnEntrada.setForeground(Color.WHITE);
        btnEntrada.setFont(new Font("Arial", Font.BOLD, 14));
        btnEntrada.addActionListener(e -> {
            String placa = placaEntradaField.getText().toUpperCase().trim();
            String modelo = modeloField.getText().trim();
            String cor = corField.getText().trim();
            String tipo = (String) tipoCombo.getSelectedItem();
            String perfilSelecionado = (String) perfilCombo.getSelectedItem();
            String perfil = perfilSelecionado.toLowerCase();
            
            if (placa.isEmpty() || modelo.isEmpty() || cor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Veiculo veiculo = new Veiculo(placa, modelo, cor, tipo, perfil);
            
            String resultado;
            
            // Verifica se é entrada manual ou automática
            if (chkDataManual.isSelected()) {
                java.time.LocalDateTime agora = java.time.LocalDateTime.now();
                int ano = agora.getYear();
                int mes = agora.getMonthValue();
                int dia = agora.getDayOfMonth();
                int hora = (Integer) spinnerHora.getValue();
                int minuto = (Integer) spinnerMinuto.getValue();
                
                resultado = capturarSaidaConsole(() -> 
                    estacionamento.registrarEntradaManual(veiculo, ano, mes, dia, hora, minuto));
            } else {
                resultado = capturarSaidaConsole(() -> estacionamento.registrarEntrada(veiculo));
            }
            
            if (resultado.contains("Estacionamento lotado")) {
                JOptionPane.showMessageDialog(this, 
                    "Estacionamento Lotado!\nVeículo adicionado à fila de espera.",
                    "Fila de Espera", JOptionPane.WARNING_MESSAGE);
                appendOutput("⚠️ Estacionamento lotado - Veículo " + placa + " adicionado à fila");
            } else {
                mostrarDialogoRelatorio("Comprovante de Entrada", resultado);
                appendOutput("✅ Entrada registrada - " + placa);
            }
            
            // Limpar campos
            placaEntradaField.setText("");
            modeloField.setText("");
            corField.setText("");
            
            atualizarTabelas();
        });
        
        entradaGbc.gridx = 0; entradaGbc.gridy = 7;
        entradaGbc.gridwidth = 2;
        entradaGbc.fill = GridBagConstraints.HORIZONTAL;
        entradaPanel.add(btnEntrada, entradaGbc);
        
        // Seção de Saída
        JPanel saidaPanel = new JPanel(new GridBagLayout());
        saidaPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(231, 76, 60), 2),
            "Registrar Saída",
            0, 0, new Font("Arial", Font.BOLD, 16), new Color(231, 76, 60)
        ));
        
        GridBagConstraints saidaGbc = new GridBagConstraints();
        saidaGbc.insets = new Insets(5, 5, 5, 5);
        saidaGbc.anchor = GridBagConstraints.WEST;
        
        JTextField placaSaidaField = new JTextField(15);
        
        saidaGbc.gridx = 0; saidaGbc.gridy = 0;
        saidaPanel.add(new JLabel("Placa:"), saidaGbc);
        saidaGbc.gridx = 1;
        saidaPanel.add(placaSaidaField, saidaGbc);
        
        JButton btnSaida = new JButton("🚪 Registrar Saída");
        btnSaida.setBackground(new Color(231, 76, 60));
        btnSaida.setForeground(Color.WHITE);
        btnSaida.setFont(new Font("Arial", Font.BOLD, 14));
        btnSaida.addActionListener(e -> {
            String placa = placaSaidaField.getText().toUpperCase().trim();
            
            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite a placa!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Captura a saída com todas as informações
            String resultado = capturarSaidaConsole(() -> estacionamento.registrarSaida(placa));
            
            // Mostra em janela de diálogo
            if (resultado.contains("Veículo não encontrado")) {
                JOptionPane.showMessageDialog(this, "Veículo não encontrado ou já saiu!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                mostrarDialogoRelatorio("Comprovante de Saída", resultado);
                appendOutput("🚪 Saída registrada - " + placa);
            }
            
            placaSaidaField.setText("");
            atualizarTabelas();
        });
        
        saidaGbc.gridx = 0; saidaGbc.gridy = 1;
        saidaGbc.gridwidth = 2;
        saidaGbc.fill = GridBagConstraints.HORIZONTAL;
        saidaPanel.add(btnSaida, saidaGbc);
        
        // Adicionar painéis ao painel principal
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0.5;
        panel.add(entradaPanel, gbc);
        
        gbc.gridx = 1;
        panel.add(saidaPanel, gbc);
        
        return panel;
    }
    
    private JPanel createVagasPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String[] colunas = {"Número", "Tipo", "Status"};
        vagasTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable table = new JTable(vagasTableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnAtualizar = new JButton("🔄 Atualizar");
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAtualizar.addActionListener(e -> atualizarTabelaVagas());
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAtualizar);
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        atualizarTabelaVagas();
        
        return panel;
    }
    
    private JPanel createVeiculosPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String[] colunas = {"Ticket", "Placa", "Modelo", "Tipo", "Vaga", "Entrada", "Tempo (min)"};
        veiculosTableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable table = new JTable(veiculosTableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnAtualizar = new JButton("🔄 Atualizar");
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAtualizar.addActionListener(e -> atualizarTabelaVeiculos());
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAtualizar);
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        atualizarTabelaVeiculos();
        
        return panel;
    }
    
    private JPanel createRelatoriosPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnFinanceiro = createReportButton("💰 Relatório Financeiro", new Color(52, 152, 219));
        btnFinanceiro.addActionListener(e -> {
            String relatorio = capturarSaidaConsole(() -> estacionamento.exibirRelatorioFinanceiro());
            mostrarDialogoRelatorio("Relatório Financeiro", relatorio);
        });
        
        JButton btnPorDia = createReportButton("📅 Arrecadação por Dia", new Color(155, 89, 182));
        btnPorDia.addActionListener(e -> {
            String relatorio = capturarSaidaConsole(() -> estacionamento.exibirRelatorioArrecadacaoPorDia());
            mostrarDialogoRelatorio("Arrecadação por Dia", relatorio);
        });
        
        JButton btnHistorico = createReportButton("📜 Histórico Completo", new Color(241, 196, 15));
        btnHistorico.addActionListener(e -> {
            String relatorio = capturarSaidaConsole(() -> estacionamento.exibirHistorico());
            mostrarDialogoRelatorio("Histórico Completo", relatorio);
        });
        
        JButton btnFila = createReportButton("⏳ Fila de Espera", new Color(230, 126, 34));
        btnFila.addActionListener(e -> {
            String relatorio = capturarSaidaConsole(() -> estacionamento.exibirFilaEspera());
            mostrarDialogoRelatorio("Fila de Espera", relatorio);
        });
        
        panel.add(btnFinanceiro);
        panel.add(btnPorDia);
        panel.add(btnHistorico);
        panel.add(btnFila);
        
        return panel;
    }
    
    private JPanel createPesquisaPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField placaField = new JTextField(15);
        
        JButton btnPesquisar = new JButton("🔍 Pesquisar");
        btnPesquisar.setFont(new Font("Arial", Font.BOLD, 14));
        btnPesquisar.setBackground(new Color(52, 152, 219));
        btnPesquisar.setForeground(Color.WHITE);
        
        btnPesquisar.addActionListener(e -> {
            String placa = placaField.getText().toUpperCase().trim();
            if (!placa.isEmpty()) {
                String resultado = capturarSaidaConsole(() -> estacionamento.pesquisarVeiculoPorPlaca(placa));
                mostrarDialogoRelatorio("Pesquisa - " + placa, resultado);
                appendOutput("🔍 Pesquisa realizada para: " + placa);
            }
        });
        
        searchPanel.add(lblPlaca);
        searchPanel.add(placaField);
        searchPanel.add(btnPesquisar);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        // Painel de filtros por tipo
        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JButton btnCarros = new JButton("🚗 Ver Carros");
        btnCarros.setFont(new Font("Arial", Font.BOLD, 14));
        btnCarros.addActionListener(e -> {
            String resultado = capturarSaidaConsole(() -> estacionamento.exibirVeiculosPorTipo("Carro"));
            mostrarDialogoRelatorio("Veículos - Carros", resultado);
            appendOutput("📋 Listagem de carros exibida");
        });
        
        JButton btnMotos = new JButton("🏍️ Ver Motos");
        btnMotos.setFont(new Font("Arial", Font.BOLD, 14));
        btnMotos.addActionListener(e -> {
            String resultado = capturarSaidaConsole(() -> estacionamento.exibirVeiculosPorTipo("Moto"));
            mostrarDialogoRelatorio("Veículos - Motos", resultado);
            appendOutput("📋 Listagem de motos exibida");
        });
        
        tipoPanel.add(btnCarros);
        tipoPanel.add(btnMotos);
        
        panel.add(tipoPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Log de Atividades"));
        panel.setPreferredSize(new Dimension(getWidth(), 150));
        
        outputArea = new JTextArea(5, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnLimpar = new JButton("🗑️ Limpar Log");
        btnLimpar.addActionListener(e -> outputArea.setText(""));
        
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnLimpar);
        panel.add(btnPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JButton createReportButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(200, 100));
        return btn;
    }
    
    private void atualizarTabelaVagas() {
        vagasTableModel.setRowCount(0);
        
        // Simular acesso às vagas (como não temos getter público, vamos usar reflection ou criar método)
        // Por simplicidade, vou fazer uma chamada que popula baseado no status
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        estacionamento.exibirStatusVagas();
        
        System.out.flush();
        System.setOut(old);
        
        String output = baos.toString();
        String[] lines = output.split("\n");
        
        for (String line : lines) {
            if (line.contains("Vaga ")) {
                String[] parts = line.split(" ");
                if (parts.length >= 4) {
                    String numero = parts[1];
                    String tipo = parts[2].replace("(", "").replace("):", "");
                    String status = parts[3];
                    vagasTableModel.addRow(new Object[]{numero, tipo, status});
                }
            }
        }
    }
    
    private void atualizarTabelaVeiculos() {
        veiculosTableModel.setRowCount(0);
        
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        estacionamento.exibirVeiculosEstacionados();
        
        System.out.flush();
        System.setOut(old);
        
        String output = baos.toString();
        String[] lines = output.split("\n");
        
        String ticket = "", placa = "", modelo = "", tipo = "", vaga = "", entrada = "", tempo = "";
        
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("Ticket #")) {
                ticket = line.replace("Ticket #", "");
            } else if (line.startsWith("Tipo:")) {
                tipo = line.replace("Tipo:", "").trim();
            } else if (line.startsWith("Placa:")) {
                placa = line.replace("Placa:", "").trim();
            } else if (line.startsWith("Modelo:")) {
                modelo = line.replace("Modelo:", "").trim();
            } else if (line.startsWith("Vaga:")) {
                vaga = line.replace("Vaga:", "").trim();
            } else if (line.startsWith("Entrada:")) {
                entrada = line.replace("Entrada:", "").trim();
            } else if (line.startsWith("Tempo decorrido:")) {
                tempo = line.replace("Tempo decorrido:", "").replace("minutos", "").trim();
                veiculosTableModel.addRow(new Object[]{ticket, placa, modelo, tipo, vaga, entrada, tempo});
            }
        }
    }
    
    private void atualizarTabelas() {
        atualizarTabelaVagas();
        atualizarTabelaVeiculos();
    }
    
    private void appendOutput(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }
    
    private String capturarSaidaConsole(Runnable action) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(baos);
        java.io.PrintStream old = System.out;
        System.setOut(ps);
        
        action.run();
        
        System.out.flush();
        System.setOut(old);
        
        return baos.toString();
    }
    
    private void mostrarDialogoRelatorio(String titulo, String conteudo) {
        JDialog dialog = new JDialog(this, titulo, true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);
        
        JTextArea textArea = new JTextArea(conteudo);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        dialog.add(scrollPane, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFechar.setBackground(new Color(52, 152, 219));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.addActionListener(e -> dialog.dispose());
        
        JButton btnImprimir = new JButton("🖨️ Imprimir");
        btnImprimir.setFont(new Font("Arial", Font.BOLD, 14));
        btnImprimir.setBackground(new Color(46, 204, 113));
        btnImprimir.setForeground(Color.WHITE);
        btnImprimir.addActionListener(e -> {
            System.out.println("=".repeat(60));
            System.out.println(conteudo);
            System.out.println("=".repeat(60));
            JOptionPane.showMessageDialog(dialog, "Impresso no console!", "Impressão", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnPanel.add(btnImprimir);
        btnPanel.add(btnFechar);
        dialog.add(btnPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EstacionamentoGUI());
    }
}
