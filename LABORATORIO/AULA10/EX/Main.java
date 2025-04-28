public class Main {
    public static void main(String[] args){

        Livro L1 = new Livro("A Arca de Noé", "Autor", 99.99);
        Livro L2 = new Livro("Laranja Mecanica", "aUtor", 59.99);
        Livro L3 = new Livro("Metropolis", "auTor", 79.99);
        Livro L4 = new Livro("1984", "autOr", 49.99);
        Livro L5 = new Livro("Admiravel Mundo Novo", "autoR", -19.99);
        Livro L6 = new Livro("", "autor", 20.99);
        Livro L7 = new Livro("", "AUTOR", -20.99);

        Inventario I = new Inventario();

        try{
            I.adicionarLivro(L1);
        } catch (LivroInvalidoException e) {
            System.out.println("Erro: " + e);
        }

        try{
            I.adicionarLivro(L2);
        } catch (LivroInvalidoException e) {
            System.out.println("Erro: " + e);
        }

        try{
            I.adicionarLivro(L3);
        } catch (LivroInvalidoException e) {
            System.out.println("Erro: " + e);
        }

        try{
            I.adicionarLivro(L4);
        } catch (LivroInvalidoException e) {
            System.out.println("Erro: " + e);
        }

        try{
            I.adicionarLivro(L5);
        } catch (LivroInvalidoException e) {
            System.out.println("Erro: " + e);
        }

        try{
            I.adicionarLivro(L6);
        } catch (LivroInvalidoException e) {
            System.out.println("Erro: " + e);
        }

        try{
            I.adicionarLivro(L7);
        } catch (LivroInvalidoException e) {
            System.out.println("Erro: " + e);
        }

        I.exibirLivros();
    }
}
