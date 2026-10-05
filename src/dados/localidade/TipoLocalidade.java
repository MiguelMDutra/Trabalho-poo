package dados.localidade;

public enum TipoLocalidade {
    NACIONAL("NACIONAL"), ESTADUAL("ESTADUAL"), MUNICIPAL("MUNICIPAL");

    private final String tipo;

    private TipoLocalidade(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
