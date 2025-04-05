public class Computador {
    String marca;
    float preco;
    MemoriaUSB musb; // Computador pode ter 0...1 MemóriaUSB
    SistemaOperacional sysOP; // Computador tem sempre 1 SistemaOperacional
    HardwareBasico[] hardBasico; // Computador tem * HardwareBasico

    public Computador(String marca, float preco, SistemaOperacional sysOP, HardwareBasico[] hardBasico) {
        this.marca = marca;
        this.preco = preco;
        this.sysOP = sysOP;
        this.hardBasico = new HardwareBasico[3];
        // for pra passar todos os corno dentro da array
        for (int i = 0; i < 3; i++) {
            this.hardBasico[i] = hardBasico[i];
        }
    }

    public void mostraPCConfigs()
    {
        System.out.println("Marca: " + marca);
        System.out.println("Preço: " + preco);
        System.out.println("Sistema Operacional: " + sysOP.nome + " (" + sysOP.tipo + " bits)");
        System.out.println("Hardware Básico: ");
        for (int i = 0; i < 3; i++) { // for pra mostra os desgraçado todos
            System.out.println("- " + hardBasico[i].nome + " (" + hardBasico[i].capacidade + " GB / Mhz)");
        }
        if (musb != null) {
            System.out.println("Memória USB: " + musb.nome + " (" + musb.capacidade + " GB)");
        } else {
            System.out.println("Sem Memória USB adicionada.");
        }
    }

    public void addMemoriaUSB(MemoriaUSB musb)
    {
        this.musb = musb;
    }
}
