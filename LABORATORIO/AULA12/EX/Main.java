public class Main {
    public static void main(String[] args) {

        Thread1 T1 = new Thread1();
        Thread2 T2 = new Thread2();
        Thread3 T3 = new Thread3();

        T1.start();
        T2.start();
        T3.start();
    }
}
