public interface AcaoDeCombate {

    // o método 'executar' realiza a ação de um combatente contra outro.
    // parâmetro 'usuario': é o combatente que está executando a ação.
    // parâmetro 'alvo': é o combatente que está recebendo o efeito.
    void executar(Combatente usuario, Combatente alvo);

}