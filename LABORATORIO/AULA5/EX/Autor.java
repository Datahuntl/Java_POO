public class Autor {
    public String nome;
    Saga[] sagas;

    public Autor(String nome) {
        this.nome = nome;
        sagas = new Saga[3];
    }

    public void listarSagas()
    {
        for (int i = 0; i < sagas.length; i++) {
            if(sagas[i] != null) {
                System.out.println(sagas[i].nota);
                sagas[i].listarLivros();
            }
        }
    }

    public void adicionarSaga(Saga saga)
    {
        for (int i = 0; i < sagas.length; i++) {
            if(sagas[i] == null)
            {
                sagas[i] = saga;
                break;
            }

        }
    }

}
