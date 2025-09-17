// representa qualquer um que pode participar de uma batalha
public interface Combatente {

    // retorna o nome do combatente.
    String getNome();

    // verifica se o combatente ainda está vivo (vida > 0)
    boolean estaVivo();

    // reduz os pontos de vida do combatente com base no dano sofrido
    void receberDano(int dano);

    // dumenta os pontos de vida do combatente.
    void receberCura(int cura);

    // Define a lógica para a escolha da próxima ação do combatente em seu turno
    AcaoDeCombate escolherAcao(Combatente alvo);
}