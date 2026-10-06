package dados;

import java.util.ArrayList;

public class CadastroVoto {
    private ArrayList<Voto> listaVoto;

    public CadastroVoto() {
        listaVoto = new ArrayList<>(10);
    }

    public String cadastrarVoto(
            int id,
            int hora,
            int numCandidato,
            String cep,
            ArrayList<Candidato> listaCandidato,
            ArrayList<Localidade> listaLocalidade) {

        for (int i = 0; i < listaVoto.size(); i++) {
            if (listaVoto.get(i).getId() == id) {
                return "5: ERRO - id repetido.";
            }
        }

        if (hora > 17 || hora < 8) {
            return "5: ERRO - hora incorreta.";
        }

        Candidato candidato = null;

        for (int i = 0; i < listaCandidato.size(); i++) {
            if (listaCandidato.get(i).getNumero() == numCandidato) {
                candidato = listaCandidato.get(i);
                break;
            }
        }

        if (candidato == null) {
            return "5: ERRO - candidato incorreto.";
        }

        Localidade localidade = null;

        for (int i = 0; i < listaLocalidade.size(); i++) {
            if (listaLocalidade.get(i).getCep().equals(cep)) {
                localidade = listaLocalidade.get(i);
                break;
            }
        }

        if (localidade == null) {
            return "5: ERRO - localidade incorreta.";
        }

        if (candidato.getLocalidade() != null
                && !candidato.getLocalidade().getCep().equals(cep)) {

            return "5: ERRO - localidade do candidato incorreta.";
        }

        Voto voto = new Voto(id, hora);
        listaVoto.add(voto);

        candidato.aumentaVotos();
        candidato.getPartido().aumentaVotosPartido();
        candidato.setUltimoVoto(hora);

        return "5: " + id + " - " + hora + " - "
                + candidato.getNome() + " - " + localidade.getNome();
    }
}