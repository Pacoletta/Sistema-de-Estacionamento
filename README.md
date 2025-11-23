# Sistema de Gerenciamento de Estacionamento

Sistema completo em Java para gerenciamento de estacionamento com controle de entrada/saída de veículos, cálculo de tempo de permanência e valores.

## 📁 Estrutura do Projeto

- `Veiculo.java` - Classe que representa um veículo (placa, modelo, cor)
- `Vaga.java` - Classe que representa uma vaga de estacionamento
- `Ticket.java` - Classe que representa um ticket de estacionamento com cálculos de tempo
- `Estacionamento.java` - Classe principal que gerencia todo o sistema
- `Main.java` - Interface de usuário com menu interativo

## ⚙️ Funcionalidades

1. **Registrar Entrada de Veículo** - Cadastra veículo e aloca vaga automaticamente
2. **Registrar Saída de Veículo** - Calcula tempo de permanência e valor a pagar
3. **Visualizar Status das Vagas** - Mostra vagas livres e ocupadas
4. **Visualizar Veículos Estacionados** - Lista todos os veículos atualmente no estacionamento
5. **Histórico Completo** - Exibe todos os tickets (ativos e finalizados)
6. **Relatório Financeiro** - Mostra total arrecadado
7. **Alterar Tarifa** - Permite modificar o valor da tarifa por hora
8. **Modo de Testes** - Executa demonstração automática do sistema

## 🔧 Tecnologias Utilizadas

- **Java 8+**
- **java.time** - Para manipulação de datas e horas
  - `LocalDateTime` - Captura data/hora de entrada e saída
  - `ChronoUnit` - Cálculo de diferenças de tempo
  - `Math.ceil()` - Arredondamento de horas para cima

## 🚀 Como Compilar e Executar

### Compilar todos os arquivos:
```bash
javac *.java
```

### Executar o programa:
```bash
java Main
```

## 💡 Conceitos Aplicados

- **Orientação a Objetos**: Classes, encapsulamento, getters/setters
- **Coleções**: ArrayList para gerenciar vagas e tickets
- **API java.time**: Manipulação moderna de data/hora
- **Lógica de Negócio**: Cálculo de tarifas, controle de vagas
- **Interface de Usuário**: Menu interativo via console

## 📊 Tipos de Vagas

O sistema cria automaticamente diferentes tipos de vagas:
- **Vagas 1-3**: Deficientes
- **Vagas 4-6**: Idosos
- **Vagas 7-20**: Comuns

## 💰 Cálculo de Valores

- Tempo de permanência calculado em minutos
- Conversão para horas com arredondamento para cima
- Valor = Horas × Tarifa por Hora

## 📝 Exemplo de Uso

```
1. Registrar entrada do veículo ABC1234
2. Sistema aloca vaga automaticamente
3. Veículo permanece 125 minutos
4. Registrar saída
5. Sistema calcula: 3 horas × R$ 5,00 = R$ 15,00
```

## 👨‍💻 Autor

Desenvolvido como trabalho acadêmico utilizando conceitos de Programação Orientada a Objetos e API java.time.
# Sistema-de-Estacionamento
