public class Saga {
        public int nota;
        Livro[] livros = new Livro[3];

        public Saga(int nota) {
            this.nota = nota;
        }

        public void listarLivros()
        {
            for (int i = 0; i < livros.length; i++) {
                if(livros[i] != null) {
                    System.out.println(livros[i].titulo);
                    System.out.println(livros[i].paginas);
                }
            }
        }
        public void adicionarLivro(Livro livro)
        {
            for (int i = 0; i < livros.length; i++) {
                if(livros[i] == null)
                {
                    livros[i] = livro;
                    break;
                }

            }
        }

}

