package dados;

import java.util.ArrayList;

public class CadastroLocalidade {
    private ArrayList<Localidade> listaLocalidade;

    public CadastroLocalidade() {
        listaLocalidade = new ArrayList<>(10);
    }

    public ArrayList<Localidade> getListaLocalidade() {
        return listaLocalidade;
    }

    public String cadastrarLocalidade(String cep, String nome, long qtdEleitores, TipoLocalidade tipo) {

        String resultado = "";

        if (tipo == null) {
            resultado = "2: ERRO - tipo de localidade incorreto.";
            return resultado;
        }

        boolean repetido = false;

        for (int i = 0; i < listaLocalidade.size(); i++) {
            if (cep.equals(listaLocalidade.get(i).getCep())) {
                resultado = "2: ERRO - localidade repetida.";
                repetido = true;
                break;
            }
        }

        if (!repetido) {
            Localidade novaLocalidade = new Localidade(
                    qtdEleitores,
                    cep,
                    nome,
                    tipo);

            listaLocalidade.add(novaLocalidade);

            resultado = "2: " + cep + " - " + nome + " - "
                    + qtdEleitores + " - " + tipo;
        }

        return resultado;
    }
}
