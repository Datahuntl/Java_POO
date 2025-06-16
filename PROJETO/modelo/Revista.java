package biblioteca.modelo;

public class Revista extends ItemBiblioteca {

    private int numeroEdicao;
    private int anoPublicacao;

    public Revista(String titulo, String issn, int numeroEdicao, int anoPublicacao) {
        super(titulo, issn);
        this.numeroEdicao = numeroEdicao;
        this.anoPublicacao = anoPublicacao;
    }

    public int getNumeroEdicao() {
        return numeroEdicao;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public String getDescricaoCompleta() {
        return "Revista: '" + titulo + "', ISSN: " + identificadorUnico + ", Edição: " + numeroEdicao + ", Ano: " + anoPublicacao;
    }

    @Override
    public String toString() {
        return getDescricaoCompleta();
    }
}
