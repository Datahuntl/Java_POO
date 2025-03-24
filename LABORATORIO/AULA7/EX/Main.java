public class Main {
    public static void main(String[] args) {

        Produto P1 = new Produto("Caixa", 5.99);
        Produto P2 = new Produto("Caneta", 2.99);
        Notebook N1 = new Notebook("Acer", 3699.99,8.00,"NVIDIA","INTEL");
        Notebook N2 = new Notebook("Lenovo", 2999.99, 6.00, "Integrada", "INTEL");
        Camiseta C1 = new Camiseta("Lacoste", 129.99,"Verde","Grande");

        Produto[] listaProdutos = new Produto[5];
        listaProdutos[0] = P1;
        listaProdutos[1] = P2;
        listaProdutos[2] = N1;
        listaProdutos[3] = N2;
        listaProdutos[4] = C1;

        for(Produto p : listaProdutos)
        {
            if (p instanceof Notebook)
            {
                System.out.println(p.etiquetaPreco());
            }
            else if (p instanceof Camiseta)
            {
                System.out.println(p.etiquetaPreco());
            }
            else
            {
                System.out.println(p.etiquetaPreco());
            }
        }

    }
}
