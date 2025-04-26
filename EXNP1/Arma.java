public class Arma {
    private String nome;
    private int dano;
    private int durabilidade;

    Arma(String nome, int dano, int durabilidade)
    {
        this.nome = nome;
        this.dano = dano;
        this.durabilidade = durabilidade;
    }

    @Override
    public String toString() {
        return "Arma{" +
                "nome='" + nome + '\'' +
                ", dano=" + dano +
                ", durabilidade=" + durabilidade +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }
}
