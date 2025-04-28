public class Inventario {
    Livro[] livros = new Livro[7];

    public void adicionarLivro(Livro livro) throws LivroInvalidoException
    {
        for (int i = 0; i < livros.length; i++)
        {
            if(livro.getTitulo().isEmpty())
            {
                throw new LivroInvalidoException("Título do livro está vazio.");
            }
            else if (livro.getPreco() < 0)
            {
                throw new LivroInvalidoException("Preço do livro é menor que zero.");
            }
            else
            {
                if(livros[i] == null)
                {
                    livros[i] = livro;
                    break;
                }
            }
        }
    }

    public void exibirLivros()
    {
        for (int i = 0; i < livros.length; i++)
        {
            if(livros[i] != null)
            {
                System.out.println("Titulo: " + livros[i].getTitulo() +
                        " Autor: " + livros[i].getAutor() + " Preço: " + livros[i].getPreco());
            }
        }
    }
}
