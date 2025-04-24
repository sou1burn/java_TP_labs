package InternetCafe;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class InternetCate {

    private static final Random random = new Random();
    private static final int MIN_USAGE_MINUTES = 15;
    private static final int MAX_USAGE_MINUTES = 120;

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число компьютеров N: ");
        int n = scanner.nextInt();

        System.out.println("Введите число пользователей M: ");
        int m = scanner.nextInt();
        scanner.close();
        
        Semaphore computers = new Semaphore(n, true);

        AtomicInteger active = new AtomicInteger(m);

        for (int i = 1; i <= m; ++i) {
            final int id = i;
            new Thread(() -> {
                try {
                    if (!computers.tryAcquire()) {
                        System.out.println("Tourist " + id + " waiting for turn");
                        computers.acquire(); 
                    }
                    System.out.println("Tourist " + id + " is online");

                    int timeOnline = random.nextInt(MAX_USAGE_MINUTES - MIN_USAGE_MINUTES + 1) + MIN_USAGE_MINUTES;
                    Thread.sleep(timeOnline * 100);

                    System.out.println("Tourist " + id + " is off, have spent " + timeOnline + " minutes online");
                    computers.release();
                    if (active.decrementAndGet() == 0) 
                        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
                    
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
