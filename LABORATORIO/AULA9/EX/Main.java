public class Main {
    public static void main(String[] args) {

        Item i1 = new Item(39, "Caderno");
        Item i2 = new Item(89, "Calculadora");
        Item i3 = new Item(3999, "Notebook");
        Item i4 = new Item(9, "Caneta");

        Estoque E = new Estoque();

        E.adicionarArray(i1);
        E.adicionarArray(i2);
        E.adicionarArray(i3);
        E.adicionarArray(i4);

        E.buscar(3);

        E.deletar(3);

        E.deletar(i1);

        E.modificar(i2,1);
    }
}
