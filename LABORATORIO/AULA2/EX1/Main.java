public class main {

    public static void main(String[] args)
    {
        System.out.println(calculaFatorial(4));
        mostraPares(10);
    }

    public static int equacao = 0;

    public static int calculaFatorial(int n)
    {
        int fatorial;
        fatorial = n - 1;

        if(equacao == 0)
        {
            equacao = n;
        }

        if(n > 1)
        {
            equacao = equacao * (n - 1);
            calculaFatorial(fatorial);

        } else
        {
            return equacao;
        }
        return equacao;
    }

    public static void mostraPares(int n)
    {
        if(n > 0)
        {
            if(n % 2 == 0)
            {
                System.out.println(n);
                mostraPares(n - 1);
            }
            else
            {
                mostraPares(n - 1);
            }
        }

    }
}
