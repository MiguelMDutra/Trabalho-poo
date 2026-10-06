package dados;

import java.util.ArrayList;
import java.util.Scanner;

public class CadastroCandidato {
    private ArrayList<Candidato> listaCandidato;

    public CadastroCandidato() {
        listaCandidato = new ArrayList<>(10);
    }

    public ArrayList<Candidato> getListaCandidato() {
        return listaCandidato;
    }

    public void cadastrarCandidato(
            Scanner entrada,
            ArrayList<Partido> listaPartido,
            ArrayList<Localidade> listaLocalidade,
            int tipo) {

        while (true) {
            int numero = entrada.nextInt();

            if (numero == -1) {
                break;
            }

            entrada.nextLine();

            String nome = entrada.nextLine();
            int partidoCodigo = entrada.nextInt();
            entrada.nextLine();

            Partido partido = helperListaPartido(partidoCodigo, listaPartido);
            String cep = entrada.nextLine();
            Localidade localidade = helperLocalidade(cep, listaLocalidade);

            boolean repetido = false;

            for (int i = 0; i < listaCandidato.size(); i++) {
                if (listaCandidato.get(i).getNumero() == numero) {
                    repetido = true;
                }
            }

            if (tipo == 1) {

                double patrimonio = entrada.nextDouble();
                entrada.nextLine();

                if (repetido) {
                    System.out.println("3: ERRO - candidato repetido.");
                    continue;
                }

                if (partido == null) {
                    System.out.println("3: ERRO - partido incorreto.");
                    continue;
                }

                if (localidade == null) {
                    System.out.println("3: ERRO - localidade incorreta.");
                    continue;
                }

                Candidato presidente = new Presidente(
                        numero,
                        nome,
                        partido,
                        localidade,
                        patrimonio);

                listaCandidato.add(presidente);
                System.out.println(
                        "3: " + numero + " - " + nome + " - " +
                                partido.getNome() + " - " + patrimonio);

            } else if (tipo == 2) {
                String escolaridade = entrada.nextLine();

                if (repetido) {
                    System.out.println("4: ERRO - candidato repetido.");
                    continue;
                }

                if (partido == null) {
                    System.out.println("4: ERRO - partido incorreto.");
                    continue;
                }

                if (localidade == null) {
                    System.out.println("4: ERRO - localidade incorreta.");
                    continue;
                }

                Candidato governador = new Governador(
                        numero,
                        nome,
                        partido,
                        localidade,
                        escolaridade);

                listaCandidato.add(governador);
                System.out.println(
                        "4: " + numero + " - " + nome + " - " +
                                partido.getNome() + " - " +
                                escolaridade + " - " + governador.getLocalidade().getNome());
            }
        }
    }

    public void consultaCandidato(int numero) {

        for (int i = 0; i < listaCandidato.size(); i++) {

            if (listaCandidato.get(i).getNumero() == numero) {
                System.out.print("6: ");
                System.out.println(listaCandidato.get(i).getDescricao());
                return;
            }
        }
        System.out.println("6: ERRO - candidato inexistente.");
    }

    public void consultaPartido(int codigo, ArrayList<Partido> listaPartido) {
        boolean partidoExiste = false;
        for (int i = 0; i < listaPartido.size(); i++) {
            if (listaPartido.get(i).getCodigo() == codigo) {
                partidoExiste = true;
            }
        }

        if (!partidoExiste) {
            System.out.println("7: ERRO - partido inexistente.");
            return;
        }

        boolean candidatoExiste = false;
        for (int i = 0; i < listaCandidato.size(); i++) {
            if (listaCandidato.get(i).getPartido().getCodigo() == codigo) {
                System.out.print("7: ");
                System.out.println(listaCandidato.get(i).getDescricao());
                candidatoExiste = true;
            }
        }

        if (!candidatoExiste) {
            System.out.println("7: nenhum candidato cadastrado.");
        }

        return;
    }

    public void consultaEleito(String cep, ArrayList<Localidade> listaLocalidade) {
        boolean localidadeExiste = false;

        for (int i = 0; i < listaLocalidade.size(); i++) {
            if (listaLocalidade.get(i).getCep().equals(cep)) {
                localidadeExiste = true;
                break;
            }
        }

        if (!localidadeExiste) {
            System.out.println("8: ERRO - localidade inexistente.");
            return;
        }

        ArrayList<Candidato> candidatosLocalidade = new ArrayList<>();
        for (int i = 0; i < listaCandidato.size(); i++) {
            if (listaCandidato.get(i).getLocalidade().getCep().equals(cep)) {
                candidatosLocalidade.add(listaCandidato.get(i));
            }
        }

        if (candidatosLocalidade.size() == 0) {
            System.out.println("8: nenhum candidato cadastrado.");
            return;
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
            System.out.println("8: nenhum candidato eleito.");
            return;
        }

        System.out.println("8: " + maior.getNumero() + " - "
                + maior.getNome() + " - " + maior.getVotos());
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
