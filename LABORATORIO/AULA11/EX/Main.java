public class Main {
    public static void main(String[] args) {
        Arquivo arq = new Arquivo();
        Jogo J1 = new Jogo(1, "Batman Arkham City", 59.99);
        Jogo J2 = new Jogo(2, "Kingdom Come Deliverance: II", 99.99);
        Jogo J3 = new Jogo(3, "Grand Theft Auto V", 129.99);

        arq.lerJogos();

        arq.escrever(J1);

        arq.lerJogos();

        arq.escrever(J3);

        arq.lerJogos();

        arq.escrever(J2);

        arq.lerJogos();

        arq.lerJogo(99.99);
    }
}
