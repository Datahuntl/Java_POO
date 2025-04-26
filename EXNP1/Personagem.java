public abstract class Personagem {
    protected String nome;
    protected int vida;
    protected Arma[] armas = new Arma[3];

    Personagem(String nome, int vida)
    {
        this.nome = nome;
        this.vida = vida;
    }

    public void addArma(Arma arma)
    {
        for (int i = 0; i < armas.length; i++) {
            if(armas[i] == null)
            {
                armas[i] = arma;
                break;
            }
        }
    }

    public void mostrarArma()
    {
        for (int i = 0; i < armas.length; i++) {
            if(armas[i] != null)
            {
                System.out.println(armas[i].toString());
            }
        }
    }

    public void atacar(int posicao, Personagem personagem)
    {
        if(armas[posicao].getDurabilidade() > 0)
        {
            if(personagem.vida > 0)
            {
                personagem.vida -= armas[posicao].getDano();
                armas[posicao].setDurabilidade(0);
                System.out.println("Arma, " + armas[posicao].getNome() + " foi usada!");
                System.out.println(personagem.nome + " recebeu " + armas[posicao].getDano() + " de dano!");
            }
            else
            {
                System.out.println("Personagem já foi derrotado...");
            }
        }
        else
        {
            System.out.println("Arma está quebrada...");
        }
    }

}
