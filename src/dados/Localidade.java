package dados;

public class Localidade {
    private long qtdEleitores;
    private String cep;
    private String nome;
    private TipoLocalidade tipo;

    public Localidade(long qtdEleitores, String cep, String nome, TipoLocalidade tipo) {
        this.qtdEleitores = qtdEleitores;
        this.cep = cep;
        this.nome = nome;
        this.tipo = tipo;
    }

    public long getQtdEleitores() {
        return qtdEleitores;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }

    public TipoLocalidade getTipoLocalidade() {
        return tipo;
    }

    public void setQtdEleitores(long qtdEleitores) {
        this.qtdEleitores = qtdEleitores;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}