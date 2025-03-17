public class Banda {
    private Musica[] musicas = new Musica[2];
    private String nome;
    private int numeroDeIntegrantes;
    
    public Banda(String nome, int numeroDeIntegrantes) {
        this.nome = nome;
        this.numeroDeIntegrantes = numeroDeIntegrantes;
    }
    
    public void tocarMusica(int posicao)
    {
        if(posicao <= musicas.length)
        {
            if(musicas[posicao] != null) {
                System.out.println("------");
                System.out.println(musicas[posicao].getNome());
                System.out.println(musicas[posicao].getAlbum());
                System.out.println("------");
            }
        }
    }
    
    public void tocarShow()
    {
        for (int i = 0; i < musicas.length; i++) {
            if(musicas[i] != null) {
                System.out.println("------");
                System.out.println(musicas[i].getNome());
                System.out.println(musicas[i].getAlbum());
                System.out.println("------");
            }
        }
    }
    
    public boolean adicionarMusica(Musica musica)
    {
        for (int i = 0; i < musicas.length; i++) {
            if(musicas[i] == null)
            {
                musicas[i] = musica;
                return true;
            }
        }
        return false;
    }
    
    public boolean deletarMusica(String nome)
    {
        for (int i = 0; i < musicas.length; i++) {
            if(musicas[i] != null) {
                if(musicas[i].getNome().equals(nome)) {
                    musicas[i] = null;
                    return true;
                }
            }
        }
        return false;
    }
}
