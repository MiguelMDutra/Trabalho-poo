package dados;

public class Candidato {
    private int numero;
    private String nome;
    private Partido partido;
    private Localidade localidade;
    private int votos;
    private int ultimoVoto;

    public Candidato(int numero, String nome, Partido partido, Localidade localidade) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.localidade = localidade;
        votos = 0;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public Partido getPartido() {
        return partido;
    }

    public int getUltimoVoto() {
        return ultimoVoto;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public int getVotos() {
        return votos;
    }

    public void setUltimoVoto(int ultimoVoto) {
        this.ultimoVoto = ultimoVoto;
    }

    public void aumentaVotos() {
        votos++;
    }

    public String getDescricao() {
        return getNumero() + " - " + getNome() + " - " + getPartido().getNome() + " - " + getLocalidade().getNome()
                + " - " + getVotos() + " - " + getUltimoVoto();
    }

    public String getDescricaoParcial() {
        return getNumero() + " - " + getNome() + " - " + getPartido().getNome();
    }
}