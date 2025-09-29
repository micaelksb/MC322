package com.rpg.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManager {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Exibe uma mensagem ao usuário, lê um valor inteiro do teclado e garante que o valor esteja dentro do intervalo especificado.
     * Repete a solicitação até que uma entrada válida seja fornecida.
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @param min O valor mínimo (inclusivo) permitido.
     * @param max O valor máximo (inclusivo) permitido.
     * @return O valor inteiro válido lido do usuário.
     */
    public static int lerInteiro(String mensagem, int min, int max) {
        int valor = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print(mensagem);
            try {
                valor = scanner.nextInt();
                if (valor >= min && valor <= max) {
                    entradaValida = true;
                } else {
                    System.out.println("Entrada fora do intervalo. Digite um número entre " + min + " e " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            } finally {
                scanner.nextLine(); // Consumir a quebra de linha pendente
            }
        }
        return valor;
    }

    /**
     * Exibe uma mensagem ao usuário e lê uma linha completa de texto do teclado.
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @return A string lida do usuário.
     */
    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    /**
     * Exibe uma mensagem ao usuário e espera uma resposta de 's' (sim) ou 'n' (não), sem diferenciar maiúsculas de minúsculas.
     * Repete a solicitação até que uma entrada válida seja fornecida.
     * @param mensagem A mensagem a ser exibida ao usuário.
     * @return true se a resposta for 's' ou 'S', false se for 'n' ou 'N'.
     */
    public static boolean lerSimNao(String mensagem) {
        String resposta;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print(mensagem + " (s/n): ");
            resposta = scanner.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                return true;
            } else if (resposta.equals("n")) {
                return false;
            } else {
                System.out.println("Entrada inválida. Digite 's' para sim ou 'n' para não.");
            }
        }
        return false; // Não deve ser alcançado
    }

    /**
     * Exibe uma mensagem ao usuário e aguarda que ele pressione a tecla Enter para continuar.
     * @param mensagem A mensagem a ser exibida ao usuário.
     */
    public static void esperarEnter(String mensagem) {
        System.out.print(mensagem + " Pressione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Fecha o objeto Scanner subjacente. Deve ser chamado uma única vez no final do programa.
     */
    public static void fecharScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}

