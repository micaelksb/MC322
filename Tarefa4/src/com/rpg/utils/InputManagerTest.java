package com.rpg.utils;

public class InputManagerTest {

    public static void main(String[] args) {
        System.out.println("--- Testando InputManager ---");

        // Teste lerInteiro
        System.out.println("\nTeste de lerInteiro:");
        int numero = InputManager.lerInteiro("Digite um número entre 1 e 10: ", 1, 10);
        System.out.println("Número lido: " + numero);

        // Teste lerString
        System.out.println("\nTeste de lerString:");
        String texto = InputManager.lerString("Digite uma frase: ");
        System.out.println("Frase lida: " + texto);

        // Teste lerSimNao
        System.out.println("\nTeste de lerSimNao:");
        boolean simNao = InputManager.lerSimNao("Você gosta de Java?");
        System.out.println("Resposta: " + (simNao ? "Sim" : "Não"));

        // Teste esperarEnter
        System.out.println("\nTeste de esperarEnter:");
        InputManager.esperarEnter("Pressione Enter para finalizar os testes.");

        // Fechar o scanner
        InputManager.fecharScanner();
        System.out.println("--- Testes do InputManager concluídos ---");
    }
}

