package dados;

public class Presidente extends Candidato {
    private double patrimonio;

    public Presidente(int numero, String nome, Partido partido, Localidade localidade, double patrimonio) {
        super(numero, nome, partido, localidade);
        this.patrimonio = patrimonio;
    }

    public double getPatrimonio() {
        return patrimonio;
    }

    @Override
    public String getDescricao() {
        return getNumero() + " - "
                + getNome() + " - "
                + getPartido().getNome() + " - "
                + getLocalidade().getNome() + " - "
                + getPatrimonio();
    }

    @Override
    public String getDescricaoParcial() {
        return getNumero() + " - "
                + getNome() + " - "
                + getPartido().getNome() + " - "
                + getPatrimonio();
    }
}
