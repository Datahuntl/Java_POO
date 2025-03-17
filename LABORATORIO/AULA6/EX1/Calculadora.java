public class Calculadora {
    private static float PI = 3.14f;

    public static double circunferencia(double raio)
    {
        return 2 * raio * PI;
    }

    public static double volumeEsfera(double raio)
    {
        return (4/3) * PI * (raio * raio);
    }

    public static double volumeCilindro(double raio, double altura)
    {
        return PI * (raio * raio) * altura;
    }
}
