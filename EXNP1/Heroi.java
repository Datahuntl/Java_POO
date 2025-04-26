public class Heroi extends Personagem implements Combater {

    Heroi(String nome, int vida) {
        super(nome, vida);
    }

    @Override
    public void usarEspecial(Personagem personagem) {
        System.out.println("Heroí, " + nome + " está usando seu especial!!");
        for (int i = 0; i < personagem.armas.length; i++) {
            if(personagem.armas[i] != null)
            {
                personagem.armas[i] = null;
            }
        }
    }
}
