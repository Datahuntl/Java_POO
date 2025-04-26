public class Vilao extends Personagem implements Combater{

    Vilao(String nome, int vida) {
        super(nome, vida);
    }

    @Override
    public void usarEspecial(Personagem personagem) {
        System.out.println("Vilão, " + nome + " está usando seu especial!!");
        personagem.vida = 0;
    }
}
