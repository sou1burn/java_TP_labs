
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
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, /*host, port*/);
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
![[Pasted image 20250610012015.png]]
Работа сокетов:
TCP:
ServerSocket -- сокет сервера
Socket -- сокет клиента = ServerSocket.accept()
InputStream in = Socket.getInputStream()
BufferedReader reader = new BufferedReader(new InputStreamReader(in))
String line = reader.readLine()


HTML:
```
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Заголовок страницы</title>
</head>
<body>
  <!-- Содержимое страницы -->
</body>
</html>
```

```
|Тег|Назначение|
|---|---|
|`<html>`|Корневой элемент HTML-документа|
|`<head>`|Метаинформация (заголовок, кодировка)|
|`<title>`|Заголовок вкладки браузера|
|`<body>`|Всё видимое содержимое страницы|
|`<h1>`...`<h6>`|Заголовки разного уровня|
|`<p>`|Абзац текста|
|`<a href="...">`|Ссылка|
|`<img src="..." alt="...">`|Картинка|
|`<ul>` `<ol>` `<li>`|Списки|
|`<table>`, `<tr>`, `<td>`, `<th>`|Таблицы|
|`<div>`|Блок-контейнер|
|`<span>`|Строчный контейнер|
|`<br>`|Перенос строки|
|`<hr>`|Горизонтальная линия|
```


### Ссылка
`<a href="https://yandex.ru" target="_blank">Перейти на Яндекс</a>`

###  Картинка

`<img src="image.jpg" alt="Описание">`

###  Список

`<ul>   
`<li>Пункт 1</li>   
`<li>Пункт 2</li> 
`</ul>`
###  Таблица 2×2

`<table border="1">   
`<tr><th>Имя</th><th>Возраст</th></tr>`
`<tr><td>Анна</td><td>21</td></tr> 
`</table>`
### Заголовки

HTML предоставляет шесть уровней заголовков:

`<h1>Заголовок первого уровня</h1>`
`<h2>Заголовок второго уровня</h2>
`<h3>Заголовок третьего уровня</h3>
`<h4>Заголовок четвертого уровня</h4>
`<h5>Заголовок пятого уровня</h5>
`<h6>Заголовок шестого уровня</h6>

### Параграфы и форматирование текста
```html
<p>Это параграф текста.</p>

<!-- Разрыв строки -->
<p>Первая строка<br>Вторая строка</p>

<!-- Горизонтальная линия -->
<hr>

<!-- Выделение текста -->
<strong>Жирный текст</strong>
<em>Курсивный текст</em>
<mark>Выделенный текст</mark>
<small>Мелкий текст</small>
<del>Зачеркнутый текст</del>
<ins>Подчеркнутый текст</ins>
<sub>Подстрочный текст</sub>
<sup>Надстрочный текст</sup>
<code>Код</code>
<pre>Предварительно отформатированный текст
  с сохранением пробелов
    и переносов строк</pre>
```

## Списки
### Маркированный список (ненумерованный)
```html
<ul>
    <li>Элемент 1</li>
    <li>Элемент 2</li>
    <li>Элемент 3</li>
</ul>
```

### Нумерованный список
```html
<ol>
    <li>Первый элемент</li>
    <li>Второй элемент</li>
    <li>Третий элемент</li>
</ol>
```

**Атрибуты нумерованного списка:**

```html
<!-- Начать с определенного номера -->
<ol start="5">
    <li>Пятый элемент</li>
    <li>Шестой элемент</li>
</ol>

<!-- Изменить тип нумерации -->
<ol type="A">
    <li>Элемент A</li>
    <li>Элемент B</li>
</ol>
```

### Список определений
```html
<dl>
    <dt>Термин 1</dt>
    <dd>Определение термина 1</dd>
    <dt>Термин 2</dt>
    <dd>Определение термина 2</dd>
</dl>
```

### Вложенные списки
```html
<ul>
    <li>Элемент 1</li>
    <li>Элемент 2
        <ul>
            <li>Вложенный элемент 2.1</li>
            <li>Вложенный элемент 2.2</li>
        </ul>
    </li>
    <li>Элемент 3</li>
</ul>
```

## Ссылки

### Базовая ссылка

```html
<a href="https://example.com">Текст ссылки</a>
```

### Дополнительные атрибуты ссылок

```html
<!-- Открыть в новой вкладке -->
<a href="https://example.com" target="_blank">Открыть в новой вкладке</a>

<!-- Добавить подсказку -->
<a href="https://example.com" title="Посетить Example">Example</a>

<!-- Ссылка на email -->
<a href="mailto:example@example.com">Отправить email</a>

<!-- Ссылка на телефон -->
<a href="tel:+1234567890">Позвонить</a>

<!-- Ссылка на якорь (внутри страницы) -->
<a href="#section1">Перейти к разделу 1</a>

<!-- Якорь (место назначения) -->
<h2 id="section1">Раздел 1</h2>

<!-- Скачивание файла -->
<a href="file.pdf" download>Скачать PDF</a>
```

## Таблицы
### Базовая структура таблицы


```html
<table>
    <caption>Название таблицы</caption>
    <thead>
        <tr>
            <th>Заголовок 1</th>
            <th>Заголовок 2</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Ячейка 1</td>
            <td>Ячейка 2</td>
        </tr>
        <tr>
            <td>Ячейка 3</td>
            <td>Ячейка 4</td>
        </tr>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="2">Итого</td>
        </tr>
    </tfoot>
</table>
```

## Формы

### Базовая структура формы

```html
<form action="/submit" method="post">
    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required>
    
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    
    <input type="submit" value="Отправить">
</form>
```

### Типы полей ввода

```html
<!-- Текстовое поле -->
<input type="text" name="username" placeholder="Введите имя пользователя">

<!-- Пароль -->
<input type="password" name="password" placeholder="Введите пароль">

<!-- Email -->
<input type="email" name="email" placeholder="example@domain.com">

<!-- Телефон -->
<input type="tel" name="phone" pattern="[0-9]{10}" placeholder="1234567890">

<!-- Число -->
<input type="number" name="quantity" min="1" max="100" step="1" value="1">

<!-- Диапазон (слайдер) -->
<input type="range" name="rating" min="1" max="10" value="5">

<!-- Дата -->
<input type="date" name="birthdate">

<!-- Время -->
<input type="time" name="meeting-time">

<!-- Цвет -->
<input type="color" name="favorite-color" value="#ff0000">

<!-- Флажок (чекбокс) -->
<input type="checkbox" id="subscribe" name="subscribe" checked>
<label for="subscribe">Подписаться на рассылку</label>

<!-- Переключатель (радиокнопка) -->
<input type="radio" id="male" name="gender" value="male">
<label for="male">Мужской</label>
<input type="radio" id="female" name="gender" value="female">
<label for="female">Женский</label>

<!-- Выпадающий список -->
<label for="country">Страна:</label>
<select id="country" name="country">
    <option value="">Выберите страну</option>
    <option value="ru">Россия</option>
    <option value="us">США</option>
    <option value="gb">Великобритания</option>
</select>

<!-- Многострочное текстовое поле -->
<label for="message">Сообщение:</label>
<textarea id="message" name="message" rows="4" cols="50"></textarea>

<!-- Загрузка файлов -->
<input type="file" name="document" accept=".pdf,.doc,.docx">
<input type="file" name="images" accept="image/*" multiple>

<!-- Скрытое поле -->
<input type="hidden" name="user_id" value="123">

<!-- Кнопка отправки формы -->
<input type="submit" value="Отправить">

<!-- Кнопка сброса формы -->
<input type="reset" value="Сбросить">

<!-- Обычная кнопка -->
<button type="button">Нажми меня</button>
```

### Группировка элементов формы
```html
<form>
    <fieldset>
        <legend>Личная информация</legend>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name">
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email">
    </fieldset>
    
    <fieldset>
        <legend>Дополнительная информация</legend>
        <label for="comments">Комментарии:</label>
        <textarea id="comments" name="comments"></textarea>
    </fieldset>
    
    <input type="submit" value="Отправить">
</form>
```

### Атрибуты для валидации форм

```html
<!-- Обязательное поле -->
<input type="text" name="username" required>

<!-- Шаблон (регулярное выражение) -->
<input type="text" name="code" pattern="[A-Za-z]{3}-[0-9]{2}" title="Формат: XXX-00">

<!-- Минимальная и максимальная длина -->
<input type="text" name="username" minlength="3" maxlength="20">

<!-- Отключение автозаполнения -->
<input type="text" name="username" autocomplete="off">

<!-- Автофокус при загрузке страницы -->
<input type="text" name="search" autofocus>

<!-- Только для чтения -->
<input type="text" name="id" value="12345" readonly>

<!-- Отключенное поле -->
<input type="text" name="status" value="Неактивно" disabled>
```