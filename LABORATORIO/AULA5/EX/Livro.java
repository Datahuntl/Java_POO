public class Livro {
    String titulo;
    int paginas;

    public Livro(String titulo, int paginas) {
        this.titulo = titulo;
        this.paginas = paginas;
    }

    public void infoLivro()
    {
        System.out.println("Titulo: " + titulo);
        System.out.println("Paginas: " + paginas);
    }
}
