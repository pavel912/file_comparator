### Hexlet tests and linter status:
[![Actions Status](https://github.com/pavel912/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/pavel912/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/1b5ef77cd38550cebd7b/maintainability)](https://codeclimate.com/github/pavel912/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/1b5ef77cd38550cebd7b/test_coverage)](https://codeclimate.com/github/pavel912/java-project-71/test_coverage)

# Вычислитель различий между файлами
Данный проект представляет собой консольную команду, позволяющую находить различия между json и yaml файлами
и выводить различия в разных форматах.

Параметры команды:
1. filepath1 - путь до первого файла
2. filepath2 - путь до второго файла

Опции команды:
1. -h, --help - выводит описание команды
2. -f, --format - позволяет указать один из возможных форматов вывода результата

Форматы вывода различий:
1. stylish - вывод всех полей в файлах с указанием добавленных, удаленных и измененных полей построчно
2. plain - вывод только подвергшихся изменениям полей в текстовом формате
3. json - вывод только подвергшихся изменениям полей в виде json-файла для дальнейшего использования

Далее представлены примеры использования команды на тестовых данных

#### Example for flat json with stylish format
[![asciicast](https://asciinema.org/a/EkxJXoS8rTbRnAVE6VE3cdI7O.svg)](https://asciinema.org/a/EkxJXoS8rTbRnAVE6VE3cdI7O)

#### Example for flat yaml with stylish format
[![asciicast](https://asciinema.org/a/3nR8raZgUCJizp38GsLTuzu8A.svg)](https://asciinema.org/a/3nR8raZgUCJizp38GsLTuzu8A)

#### Examples for nested yaml and json with stylish format
[![asciicast](https://asciinema.org/a/zU8YvdpNQaMHDlWRyTA0Yt0Ig.svg)](https://asciinema.org/a/zU8YvdpNQaMHDlWRyTA0Yt0Ig)

#### Example for nested json with plain format
[![asciicast](https://asciinema.org/a/dNkhaS0g64jLd964hLZ8zNmqR.svg)](https://asciinema.org/a/dNkhaS0g64jLd964hLZ8zNmqR)

#### Example for json format
[![asciicast](https://asciinema.org/a/YYDXnXVjBZQjbEYh35Cvm3XZK.svg)](https://asciinema.org/a/YYDXnXVjBZQjbEYh35Cvm3XZK)