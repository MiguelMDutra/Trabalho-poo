package dados;

import java.util.ArrayList;
import java.util.Scanner;

public class CadastroLocalidade {
    private ArrayList<Localidade> listaLocalidade;

    public CadastroLocalidade() {
        listaLocalidade = new ArrayList<>(10);
    }

    public ArrayList<Localidade> getListaLocalidade() {
        return listaLocalidade;
    }

    public void cadastrarLocalidade(Scanner entrada) {
        entrada.nextLine();

        while (true) {

            String cep = entrada.nextLine();

            if (cep.equals("-1")) {
                break;
            }

            String nome = entrada.nextLine();
            long qtdEleitores = Long.parseLong(entrada.nextLine());
            String tipo = entrada.nextLine();

            TipoLocalidade tipoLocalidade = null;

            TipoLocalidade[] tipos = TipoLocalidade.values();

            for (int i = 0; i < tipos.length; i++) {
                if (tipos[i].name().equals(tipo)) {
                    tipoLocalidade = tipos[i];
                    break;
                }
            }

            if (tipoLocalidade == null) {
                System.out.println("2: ERRO - tipo de localidade incorreto.");
                continue;
            }

            boolean repetido = false;

            for (int i = 0; i < listaLocalidade.size(); i++) {

                if (cep.equals(listaLocalidade.get(i).getCep())) {
                    System.out.println("2: ERRO - localidade repetida.");
                    repetido = true;
                    break;
                }
            }

            if (!repetido) {
                Localidade novaLocalidade = new Localidade(
                        qtdEleitores,
                        cep,
                        nome,
                        tipoLocalidade);
                listaLocalidade.add(novaLocalidade);
                System.out.println(
                        "2: " + cep + " - " + nome + " - " +
                                qtdEleitores + " - " + tipoLocalidade);
            }
        }
    }
}
