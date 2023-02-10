# merge-sorter
Test task for one famous company (no FAAGN)

Инструкция по запуску merge-sorter.jar:
Скачать исполняемый файл merge-sorter.jar
merge-sorter/out

Запуск приложения: java -jar <путь к merge-sorter.jar> <режим сортировки> <тип данных> <имя выходного файла> <имена входных файлов и путь к ним>

Параметры программы:
(задаются при запуске через аргументы командной строки, по порядку):

режим сортировки: -a или -d, необязательный, по умолчанию сортируем по возрастанию;
тип данных: -s или -i, обязательный;
имя выходного файла, обязательное;
остальные параметры - имена входных файлов, не менее одного.
Примеры запуска из командной строки для Windows:
java -jar merge-sorter.jar -i -a out.txt in1.txt in2.txt (для целых чисел по возрастанию);

java -jar merge-sorter.jar -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию);

java -jar merge-sorter.jar -d -s out.txt in1.txt in2.txt (для строк по убыванию);

Особенности реализации:
Возможны потери данных, если на вход подаются не отсортированные(или частично отсортированные) входные файлы.
Пустые строки в файлах пропускаются и не учитываются при сортировке слиянием.
При обработке чисел, если строку невозможно преобразовать к числу, она будет пропущена.
Все виды ошибок выводятся в консоль.
Версия Java 17
