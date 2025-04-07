public class Main{
    public static void main(String[] args) {

        Nadador nadador = new Nadador();
        Corredor corredor = new Corredor();
        Ciclista ciclista = new Ciclista();

        nadador.treinar();
        corredor.treinar();
        corredor.colocarEquipamento();
        ciclista.treinar();
        ciclista.colocarEquipamento();
    }
}
