armas[posicao].getDano()public class Main {
    public static void main(String[] args) {

        Heroi H1 = new Heroi("Connan", 125);
        Vilao V1 = new Vilao("Gru", 75);

        Arma A1 = new Arma("Excalibur", 30, 15);
        Arma A2 = new Arma("Varinha da Morte", 100, 5);
        Arma A3 = new Arma("Laser Congelante", 15, 10);
        Arma A4 = new Arma("Arco de Ulysses", 25, 20);

        H1.addArma(A1);
        H1.addArma(A4);

        V1.addArma(A2);
        V1.addArma(A3);

        H1.mostrarArma();
        V1.mostrarArma();

        H1.atacar(0,V1);
        V1.atacar(1,H1);

        H1.usarEspecial(V1);
        V1.usarEspecial(H1);
    }
}
