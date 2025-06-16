package biblioteca.modelo;

public class Usuario {

    private String nome;
    private String idUsuario;

    public Usuario(String nome, String idUsuario) {
        this.nome = nome;
        this.idUsuario = idUsuario;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", ID: " + idUsuario;
    }
}
