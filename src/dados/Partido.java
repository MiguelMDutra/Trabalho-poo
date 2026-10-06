package dados;

public class Partido {
    private int codigo;
    private String nome;
    private int votosPartido;
    private int eleitos;

    public Partido(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        votosPartido = 0;
        eleitos = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getVotosPartido() {
        return votosPartido;
    }

    public int getEleitos() {
        return eleitos;
    }

    public void aumentaEleitos() {
        eleitos++;
    }

    public void aumentaVotosPartido() {
        votosPartido++;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
