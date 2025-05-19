public abstract class Item implements AplicarDesconto {
    private String nome;
    private double preco;
    private int estoque;
    private int raridade;

    public Item(String nome, double preco, int estoque, int raridade) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.raridade = raridade;
    }

    public boolean verificarEstoque(int quantidade)
    {
        if(quantidade <= estoque)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public void mostraInfo()
    {
        System.out.println("Nome:" + nome);
        System.out.println("Preço: " + preco);
        System.out.println("Estoque: " + estoque);
        System.out.println("Raridade: " + raridade);
    }

    @Override
    public double aplicarDesconto(double porcentagem)
    {
        this.preco = preco * porcentagem;
        return preco * porcentagem;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public int getRaridade() {
        return raridade;
    }
}
