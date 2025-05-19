public class Item_Magico extends Item{
    private int nivelPoder;
    private String descricao;

    public Item_Magico(String nome, double preco, int estoque, int raridade, int nivelPoder, String descricao) {
        super(nome, preco, estoque, raridade);
        this.nivelPoder = nivelPoder;
        this.descricao = descricao;
    }

    @Override
    public void mostraInfo()
    {
        System.out.println("Nivel Poder: " + nivelPoder);
        System.out.println("Descrição: " + descricao);
    }

}
