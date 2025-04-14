import java.util.ArrayList;

public class Estoque {
    private ArrayList<Item> itensArray = new ArrayList<>();

    public void adicionarArray(Item item)
    {
        itensArray.add(item);
    }

    public void buscar(int posicao)
    {
        System.out.println(itensArray.get(posicao).getNome());
        System.out.println(itensArray.get(posicao).getValor());
    }

    public void deletar(int posicao)
    {
        System.out.println(itensArray.get(posicao).getNome());
        System.out.println(itensArray.get(posicao).getValor());
        itensArray.remove(posicao);
    }

    public void deletar(Item item)
    {
        itensArray.remove(item);
    }

    public void modificar(Item item, int posicao)
    {
        itensArray.set(posicao, item);
        System.out.println(itensArray.get(posicao).getNome());
        System.out.println(itensArray.get(posicao).getValor());
    }
}
