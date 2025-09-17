public enum TipoCenario {

    MIDGARD("as florestas selvagens de Midgard") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println(heroi.getNome() + " sente o vento forte de Midgard. A jornada é longa e perigosa.");
        }
    },

    ALFHEIM("o reino iluminado e sombrio de Alfheim") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("A luz de Alfheim ofusca os olhos de " + heroi.getNome() + ". Elfos de luz e sombrios batalham eternamente nese mundo.");
        }
    },

    SVARTALFHEIM("as profundas e brilhantes minas de Svartalfheim") {
        @Override
        public void aplicarEfeitos(Heroi heroi) {
            System.out.println("O som de martelos ecoa pelas cavernas de Svartalfheim. " + heroi.getNome() + " se prepara para os desafios criados pelos anões.");
        }
    };

    private final String descricao;

    // construtor para inicializar a descrição
    TipoCenario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // descreve o ambiente e aplica um efeito em kratos quando a fase inicia
    public abstract void aplicarEfeitos(Heroi heroi);
}