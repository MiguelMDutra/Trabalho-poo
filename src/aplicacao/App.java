package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

import dados.cadastro.CadastroCandidato;
import dados.cadastro.CadastroLocalidade;
import dados.cadastro.CadastroPartidos;
import dados.cadastro.CadastroVoto;

public class App {

    private Scanner entrada = new Scanner(System.in);
    private final String nomeArquivoEntrada = "pollingin.txt";
    private final String nomeArquivoSaida = "pollingout.txt";

    public void executar() {
        CadastroPartidos partido = new CadastroPartidos();
        CadastroLocalidade localidade = new CadastroLocalidade();
        CadastroCandidato candidato = new CadastroCandidato();
        CadastroVoto voto = new CadastroVoto();

        partido.cadastrarPartido(entrada);

        localidade.cadastrarLocalidade(entrada);

        candidato.cadastrarCandidato(entrada, partido.getListaPartido(),
                localidade.getListaLocalidade(), 1);

        candidato.cadastrarCandidato(entrada, partido.getListaPartido(),
                localidade.getListaLocalidade(), 2);

        voto.cadastrarVoto(entrada, candidato.getListaCandidato(),
                localidade.getListaLocalidade());

        int numero = entrada.nextInt();
        entrada.nextLine();
        candidato.consultaCandidato(numero);

        while (entrada.hasNextInt()) { // pesquisei, não sabia da existência!
            numero = entrada.nextInt();
            entrada.nextLine();

            candidato.consultaPartido(numero, partido.getListaPartido());
        }

        String cep = entrada.nextLine();
        candidato.consultaEleito(cep, localidade.getListaLocalidade());

        partido.consultaMaiorPartido();

        partido.consultaMaiorEleito(candidato.getListaCandidato());
    }

    public App() {
        redirecionaEntrada();
        redirecionaSaida();

    }

    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada);
        } catch (Exception e) {
            System.out.println(e);
        }

        Locale.setDefault(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);
    }

    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }

        Locale.setDefault(Locale.ENGLISH);
    }
}
