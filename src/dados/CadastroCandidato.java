package dados;

import java.util.ArrayList;

public class CadastroCandidato {
    private ArrayList<Candidato> listaCandidato;

    public CadastroCandidato() {
        listaCandidato = new ArrayList<>(10);
    }

    public ArrayList<Candidato> getListaCandidato() {
        return listaCandidato;
    }

    public String cadastrarCandidato(
            int numero, String nome,
            int partidoCodigo, String cep,
            double patrimonio, ArrayList<Partido> listaPartido,
            ArrayList<Localidade> listaLocalidade) {

        Partido partido = helperListaPartido(partidoCodigo, listaPartido);

        for (int i = 0; i < listaCandidato.size(); i++) {
            if (listaCandidato.get(i).getNumero() == numero) {
                return "3: ERRO - candidato repetido.";
            }
        }

        if (partido == null) {
            return "3: ERRO - partido incorreto.";
        }

        Localidade localidade = helperLocalidade(cep, listaLocalidade);

        if (localidade == null) {
            return "3: ERRO - localidade incorreta.";
        }

        Candidato presidente = new Presidente(
                numero,
                nome,
                partido,
                localidade,
                patrimonio);

        listaCandidato.add(presidente);

        return "3: " + numero + " - " + nome + " - "
                + partido.getNome() + " - " + patrimonio;
    }

    public String cadastrarGovernador(
            int numero,
            String nome,
            int partidoCodigo,
            String cep,
            String escolaridade, ArrayList<Partido> listaPartido,
            ArrayList<Localidade> listaLocalidade) {

        Partido partido = helperListaPartido(partidoCodigo, listaPartido);

        if (partido == null) {
            return "4: ERRO - partido incorreto.";
        }

        Localidade localidade = helperLocalidade(cep, listaLocalidade);

        if (localidade == null) {
            return "4: ERRO - localidade incorreta.";
        }

        for (int i = 0; i < listaCandidato.size(); i++) {
            if (listaCandidato.get(i).getNumero() == numero) {
                return "4: ERRO - candidato repetido.";
            }
        }

        Candidato governador = new Governador(
                numero,
                nome,
                partido,
                localidade,
                escolaridade);

        listaCandidato.add(governador);

        return "4: " + numero + " - " + nome + " - "
                + partido.getNome() + " - "
                + escolaridade + " - "
                + governador.getLocalidade().getNome();
    }

    public String consultaCandidato(int numero) {

        for (int i = 0; i < listaCandidato.size(); i++) {

            String resultado = "";

            if (listaCandidato.get(i).getNumero() == numero) {
                resultado = "6: "
                        + listaCandidato.get(i).getNumero() + " - " + listaCandidato.get(i).getNome() + " - "
                        + listaCandidato.get(i).getPartido().getNome() + " - "
                        + listaCandidato.get(i).getLocalidade().getNome();
                if (listaCandidato.get(i) instanceof Presidente) {
                    Presidente presidente = (Presidente) listaCandidato.get(i);
                    resultado += " - " + presidente.getPatrimonio();
                } else if (listaCandidato.get(i) instanceof Governador) {
                    Governador governador = (Governador) listaCandidato.get(i);
                    resultado += " - " + governador.getEscolaridade();
                }
                return resultado;
            }
        }
        return "6: ERRO - candidato inexistente.";
    }

    public String consultaPartido(int codigo, ArrayList<Partido> listaPartido) {
        boolean partidoExiste = false;
        for (int i = 0; i < listaPartido.size(); i++) {
            if (listaPartido.get(i).getCodigo() == codigo) {
                partidoExiste = true;
            }
        }

        if (!partidoExiste) {
            return "7: ERRO - partido inexistente.";
        }

        String resultado = "";

        for (int i = 0; i < listaCandidato.size(); i++) {
            Candidato candidato = listaCandidato.get(i);

            if (candidato.getPartido().getCodigo() == codigo) {
                resultado += "7: "
                        + candidato.getNumero()
                        + " - "
                        + candidato.getNome()
                        + " - "
                        + candidato.getPartido().getNome()
                        + " - "
                        + candidato.getLocalidade().getNome();

                if (candidato instanceof Presidente) {
                    Presidente presidente = (Presidente) candidato;
                    resultado += " - " + presidente.getPatrimonio();
                } else if (candidato instanceof Governador) {
                    Governador governador = (Governador) candidato;
                    resultado += " - " + governador.getEscolaridade();
                }
                resultado += "\n";
            }
        }

        if (resultado.equals("")) {
            return "7: nenhum candidato cadastrado.";
        }

        resultado = resultado.substring(0, resultado.length() - 1); // pesquisei pra conseguir n pular a ultima linha

        return resultado;
    }

    public String consultaEleito(
            String cep,
            ArrayList<Localidade> listaLocalidade) {

        boolean localidadeExiste = false;

        for (int i = 0; i < listaLocalidade.size(); i++) {

            if (listaLocalidade.get(i).getCep().equals(cep)) {
                localidadeExiste = true;
                break;
            }
        }

        if (!localidadeExiste) {
            return "8: ERRO - localidade inexistente.";
        }

        ArrayList<Candidato> candidatosLocalidade = new ArrayList<>();

        for (int i = 0; i < listaCandidato.size(); i++) {

            if (listaCandidato.get(i).getLocalidade().getCep().equals(cep)) {
                candidatosLocalidade.add(listaCandidato.get(i));
            }
        }

        if (candidatosLocalidade.size() == 0) {
            return "8: nenhum candidato cadastrado.";

        }

        Candidato maior = candidatosLocalidade.get(0);

        for (int i = 1; i < candidatosLocalidade.size(); i++) {

            if (candidatosLocalidade.get(i).getVotos() > maior.getVotos()) {

                maior = candidatosLocalidade.get(i);

            } else if (candidatosLocalidade.get(i).getVotos() == maior.getVotos()) {

                if (candidatosLocalidade.get(i).getUltimoVoto() < maior.getUltimoVoto()) {

                    maior = candidatosLocalidade.get(i);
                }
            }
        }

        if (maior.getVotos() == 0) {
            return "8: nenhum candidato eleito.";

        }

        return "8: " + maior.getNumero() + " - " + maior.getNome() + " - " + maior.getVotos();
    }

    public Partido helperListaPartido(int partidoCodigo, ArrayList<Partido> listaPartido) {
        for (int i = 0; i < listaPartido.size(); i++) {
            if (listaPartido.get(i).getCodigo() == partidoCodigo) {
                return listaPartido.get(i);
            }
        }
        return null;
    }

    public Localidade helperLocalidade(String cep, ArrayList<Localidade> listaLocalidade) {
        for (int i = 0; i < listaLocalidade.size(); i++) {
            if (listaLocalidade.get(i).getCep().equals(cep)) {
                return listaLocalidade.get(i);
            }
        }

        return null;
    }
}
