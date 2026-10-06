package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

import dados.CadastroLocalidade;
import dados.CadastroPartidos;
import dados.CadastroVoto;
import dados.TipoLocalidade;
import dados.CadastroCandidato;

public class AppACMEPolling {

    private Scanner entrada = new Scanner(System.in);
    private final String nomeArquivoEntrada = "pollingin.txt";
    private final String nomeArquivoSaida = "pollingout.txt";

    private CadastroPartidos partidoCadastro = new CadastroPartidos();
    private CadastroLocalidade localidadeCadastro = new CadastroLocalidade();
    private CadastroCandidato candidatoCadastro = new CadastroCandidato();
    private CadastroVoto votoCadastro = new CadastroVoto();

    public void executar() {
        cadastrarPartido();

        cadastrarLocalidade();

        cadastrarCandidatoAPresidente();

        cadastrarCandidatoAGovernador();

        cadastrarVoto();

        consultarCandidato();

        mostrarCandidatoDoPartido();

        mostrarEleitoDeLocalidade();

        mostrarPartidoComMaisVotos();

        mostrarPartidoComMaisEleitos();
    }

    public void cadastrarPartido() {
        while (true) {
            int codigo = Integer.parseInt(entrada.nextLine());

            if (codigo == -1) {
                break;
            }

            String partido = entrada.nextLine();

            System.out.println(partidoCadastro.cadastrarPartido(codigo, partido));
        }
    }

    public void cadastrarLocalidade() {

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

            String resposta = localidadeCadastro.cadastrarLocalidade(
                    cep,
                    nome,
                    qtdEleitores,
                    tipoLocalidade);

            System.out.println(resposta);
        }
    }

    public void cadastrarCandidatoAPresidente() {
        while (true) {
            int numero = Integer.parseInt(entrada.nextLine());

            if (numero == -1) {
                break;
            }

            String nome = entrada.nextLine();
            int partidoCodigo = Integer.parseInt(entrada.nextLine());

            String cep = entrada.nextLine();
            double patrimonio = Double.parseDouble(entrada.nextLine());

            System.out.println(candidatoCadastro.cadastrarCandidato(
                    numero,
                    nome,
                    partidoCodigo,
                    cep,
                    patrimonio,
                    partidoCadastro.getListaPartido(),
                    localidadeCadastro.getListaLocalidade()));
        }
    }

    public void cadastrarCandidatoAGovernador() {
        while (true) {
            int numero = Integer.parseInt(entrada.nextLine());

            if (numero == -1) {
                break;
            }

            String nome = entrada.nextLine();

            int partidoCodigo = Integer.parseInt(entrada.nextLine());

            String cep = entrada.nextLine();

            String escolaridade = entrada.nextLine();

            System.out.println(candidatoCadastro.cadastrarGovernador(
                    numero,
                    nome,
                    partidoCodigo,
                    cep,
                    escolaridade,
                    partidoCadastro.getListaPartido(),
                    localidadeCadastro.getListaLocalidade()));
        }
    }

    public void cadastrarVoto() {
        while (true) {
            int id = Integer.parseInt(entrada.nextLine());

            if (id == -1) {
                break;
            }

            int hora = Integer.parseInt(entrada.nextLine());

            int numCandidato = Integer.parseInt(entrada.nextLine());

            String cep = entrada.nextLine();

            System.out.println(votoCadastro.cadastrarVoto(
                    id,
                    hora,
                    numCandidato,
                    cep,
                    candidatoCadastro.getListaCandidato(),
                    localidadeCadastro.getListaLocalidade()));
        }
    }

    public void consultarCandidato() {
        int numero = entrada.nextInt();
        entrada.nextLine();
        System.out.println(candidatoCadastro.consultaCandidato(numero));
    }

    public void mostrarCandidatoDoPartido() {
        int codigo = entrada.nextInt();
        entrada.nextLine();

        System.out.println(candidatoCadastro.consultaPartido(codigo, partidoCadastro.getListaPartido()));
    }

    public void mostrarEleitoDeLocalidade() {
        String cep = entrada.nextLine();

        System.out.println(candidatoCadastro.consultaEleito(
                cep,
                localidadeCadastro.getListaLocalidade()));
    }

    public void mostrarPartidoComMaisVotos() {
        System.out.println(partidoCadastro.consultaMaiorPartido());
    }

    public void mostrarPartidoComMaisEleitos() {
        System.out.print(partidoCadastro.consultaMaiorEleito(
                candidatoCadastro.getListaCandidato()));
    }

    public AppACMEPolling() {
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
