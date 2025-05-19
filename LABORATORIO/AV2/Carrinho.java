import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Item> carrinhoArray = new ArrayList<>();

    public void adicionarItem(Item item, int quantidade) throws EstoqueInsuficienteException
    {
        if(item.verificarEstoque(quantidade) == true)
        {
            for (int i = 0; i < quantidade; i++) {
                carrinhoArray.add(item);
            }
        }
        else{
            throw new EstoqueInsuficienteException("Estoque insuficiente para o item pedido.");
        }
    }

    public double calcularTotal() throws CarrinhoVazioException
    {
        double total = 0;
        if(carrinhoArray.isEmpty())
        {
            throw new CarrinhoVazioException("O carrinho esta vazio!");
        }
        else {
            for (int i = 0; i < carrinhoArray.size(); i++) {
                total = total + carrinhoArray.get(i).getPreco();
            }
            return total;
        }
    }

    public void exibirItens() throws CarrinhoVazioException
    {
        if(carrinhoArray.isEmpty())
        {
            throw new CarrinhoVazioException("O carrinho esta vazio!");
        }
        else {
            for (int i = 0; i < carrinhoArray.size(); i++) {
                carrinhoArray.get(i).mostraInfo();
            }
        }
    }
}
