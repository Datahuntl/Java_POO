package biblioteca.modelo;

public class Livro extends ItemBiblioteca {

    private String autor;
    private int anoPublicacao;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn, int anoPublicacao) {
        super(titulo, isbn);
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.disponivel = true;
    }

    // Getters específicos de Livro
    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String getDescricaoCompleta() {
        return "Livro: '" + titulo + "', Autor: " + autor + ", ISBN: " + identificadorUnico + ", Ano: " + anoPublicacao + ", Disponível: " + (disponivel ? "Sim" : "Não");
    }

    @Override
    public String toString() {
        return getDescricaoCompleta();
    }
}
