package dados.partidoCandidatos;

import dados.localidade.Localidade;

public class Governador extends Candidato {
    private String escolaridade;

    public Governador(int numero, String nome, Partido partido, Localidade localidade, String escolaridade) {
        this.escolaridade = escolaridade;
        super(numero, nome, partido, localidade);
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    @Override
    public String getDescricao() {
        return getNumero() + " - "
                + getNome() + " - "
                + getPartido().getNome() + " - "
                + getLocalidade().getNome() + " - "
                + getEscolaridade();
    }
}