package biblioteca.excecao;

public class LivroNaoDisponivelException extends Exception {
    public LivroNaoDisponivelException(String message) {
        super(message);
    }
}
