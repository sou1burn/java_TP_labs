import java.util.concurrent.atomic.AtomicInteger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;


public class Ekzamen {

    public static void main(String[] args) {
        int[] array = new int[1000];

        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        System.out.println("Sum in cycle = " + sum);
        int result = multiThreadedSumOfArray(array, 4);
        System.out.println("Total Sum: " + result); 

        double avg = calculateAverageIn2Threads(array);
        System.out.println("Average: " + avg);

        int sum1 = countSumIn2Threads(array);
        System.out.println("Sum = " + sum1);

        int minimum = findMinimumUsing2Threads(array);
        System.out.println("Minimum = " + minimum);

        int[] test = {1, 5, 6, 3, 9, 9};
        int mul = calculateMultiplicationOfArrayIn2Threads(test);
        System.out.println("Mul = " + mul);

        System.out.println("Counter = " + synchroCounter(10));

    }

    public static int multiThreadedSumOfArray(int[] arr, int threadCount) {

        if (arr == null || arr.length == 0 || threadCount <= 0)
            throw new IllegalArgumentException();

        threadCount = Math.min(threadCount, arr.length);
        
        int[] partialSums = new int[threadCount];
        
        Thread[] threads = new Thread[threadCount];

        int chunkSize = arr.length / threadCount;
        int remainder = arr.length % threadCount;
        int start = 0;

        for (int i = 0; i < threadCount; ++i) {
            int idx = i;
            int begin = start;
            int end = start + chunkSize + (idx < remainder ? 1 : 0);

            start = end;

            threads[i] = new Thread(() -> {
                int sum = 0;
                for (int j = begin; j < end; j++) {
                    sum += arr[j];
                }
                partialSums[idx] = sum;
            });
            
            threads[i].start();
        }

        for (int i = 0; i < threadCount; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted", e);
            }
        }
        int totalSum = 0;
        for (int i = 0; i < partialSums.length; ++i) {
            totalSum += partialSums[i];
        }

        return totalSum;
    }

    public static double calculateAverageIn2Threads(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();

        int[] partialSums = new int[2];

        int mid = arr.length / 2;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < mid; i++) {
                partialSums[0] += arr[i];
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = mid; i < arr.length; i++) {
                partialSums[0] += arr[i];
            }
        });
        
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int total = partialSums[0] + partialSums[1];

        return (double) total / arr.length;
    }

    public static int findMinimumUsing2Threads(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();
        
        int mid = arr.length / 2;
        final int[] results = {0, 0};

        Thread t1 = new Thread(() -> {
            int localMin = arr[0];
            for (int i = 1; i < mid; i++) {
                localMin = Math.min(localMin, arr[i]);
            }
            results[0] = localMin;
        });

        Thread t2 = new Thread(() -> {
            int localMin = arr[mid];
            for (int i = mid + 1; i < arr.length; i++) {
                localMin = Math.min(localMin, arr[i]);
            }
            results[1] = localMin;
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return Math.min(results[0], results[1]);
    }

    public static int countSumIn2Threads(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();

        int mid = arr.length / 2;
        final int[] results = {0, 0};

        Thread t1 = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < mid; i++) {
                sum += arr[i];
            }
            results[0] += sum;
        });

        Thread t2 = new Thread(() -> {
            int sum = 0;
            for (int i = mid; i < arr.length; i++) {
                sum += arr[i];
            }
            results[1] += sum;
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return results[0] + results[1];
    }

    public static int calculateMultiplicationOfArrayIn2Threads(int[] arr) {
        if (arr == null || arr.length == 0) 
            throw new IllegalArgumentException();
        
        int[] res = {0, 0};

        int mid = arr.length / 2;

        Thread th1 = new Thread(() -> {
            int sum = 1;
            for (int i = 0; i < mid; i++) {
                sum *= arr[i];
            }
            res[0] += sum;
        });

        Thread th2 = new Thread(() -> {
            int sum = 1;
            for (int i = mid; i < arr.length; i++) {
                sum *= arr[i];
            }
            res[1] += sum;
        });

        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return res[0] * res[1];
    }

    public static int synchroCounter(int iterations) {
        int[] sum = {0};
        Object obj1 = new Object();

        Thread th1 = new Thread(() -> {
            synchronized(obj1) {
                for (int i = 0; i < iterations; i++) {
                    sum[0]++;
                }
            }
        });

        Thread th2 = new Thread(() -> {
            synchronized(obj1) {
                for (int j = 0; j < iterations; j++) {
                    sum[0]++;
                }
            }
        });

        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return sum[0];
    }

    public static void TcpReceiver(int port) {
        try (ServerSocket socket = new ServerSocket(port)){
            System.out.println("Listening on port " + port);

            Socket sock = socket.accept();
            System.out.println("Client connected");

            InputStream in = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Received" + line);
            }
            sock.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void UdpReceiver(int port) {
        try (DatagramSocket socket = new DatagramSocket(port)){
            System.out.println("Listening on UDP port " + port);
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); 

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + received);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void UdpRemembering(int port) {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Udp listen on port" + port);
            byte[] buf = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String recv = new String(packet.getData(), 0, packet.getLength());
                System.out.println("received " + recv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UdpReceive(int port) {
        try (DatagramSocket sock = new DatagramSocket(port)) {
            byte[] buf = new byte[1024];
            DatagramPacket pack = new DatagramPacket(buf, buf.length);
            sock.receive(pack);
            String received = new String(pack.getData(), 0, pack.getLength());
            System.out.println("received " + received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UdpSend(int port) {
        try (DatagramSocket sock = new DatagramSocket()) {
            InetAddress addr = InetAddress.getLocalHost();
            byte[] buf = new byte[1024];
            DatagramPacket pack = new DatagramPacket(buf, buf.length, addr, port);
            sock.send(pack);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void TcpSend(int port) {
        try (Socket sock = new Socket()) {
            InputStream inp = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) 
                    break;
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void TcpRemembering(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Tcp listen on port" + port);
            Socket sock = serverSocket.accept();
            System.out.println("Tcp client connected");           
            InputStream input = sock.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("recv = " + line);
            }
            sock.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static int multiThreadedMinimumOfArray(int[] arr, int threadCount) {
    //     if (arr == null || arr.length == 0 || threadCount <= 0)
    //         throw new IllegalArgumentException();

    //     threadCount = Math.min(threadCount, arr.length);
    //     AtomicInteger minimalInteger = new AtomicInteger(Integer.MAX_VALUE);

    //     Thread[] threads = new Thread[threadCount];

    //     int chunkSize = arr.length / threadCount;
    //     int remainder = arr.length % threadCount;
    //     int start = 0;

    //     for (int i = 0; i < threadCount; i++) {
    //         int begin = start;
    //         int end = begin + chunkSize + (i < remainder ? 1 : 0);
    //         start = end;

    //         threads[i] = new Thread(() -> {
    //             int localMin = Integer.MAX_VALUE;
    //             for (int j = begin; j < end; j++) {
    //                 localMin = Math.min(localMin, arr[j]);
    //                 if ((j - begin + 1) % 2 == 0) {
    //                     minimalInteger.getAndUpdate(x -> Math.min(x, localMin));
    //                 }
    //             }
    //             minimalInteger.getAndUpdate(x -> Math.min(x, localMin));
    //         });
    //         threads[i].start();
    //     }
        
    //     Thread monitor = new Thread(() -> {
    //         try {
    //             while (true) {
    //                 System.out.println("Current minimum: " + minimalInteger.get());
    //                 Thread.sleep(2500);
    //             }
    //         } catch (InterruptedException ignored) {}
    //     });
        
    //     monitor.setDaemon(true); 
    //     monitor.start();

    //     for (Thread thread : threads) {
    //         try {
    //             thread.join();
    //         } catch (InterruptedException e) {
    //             Thread.currentThread().interrupt();
    //         }
    //     }

    //     System.out.println("Resulting total min = " + minimalInteger);

    //     return minimalInteger.get();
    // }
}