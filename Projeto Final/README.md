# Gerenciador de Finanças Pessoais

## Projeto Final - MC322A - Programação Orientada a Objetos

Este projeto implementa um sistema de gerenciamento de finanças pessoais em Java, atendendo a todos os requisitos do projeto final da disciplina MC322A.

### Estrutura do Projeto

*   `src/main/java/com/financas/`: Contém todo o código-fonte da aplicação.
*   `src/test/java/com/financas/`: Contém os testes unitários.
*   `bin/`: Diretório de saída para os arquivos `.class` compilados.
*   `bin-test/`: Diretório de saída para os arquivos `.class` de teste compilados.
*   `dados_financeiros.dat`: Arquivo de persistência de dados (criado após a primeira execução).

### Requisitos

*   **Java Development Kit (JDK):** Versão 11 ou superior.
*   **JUnit:** Necessário para compilar e executar os testes unitários.

### 1. Compilação

O projeto foi compilado usando o `javac`.

1.  **Navegue até o diretório do projeto:**
    ```bash
    cd MC322A_Projeto_Final
    ```

2.  **Compile o código-fonte:**
    ```bash
    mkdir -p bin
    javac -d bin -sourcepath src/main/java src/main/java/com/financas/*.java
    ```

### 2. Execução da Aplicação

1.  **Execute a classe principal:**
    ```bash
    java -cp bin com.financas.Main
    ```
    A Interface Gráfica (GUI) da aplicação será iniciada.

### 3. Testes Unitários

Os testes unitários foram desenvolvidos utilizando **JUnit 4**.

1.  **Compile os testes:**
    *   **Nota:** Você precisará dos arquivos `junit4.jar` e `hamcrest-core.jar` no seu classpath. Se estiver em um ambiente Linux com `apt-get`, eles podem ser encontrados em `/usr/share/java/`.
    ```bash
    mkdir -p bin-test
    javac -d bin-test -cp bin:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core.jar -sourcepath src/test/java src/test/java/com/financas/TesteGerenciadorFinancas.java
    ```

2.  **Execute os testes:**
    ```bash
    java -cp bin:bin-test:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core.jar org.junit.runner.JUnitCore com.financas.TesteGerenciadorFinancas
    ```
    A saída deve indicar que **23 testes foram executados com sucesso (OK)**.

### 4. Funcionalidades Chave

*   **Contas:** Adicione contas correntes e poupanças.
*   **Cartões de Crédito:** Cadastre múltiplos cartões com datas de fechamento e vencimento.
*   **Transações:** Registre receitas, despesas e compras no cartão.
*   **Relatórios:** Gere relatórios por período e por categoria.
*   **Persistência:** Os dados são salvos automaticamente ao fechar a aplicação e carregados ao iniciar.
