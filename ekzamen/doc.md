
#Билеты #Теория

# Билеты 

## Билет 1
1) Программа, которая находит сумму всех элементов массива. Написать с использованием многопоточности. 
```
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
```
2) Программа, которая находит минимальный элемент в массиве. Написать с использованием многопоточности. Программа должна работать так, чтобы в любой момент можно было обратиться к потоку и узнать минимум на текущий момент (я так понимаю, это про synchronized)
```

```
3) Написать фрагмент кода, отвечающий за получение пакета для протокола TCP
```
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
```
4) Написать фрагмент HTML-кода, который создаёт таблицу 3×3, заполненную числами
```
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>3x3 Table</title>
  <style>
    table {
      border-collapse: collapse;
    }
    td {
      border: 1px solid black;
      padding: 10px;
      text-align: center;
    }
  </style>
</head>
<body>
<table>
  <tr>
    <td>1</td>
    <td>2</td>
    <td>3</td>
  </tr>
  <tr>
    <td>4</td>
    <td>5</td>
    <td>6</td>
  </tr>
  <tr>
    <td>7</td>
    <td>8</td>
    <td>9</td>
  </tr>
</table>
</body>
</html>
```
# Теория
