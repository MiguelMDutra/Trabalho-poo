package dados.cadastro;

import java.util.ArrayList;
import java.util.Scanner;

import dados.Voto;
import dados.localidade.Localidade;
import dados.partidoCandidatos.Candidato;

public class CadastroVoto {
    private ArrayList<Voto> listaVoto;

    public CadastroVoto() {
        listaVoto = new ArrayList<>(10);
    }

    public void cadastrarVoto(Scanner entrada, ArrayList<Candidato> listaCandidato,
            ArrayList<Localidade> listaLocalidade) {

        while (true) {
            int id = entrada.nextInt();
            if (id == -1) {
                break;
            }

            entrada.nextLine();

            int hora = entrada.nextInt();
            entrada.nextLine();

            int numCandidato = entrada.nextInt();
            entrada.nextLine();

            String cep = entrada.nextLine();
            Candidato candidato = null;
            boolean valido = true;
            Localidade localidade = null;

            if (hora > 17 || hora < 8) { // output (mais dados) do professor deixou 17 por isso > e não >=
                System.out.println("5: ERRO - hora incorreta.");
                valido = false;
                continue;
            }

            for (int i = 0; i < listaCandidato.size(); i++) {
                if (listaCandidato.get(i).getNumero() == numCandidato) {
                    candidato = listaCandidato.get(i);
                    break;
                }
            }

            if (candidato == null) {
                System.out.println("5: ERRO - candidato incorreto.");
                valido = false;
            }

            if (candidato != null) {
                for (int i = 0; i < listaLocalidade.size(); i++) {
                    if (listaLocalidade.get(i).getCep().equals(cep)) {
                        localidade = listaLocalidade.get(i);
                        break;
                    }
                }

                if (localidade == null) {
                    System.out.println("5: ERRO - localidade do candidato incorreta.");
                    valido = false;
                }

                if (localidade != null && candidato.getLocalidade() != null) {
                    if (!candidato.getLocalidade().getCep().equals(cep)) {
                        System.out.println("5: ERRO - localidade do candidato incorreta.");
                        valido = false;
                    }
                }
            }

            for (int i = 0; i < listaVoto.size(); i++) {
                if (listaVoto.get(i).getId() == id) {
                    System.out.println("5: ERRO - id repetido.");
                    valido = false;
                    break;
                }
            }

            if (valido && candidato != null && localidade != null) {
                candidato.aumentaVotos();
                candidato.getPartido().aumentaVotosPartido();
                candidato.setUltimoVoto(hora);

                Voto novoVoto = new Voto(id, hora);
                listaVoto.add(novoVoto);

                System.out.println(
                        "5: " + id + " - " + hora + " - " + candidato.getNome() + " - "
                                + candidato.getLocalidade().getNome());
            }
        }
    }
}
