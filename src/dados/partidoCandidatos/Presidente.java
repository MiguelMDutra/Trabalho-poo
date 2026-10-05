package dados.partidoCandidatos;

import dados.localidade.Localidade;

public class Presidente extends Candidato {
    private double patrimonio;

    public Presidente(int numero, String nome, Partido partido, Localidade localidade, double patrimonio) {
        this.patrimonio = patrimonio;
        super(numero, nome, partido, localidade);
    }

    public double getPatrimonio() {
        return patrimonio;
    }

    @Override
    public String toString() {
        return getNumero() + " - "
                + getNome() + " - "
                + getPartido().getNome() + " - "
                + getLocalidade().getNome() + " - "
                + getPatrimonio();
    }
}
