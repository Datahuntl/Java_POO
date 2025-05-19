public class Main {
    public static void main(String[] args) {

        Item_Magico IM1 = new Item_Magico("Escudo de Perseu", 99.99,5,5,3,"Escudo Indestrutível");
        Item_Magico IM2 = new Item_Magico("Laço da Verdade", 159.99, 2, 10,2,"Todos falam a verdade");
        Arma A1 = new Arma("Espada",29.99,9,2,20,"Uma espada simples","Ataque Pesado");

        Carrinho C = new Carrinho();
        Carrinho C2 = new Carrinho();

        IM2.aplicarDesconto(0.3); // desconto de 30%

        try{
            C.adicionarItem(IM1,2);
            System.out.println("Item adicionado.");
        } catch(EstoqueInsuficienteException e)
        {
            System.out.println("Erro: " + e);
        }

        try{
            C.adicionarItem(IM2, 1);
            System.out.println("Item adicionado.");
        } catch(EstoqueInsuficienteException e)
        {
            System.out.println("Erro: " + e);
        }

        try{
            C.adicionarItem(A1, 10);
            System.out.println("Item adicionado.");
        } catch(EstoqueInsuficienteException e)
        {
            System.out.println("Erro: " + e);
        }

        try{
            System.out.println(C.calcularTotal());
        } catch(CarrinhoVazioException e)
        {
            System.out.println("Erro: " + e);
        }

        try{
            System.out.println(C2.calcularTotal());
        } catch(CarrinhoVazioException e)
        {
            System.out.println("Erro: " + e);
        }

        try{
            C.exibirItens();
        } catch(CarrinhoVazioException e)
        {
            System.out.println("Erro: " + e);
        }
    }
}
