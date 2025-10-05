package com.rpg.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * classe de teste para InputManager
 * simula a entrada do usuário e verifica as saídas para garantir que os métodos funcionem como esperado
 */
public class InputManagerTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outContent;

    /**
     * Configura os streams de entrada e saída para capturar os resultados antes de cada teste.
     */
    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restaura os streams originais do sistema após cada teste para não afetar outras operações.
     */
    @AfterEach
    public void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    /**
     * fornece uma string como se tivesse sido digitada pelo usuário no console.
     * @param data A string que simula a entrada do usuário.
     */
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
    }

    /**
     * Testa o caso comum do método lerString.
     */
    @Test
    public void testLerString_CommonCase() {
        provideInput("Teste de frase\n");
        String result = InputManager.lerString("Digite uma frase: ");
        assertEquals("Teste de frase", result, "A string lida deve ser igual à entrada simulada.");
        assertEquals("Digite uma frase: ", outContent.toString(), "A mensagem para o usuário deve ser exibida corretamente.");
    }

    /**
     * Testa o caso de entrada 'S' para o método lerSimNao.
     */
    @Test
    public void testLerSimNao_SimUpperCase() {
        provideInput("S\n");
        assertTrue(InputManager.lerSimNao("Sim ou Nao?"), "A entrada 'S' deve retornar true.");
    }

    /**
     * Testa o caso de entrada 's' para o método lerSimNao.
     */
    @Test
    public void testLerSimNao_SimLowerCase() {
        provideInput("s\n");
        assertTrue(InputManager.lerSimNao("Sim ou Nao?"), "A entrada 's' deve retornar true.");
    }

    /**
     * Testa o caso de entrada 'N' para o método lerSimNao.
     */
    @Test
    public void testLerSimNao_NaoUpperCase() {
        provideInput("N\n");
        assertFalse(InputManager.lerSimNao("Sim ou Nao?"), "A entrada 'N' deve retornar false.");
    }

    /**
     * Testa o caso de entrada 'n' para o método lerSimNao.
     */
    @Test
    public void testLerSimNao_NaoLowerCase() {
        provideInput("n\n");
        assertFalse(InputManager.lerSimNao("Sim ou Nao?"), "A entrada 'n' deve retornar false.");
    }

    /**
     * testa o caso de uma entrada inválida seguida por uma válida para o método lerSimNao.
     * verifica se o loop de validação está funcionando.
     */
    @Test
    public void testLerSimNao_InvalidInputThenSim() {
        // simula uma entrada inválida ("x") e depois uma válida ("s")
        provideInput("x\ns\n");
        boolean result = InputManager.lerSimNao("Sim ou Nao?");
        assertTrue(result, "Deve retornar true para a segunda entrada válida 's'.");

        // verifica se a mensagem de erro foi exibida corretamente para a entrada inválida
        String expectedOutput = "Sim ou Nao? (s/n): " +
                                "Entrada inválida. Digite 's' para sim ou 'n' para não." + System.lineSeparator() +
                                "Sim ou Nao? (s/n): ";
        assertEquals(expectedOutput, outContent.toString());
    }
}