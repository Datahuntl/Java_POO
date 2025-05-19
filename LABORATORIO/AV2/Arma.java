public class Arma extends Item{
    private int dano;
    private String descricao;
    private String poder;

    public Arma(String nome, double preco, int estoque, int raridade, int dano, String descricao, String poder) {
        super(nome, preco, estoque, raridade);
        this.dano = dano;
        this.descricao = descricao;
        this.poder = poder;
    }

    @Override
    public void mostraInfo()
    {
        System.out.println("Dano: " + dano);
        System.out.println("Descrição: " + descricao);
        System.out.println("Poder: " + poder);
    }
}
