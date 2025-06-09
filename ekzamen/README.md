
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

То же самое для UDP пакета

```
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

## Билет вариант 1, текущий
1) Напишите код, который вычисляет среднее арифметическое по массиву целых чисел с использованием двух потоков.  
```
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
```
2) Создайте HTML-файл, содержащий таблицу размером 3×2, заполненную произвольными цифрами.  
```
<!DOCTYPE html>
<html>
    <head>

    </head>
<body>

<table>
  <tr>
    <td>1</td>
    <td>2</td>
  </tr>
  <tr>
    <td>4</td>
    <td>5</td>
  </tr>
  <tr>
    <td>7</td>
    <td>8</td>
  </tr>
</table>

</body>
</html>
```
3) Составьте HTTP-запрос (не URL, а разбивку на поля заголовков и тела) к сервису get_user на сайте guap.ru, передающий два параметра: имя (`Василий`) и возраст (`20`).
### GET
```
GET /get_user?name=Василий&age=20 HTTP/1.1
Host: guap.ru
Connection: close
```
### POST с json
```
POST /get_user HTTP/1.1
Host: guap.ru
Content-Type: application/json
Content-Length: 43
Connection: close
```
### не json
```
name=Василий&age=20
```
### json
```
{
  "name": "Василий",
  "age": 20
}
```
## Билет, вариант 2, текущий
Вариант 2
1. Напишите программу, которая ищет минимум по массиву целых чисел с использованием двух потоков
```
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
```
2. Напишите полную HTML страницу, в которой есть ссылка на yandex.ru и ненумерованный список из трех элементов
```
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <p><a href="https://yandex.ru" target="_blank">Yandex</a></p>
    <h2>Список покупок:</h2>
    <ul>
        <li>Хлеб</li>
        <li>Молоко</li>
        <li>Яблоки</li>
    </ul>
</body>
</html>
```
3. а 
Добавьте командами git несколько файлов в репозиторий( команда git add имя файла)
```
git add -A
```
ИЛИ
```
git add blabla.java
git add bebebe.java
```
3. б
Сделайте коммит этих двух файлов
```
git commit -a 
```
ИЛИ
``` 
git commit -m "commit 1"
```
## Билет, вариант 3, текущий
1. Написать код, который ищет сумму элементов по массиву целых чисел с использованием двух потоков
```
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
```
2. Напишите полную HTML страницу, в которой есть ссылка на yandex.ru и заголовок верхнего уровня
```
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Главный заголовок</h1>
    <p><a href="https://yandex.ru" target="_blank">Yandex</a></p>
</body>
</html>
```
3. Есть программа, состоящая из двух файлов Main.java (с функцией main) и Book.java (используется в main). Какие файлы должны быть в book.jar файле этой программы, чтобы можно было ее запустить с помощью команды book.jar 
```
java -jar book.jar
```
Необходимо скомпилировать код 
```
javac Main.java Book.java
```
Создаем текстовый документ (manifest) с правилами создания jar
```manifest.mf
Main-class: Main
```
Создаем jar
```
jar cfm book.jar manifest.mf Main.class Book.class
java -jar book.jar
```
# Теория
