# Sistema de Gerenciamento de Estacionamento 🚗

Sistema completo em Java para gerenciamento de estacionamento com controle de entrada/saída de veículos, cálculo de tempo de permanência, valores e funcionalidades avançadas.

## 📁 Estrutura do Projeto

- `Veiculo.java` - Classe que representa um veículo (placa, modelo, cor, tipo, perfil do condutor)
- `Vaga.java` - Classe que representa uma vaga de estacionamento (comum, idoso, deficiente)
- `Ticket.java` - Classe que representa um ticket de estacionamento com cálculos de tempo e valores
- `Estacionamento.java` - Classe principal que gerencia todo o sistema (vagas, tickets, fila de espera)
- `Main.java` - Interface de usuário via console com menu interativo
- `EstacionamentoGUI.java` - Interface gráfica moderna usando Java Swing

## ⚙️ Funcionalidades Principais

### Requisitos Obrigatórios ✅

1. **Registrar Entrada de Veículo**

   - Cadastra placa, modelo, cor, tipo (Carro/Moto)
   - Aloca vaga automaticamente por perfil do condutor
   - Permite entrada com hora atual OU manual (apenas hora/minuto)
   - Bloqueia entrada quando estacionamento lotado

2. **Registrar Saída de Veículo**

   - Calcula tempo de permanência (sem fração de hora)
   - Valores: Carro R$12 (1ª hora) + R$8 adicional | Moto R$8 (1ª hora) + R$5 adicional
   - Registra valor no total arrecadado
   - Exibe comprovante completo com valor a pagar

3. **Visualizar Status das Vagas** - Mostra vagas livres e ocupadas (20 vagas no total)

4. **Visualizar Veículos Estacionados** - Lista todos os veículos atualmente no estacionamento

5. **Pesquisar Veículo por Placa** - Busca veículo e exibe informações de entrada

6. **Relatório Financeiro** - Exibe total arrecadado com todas as saídas registradas

### Funcionalidades Extras (+9 pontos) 🌟

7. **Diferenciação Carro/Moto** - Tarifas específicas por tipo de veículo (+3 pontos)

8. **Fila de Espera Automática** - Quando lotado, veículos aguardam em fila e entram automaticamente ao liberar vaga (+3 pontos)

9. **Relatório por Dia** - Total arrecadado separado por data (+3 pontos)

### Funcionalidades Adicionais 🚀

10. **Vagas Especiais** - Sistema de priorização para deficientes e idosos (3 vagas PCD + 3 idosos + 14 comuns)

11. **Consulta por Tipo** - Filtra e exibe apenas Carros ou Motos

12. **Histórico Completo** - Exibe todos os tickets (ativos e finalizados)

13. **Visualizar Fila de Espera** - Mostra veículos aguardando vaga

14. **Interface Gráfica** - GUI moderna com abas, tabelas e janelas de relatórios com todos os dados

15. **Modo de Testes** - Demonstração automática do sistema

## 🔧 Tecnologias Utilizadas

- **Java 8+**
- **java.time** - LocalDateTime, ChronoUnit para cálculos de tempo
- **Collections Framework** - ArrayList, Queue (LinkedList), Map (HashMap)
- **Java Swing** - Interface gráfica (JFrame, JTabbedPane, JTable, JDialog, JSpinner)
- **I/O Streams** - Scanner, PrintStream redirection

## 📥 Como Baixar e Instalar

### Pré-requisitos

- **Java JDK 8 ou superior** instalado
  - Verifique: `java -version`
  - Download: https://www.oracle.com/java/technologies/downloads/

### Passo 1: Clonar o Repositório

**Opção 1 - Usando Git:**

```bash
git clone https://github.com/Pacoletta/Sistema-de-Estacionamento.git
cd Sistema-de-Estacionamento
```

**Opção 2 - Baixar ZIP:**

1. Clique no botão verde **Code** no GitHub
2. Clique em **Download ZIP**
3. Extraia o arquivo ZIP em uma pasta de sua preferência
4. Abra o terminal/prompt na pasta extraída

## 🚀 Como Compilar e Executar

### Passo 2: Compilar o Projeto

```bash
javac *.java
```

### Passo 3: Executar o Sistema

**Opção 1 - Interface Console (Menu de Texto):**

```bash
java Main
```

**Opção 2 - Interface Gráfica (GUI Moderna):**

```bash
java EstacionamentoGUI
```

### Comandos Completos (Windows - PowerShell/CMD)

```powershell
# Navegar até a pasta do projeto
cd caminho\para\Sistema-de-Estacionamento

# Compilar todos os arquivos
javac *.java

# Executar via Console
java Main

# OU Executar via Interface Gráfica
java EstacionamentoGUI
```

### Comandos Completos (Linux/Mac - Terminal)

```bash
# Navegar até a pasta do projeto
cd caminho/para/Sistema-de-Estacionamento

# Compilar todos os arquivos
javac *.java

# Executar via Console
java Main

# OU Executar via Interface Gráfica
java EstacionamentoGUI
```

## 💡 Conceitos Aplicados

## 📊 Sistema de Vagas

## 💰 Cálculo de Valores (Feita por AI) 🤖

### Carros:

- 1ª hora: **R$ 12,00**
- Hora adicional: **R$ 8,00/hora**
- Exemplo: 2h30min = 3 horas → R$ 12 + (2 × R$ 8) = **R$ 28,00**

### Motos:

- 1ª hora: **R$ 8,00**
- Hora adicional: **R$ 5,00/hora**
- Exemplo: 2h30min = 3 horas → R$ 8 + (2 × R$ 5) = **R$ 18,00**

**Observação:** Não existe fração de hora. Qualquer minuto adicional conta como hora completa.

### Lógica de Alocação (Feita por AI) 🤖

- Condutores PCD e idosos têm prioridade em suas vagas especiais
- Se vagas especiais estiverem ocupadas, podem usar vagas comuns
- Condutores comuns só podem usar vagas comuns
- Sistema de fila de espera quando todas as vagas estão ocupadas

## 💰 Cálculo de Valores (Feita por AI) 🤖

### Carros:

- 1ª hora: **R$ 12,00**
- Hora adicional: **R$ 8,00/hora**
- Exemplo: 2h30min = 3 horas → R$ 12 + (2 × R$ 8) = **R$ 28,00**

## 📝 Exemplo de Uso

### Via Console:

```
╔════════════════════════════════════════════╗
║ SISTEMA DE GERENCIAMENTO DE ESTACIONAMENTO ║
╚════════════════════════════════════════════╝

1. Registrar Entrada → ABC1234 (Carro, Comum)
### Via Interface Gráfica:

1. Abra a aba **"📥 Entrada/Saída"**
2. Preencha: placa, modelo, cor, tipo, perfil
3. (Opcional) Marque **"Informar horário manualmente"** e escolha hora/minuto
4. Clique em **"✅ Registrar Entrada"**
5. Visualize o comprovante completo na janela popup
6. Acesse **"🚙 Veículos Estacionados"** para ver tabela em tempo real
7. Para saída, informe a placa e clique em **"🚪 Registrar Saída"**
8. Receba comprovante com **valor a pagar** em destaque
9. Consulte relatórios na aba **"📊 Relatórios"** (todos em janelas popup)

## 🎯 Diferenciais do Projeto

- ✅ **Atende 100% dos requisitos obrigatórios**
- ✅ **Implementa TODAS as funcionalidades extras** (+9 pontos)
- ✅ **Sistema de vagas especiais** (acessibilidade)
- ✅ **Dupla interface** (Console + GUI completa)
- ✅ **Entrada manual de horário** (ano/mês/dia automáticos)
- ✅ **Comprovantes visuais** com todos os dados
- ✅ **Código comentado** identificando partes complexas (AI) e simples
- ✅ **POO bem aplicada** (5 classes independentes)
- ✅ **Uso de estruturas avançadas** (Queue, Map, ArrayList)
- ✅ **.gitignore** configurado (ignora .class e .iml)
- ✅ **Implementa TODAS as funcionalidades extras** (+9 pontos)
- ✅ **Sistema de vagas especiais** (acessibilidade)
- ✅ **Dupla interface** (Console + GUI)
- ✅ **Código comentado** identificando partes complexas (AI) e simples
- ✅ **POO bem aplicada** (5 classes independentes)
- ✅ **Uso de estruturas avançadas** (Queue, Map, ArrayList)

## 📚 Conceitos de Programação Aplicados

| Conceito                | Implementação                                     |
| ----------------------- | ------------------------------------------------- |
| **POO**                 | 5 classes com encapsulamento, herança implícita   |
## 👨‍💻 Autores

- **Desenvolvimento básico**: Implementação manual
- **Funcionalidades complexas**: Desenvolvidas com auxílio de IA
  - Sistema de fila de espera
  - Cálculo de valores diferenciados
  - Busca inteligente de vagas por perfil
  - Interface gráfica completa com popups

Desenvolvido como trabalho acadêmico - Programação Orientada a Objetos

---

## 📋 Observações Importantes

- **Arquivos .class** não estão no repositório (gerados na compilação)
- **Arquivo .iml** (IntelliJ) ignorado pelo .gitignore
- **Apenas código-fonte .java** está versionado no Git
- **README.md** e **.gitignore** incluídos para documentação
5. Sistema calcula: 3 horas × R$ 5,00 = R$ 15,00
```

## 👨‍💻 Autor

Desenvolvido como trabalho acadêmico
