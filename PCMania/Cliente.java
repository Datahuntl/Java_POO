public class Cliente {
    String nome;
    long cpf;
    public Computador[] computadoresComprados = new Computador[2];

    public Cliente(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public void adicionarComputador(Computador computador) { // cliente composição ou aggregations? deixa que vai de aggregations entao
        for (int i = 0; i < computadoresComprados.length; i++) {
            if(computadoresComprados[i] == null)
            {
                computadoresComprados[i] = computador;
                break;
            }
            System.out.println("Limite de computadores comprados atingido.");
        }
    }

    public float calculaTotalCompra() {
        float total = 0.0f;
        for (Computador computadoresComprado : computadoresComprados) { // o pai ta chato com o for enhanced
            if (computadoresComprado != null) {
                total += computadoresComprado.preco;
            }
        }
        return total;
    }
}
