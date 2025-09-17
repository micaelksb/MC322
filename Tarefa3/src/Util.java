import java.util.Random;

// classe para centralizar a geração de números aleatórios
public class Util {
    private static final Random random = new Random();

    // retorna um número decimal aleatório entre 0.0 (inclusivo) e 1.0 (exclusivo).
    public static double gerarNumeroAleatorio() {
        return random.nextDouble();
    }

    // retorna um numero inteiro aleatório entre 0 (inclusivo) e o limite (exclusivo).
    public static int gerarNumeroAleatorio(int limite) {
        if (limite <= 0) {
            return 0;
        }
        return random.nextInt(limite);
    }
}