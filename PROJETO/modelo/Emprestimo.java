package biblioteca.modelo;

import java.time.LocalDate;

public class Emprestimo {

    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal; // Null se ainda não devolvido

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = null;
    }

    // Getters
    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    // Setter para dataDevolucaoReal
    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public boolean estaAberto() {
        return dataDevolucaoReal == null;
    }

    @Override
    public String toString() {
        return "Empréstimo: Livro: '" + livro.getTitulo() + "' (ISBN: " + livro.getIdentificadorUnico() + ") para Usuário: '" + usuario.getNome() + "' (ID: " + usuario.getIdUsuario() +
                "), Empréstimo em: " + dataEmprestimo + ", Devolução Prevista: " + dataDevolucaoPrevista +
                (dataDevolucaoReal != null ? ", Devolvido em: " + dataDevolucaoReal : ", Status: Em Aberto");
    }
}
