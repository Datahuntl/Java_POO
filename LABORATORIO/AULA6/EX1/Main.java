public class Main {
    public static void main(String[] args) {
        double raio = 2;
        double altura = 2;

        System.out.println(Calculadora.circunferencia(raio));
        System.out.println(Calculadora.volumeEsfera(raio));
        System.out.println(Calculadora.volumeCilindro(raio, altura));
    }
}
