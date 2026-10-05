package dados.cadastro;

import java.util.ArrayList;
import java.util.Scanner;

import dados.partidoCandidatos.Candidato;
import dados.partidoCandidatos.Partido;

public class CadastroPartidos {
    private ArrayList<Partido> listaPartido;

    public CadastroPartidos() {
        listaPartido = new ArrayList<>(10);
    }

    public ArrayList<Partido> getListaPartido() {
        return listaPartido;
    }

    public void cadastrarPartido(Scanner entrada) {
        boolean liberado = false;

        while (!liberado) {
            int codigo = entrada.nextInt();
            if (codigo == -1) {
                break;
            }
            entrada.nextLine();
            String partido = entrada.nextLine();

            boolean repetido = false;

            for (int i = 0; i < listaPartido.size(); i++) {
                if (codigo == listaPartido.get(i).getCodigo()) {
                    System.out.println("1: ERRO - partido repetido.");
                    repetido = true;
                }
            }

            if (!repetido) {
                Partido novPartido = new Partido(codigo, partido);
                listaPartido.add(novPartido);
                System.out.println("1: " + codigo + " - " + partido);
            }
        }
    }

    public void consultaMaiorPartido() {
        if (listaPartido.size() == 0) {
            System.out.println("9: ERRO - nenhum partido cadastrado.");
            return;
        }
        Partido maior = listaPartido.get(0);
        for (int i = 0; i < listaPartido.size(); i++) {
            if (listaPartido.get(i).getVotosPartido() >= maior.getVotosPartido()) {
                maior = listaPartido.get(i);
            }
        }
        if (maior.getVotosPartido() == 0) {
            System.out.println("9: nenhum partido com votos.");
            return;
        }

        System.out.print("9: ");
        System.out.println(maior.getCodigo() + " - " + maior.getNome() + " - " + maior.getVotosPartido());
    }

    public void consultaMaiorEleito(ArrayList<Candidato> listaCandidatos) {

        if (listaPartido.size() == 0) {
            System.out.println("10: ERRO - nenhum partido cadastrado.");
            return;
        }

        helperEleitos(listaCandidatos);

        Partido maior = listaPartido.get(0);

        for (int i = 1; i < listaPartido.size(); i++) {
            if (listaPartido.get(i).getEleitos() >= maior.getEleitos()) {
                maior = listaPartido.get(i);
            }
        }

        if (maior.getEleitos() == 0) {
            System.out.println("10: nenhum partido com eleitos.");
            return;
        }

        System.out.println("10: " + maior.getCodigo() + " - "
                + maior.getNome() + " - " + maior.getEleitos());
    }

    public void helperEleitos(ArrayList<Candidato> listaCandidatos) {
        ArrayList<Candidato> candidatosAnalisados = new ArrayList<>();

        for (int i = 0; i < listaCandidatos.size(); i++) {
            Candidato candidato = listaCandidatos.get(i);
            boolean localidadeAnalisada = false;

            for (int j = 0; j < candidatosAnalisados.size(); j++) {
                if (candidatosAnalisados.get(j).getLocalidade().getCep()
                        .equals(candidato.getLocalidade().getCep())) {
                    localidadeAnalisada = true;
                    break;
                }
            }

            if (localidadeAnalisada)
                continue;

            ArrayList<Candidato> candidatosLocalidade = new ArrayList<>();
            for (int j = 0; j < listaCandidatos.size(); j++) {
                if (listaCandidatos.get(j).getLocalidade().getCep()
                        .equals(candidato.getLocalidade().getCep())) {
                    candidatosLocalidade.add(listaCandidatos.get(j));
                }
            }

            Candidato maior = candidatosLocalidade.get(0);

            for (int j = 1; j < candidatosLocalidade.size(); j++) {
                if (candidatosLocalidade.get(j).getVotos() > maior.getVotos()) {
                    maior = candidatosLocalidade.get(j);
                } else if (candidatosLocalidade.get(j).getVotos() == maior.getVotos()) {
                    if (candidatosLocalidade.get(j).getUltimoVoto() < maior.getUltimoVoto()) {
                        maior = candidatosLocalidade.get(j);
                    }
                }
            }

            if (maior.getVotos() > 0) {
                maior.getPartido().aumentaEleitos();
            }

            candidatosAnalisados.add(candidato);
        }

    }
}
