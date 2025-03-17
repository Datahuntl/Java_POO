public class Main {
    public static void main(String[] args) {
        Banda B = new Banda("Mamonas Assasinas", 4);

        Musica M1 = new Musica("Musica 1", "Album 0");
        Musica M2 = new Musica("Musica 2", "Album 0");

        B.adicionarMusica(M1);
        B.adicionarMusica(M2);

        B.tocarMusica(1);
        B.tocarMusica(0);
        B.tocarMusica(4);

        B.tocarShow();

        B.deletarMusica("Musica 1");

        B.tocarShow();
    }
}
