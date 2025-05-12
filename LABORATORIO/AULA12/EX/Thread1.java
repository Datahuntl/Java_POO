import java.util.concurrent.TimeUnit;

public class Thread1 extends Thread{

    @Override
    public void run()
    {
        for (int i = 1; i < 11; i++) {
            System.out.println("THREAD 1: " + i);
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        
    }
}
