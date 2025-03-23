package Deadlock;

public class Deadlock{
    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("t1 accessed lock 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) { }
                synchronized (lock2) {
                    System.out.println("t1 accessed lock 2");
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("t2 accessed lock 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) { }
                synchronized (lock1) {
                    System.out.println("t2 accessed lock 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}