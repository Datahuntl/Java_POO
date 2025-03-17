public class Main {
    public static void main(String[] args) {

        Autor A1 = new Autor("nome");

        Saga S1 = new Saga(8);
        Saga S2 = new Saga(7);
        Saga S3 = new Saga(6);

        A1.adicionarSaga(S1);
        A1.adicionarSaga(S2);
        A1.adicionarSaga(S3);

        Livro L1 = new Livro("Livro 1.1", 11);
        Livro L2 = new Livro("Livro 2.1", 21);
        Livro L3 = new Livro("Livro 3.1", 31);

        S1.adicionarLivro(L1);
        S2.adicionarLivro(L2);
        S3.adicionarLivro(L3);

        A1.listarSagas();

    }
}
