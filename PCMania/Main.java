import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SistemaOperacional S1 = new SistemaOperacional("Linux Ubuntu", 32); // Sistema Operacional Computador Promoção 1
        SistemaOperacional S2 = new SistemaOperacional("Windows 8", 64); // Sistema Operacional Computador Promoção 2
        SistemaOperacional S3 = new SistemaOperacional("Windows 10", 64); // Sistema Operacional Computador Promoção 3

        // ----------------- Hardware Básico Computador Promoção 1 ------------------------
        HardwareBasico processador1 = new HardwareBasico("Pentium Core i3", 2200);
        HardwareBasico memoriaRAM1 = new HardwareBasico("Memória RAM", 8);
        HardwareBasico memoriaDisco1 = new HardwareBasico("HD", 500);
        HardwareBasico[] hardwareBasico1 = new HardwareBasico[3];
        hardwareBasico1[0] = processador1;
        hardwareBasico1[1] = memoriaRAM1;
        hardwareBasico1[2] = memoriaDisco1;

        // ----------------- Hardware Básico Computador Promoção 2 ------------------------
        HardwareBasico processador2 = new HardwareBasico("Pentium Core", 3370);
        HardwareBasico memoriaRAM2 = new HardwareBasico("Memória RAM", 16);
        HardwareBasico memoriaDisco2 = new HardwareBasico("HD", 1000);
        HardwareBasico[] hardwareBasico2 = new HardwareBasico[3];
        hardwareBasico2[0] = processador2;
        hardwareBasico2[1] = memoriaRAM2;
        hardwareBasico2[2] = memoriaDisco2;

        // ----------------- Hardware Básico Computador Promoção 3 ------------------------
        HardwareBasico processador3 = new HardwareBasico("Pentium Core", 4500);
        HardwareBasico memoriaRAM3 = new HardwareBasico("Memória RAM", 32);
        HardwareBasico memoriaDisco3 = new HardwareBasico("HD", 2000);
        HardwareBasico[] hardwareBasico3 = new HardwareBasico[3];
        hardwareBasico3[0] = processador3;
        hardwareBasico3[1] = memoriaRAM3;
        hardwareBasico3[2] = memoriaDisco3;

        MemoriaUSB mUSB1 = new MemoriaUSB("Pen-drive", 16); // Memória USB Computador Promoção 1
        MemoriaUSB mUSB2 = new MemoriaUSB("Pen-drive", 32); // Memória USB Computador Promoção 2
        MemoriaUSB mUSB3 = new MemoriaUSB("HD Externo", 1000); // Memória USB Computador Promoção 3

        Computador C1 = new Computador("Apple", 1875, S1, hardwareBasico1);
        Computador C2 = new Computador("Samsung", 1875+1234, S2, hardwareBasico2);
        Computador C3 = new Computador("Dell", 1875+5678, S3, hardwareBasico3);

        C1.addMemoriaUSB(mUSB1);
        C2.addMemoriaUSB(mUSB2);
        C3.addMemoriaUSB(mUSB3);

        Cliente cliente = new Cliente("Admin", 1111111);

        // compra
        int codigoCompra;
        do {
            System.out.println("--- Promoções Disponíveis ---");
            System.out.println("\n-----------------------");
            System.out.println("\nCódigo 1: Computador Apple - R$" + C1.preco);
            C1.mostraPCConfigs();
            System.out.println("\n-----------------------");
            System.out.println("\nCódigo 2: Computador Samsung - R$" + C2.preco);
            C2.mostraPCConfigs();
            System.out.println("\n-----------------------");
            System.out.println("\nCódigo 3: Computador Dell - R$" + C3.preco);
            C3.mostraPCConfigs();
            System.out.println("\n-----------------------");
            System.out.println("\nDigite o código da promoção que deseja comprar (ou 0 para finalizar):");
            codigoCompra = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (codigoCompra) {
                case 1:
                    System.out.println("Você escolheu o Computador Apple.");
                    cliente.adicionarComputador(C1);
                    break;
                case 2:
                    System.out.println("Você escolheu o Computador Samsung.");
                    cliente.adicionarComputador(C2);
                    break;
                case 3:
                    System.out.println("Você escolheu o Computador Dell.");
                    cliente.adicionarComputador(C3);
                    break;
                case 0:
                    System.out.println("Finalizando a compra.");
                    break;
                default:
                    System.out.println("Código inválido. Por favor, tente novamente.");
            }
        } while (codigoCompra != 0);

        // informações da compra
        System.out.println("\n--- Informações da Compra ---");
        System.out.println("Informações do Cliente:");
        System.out.println("Nome: " + cliente.nome);
        System.out.println("CPF: " + cliente.cpf);

        System.out.println("\nPC's Adquiridos:");
        if (cliente.computadoresComprados[0] == null) {
            System.out.println("Nenhum PC foi adquirido.");
        } else {
            for (Computador computador : cliente.computadoresComprados) {
                if (computador != null) {
                    computador.mostraPCConfigs();
                    System.out.println("---");
                }
            }
        }

        System.out.println("Total da Compra: R$ " + cliente.calculaTotalCompra());

        scanner.close();
    }
}
