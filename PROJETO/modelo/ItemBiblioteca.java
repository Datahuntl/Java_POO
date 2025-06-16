package biblioteca.modelo;

public abstract class ItemBiblioteca {

    protected String titulo;
    protected String identificadorUnico;

    public ItemBiblioteca(String titulo, String identificadorUnico) {
        this.titulo = titulo;
        this.identificadorUnico = identificadorUnico;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdentificadorUnico() {
        return identificadorUnico;
    }

    public abstract String getDescricaoCompleta();
}
