package ru.job4j.yandextask;

/**
 * Название задачи - Проверка печати
 * Ограничение времени - 1 секунда
 * Ограничение памяти - 64Mb
 * Ввод - стандартный ввод или input.txt
 * Вывод - стандартный вывод или output.txt
 * -
 *  Зимние каникулы... Чудесное время, полное ощущения волшебства, веселых игр на улице в снегу и прочих детских
 * радостей. Но не для Васи. Вася — стартапер. И вот его осенила новая идея — сделать собственный клавиатурный тренажер!
 * Сел Вася и принялся писать код.
 * Дни и ночи напролет Вася усердно трудился. И вот, прототип уже почти дописан, за исключением одной маленькой детали.
 * Тренажер представляет собой серию заданий: запрос к пользователю набрать некоторую строку А.
 * После этого пользователь должен как можно быстрее набрать на экране строку А, используя только латинские буквы и
 * клавиши управления.
 * Вася решил записывать все нажатия клавиш пользователя в отдельный лог. В нем присутствуют маленькие латинские буквы,
 * а также управляющие конструкции
 *   <delete> Удаление символа после текущей позиции курсора.
 *   <bspace> Удаление символа перед текущей позицией курсора.
 *   <left> Курсор перемещается влево на один символ.
 *   <right> Курсор перемещается вправо на один символ.
 * Если управляющая конструкция перемещает курсор за границы текущей строки или пытается удалить несуществующий символ,
 * то ничего не происходит.
 * Теперь у Васи есть строчка, которую должен был набрать пользователь, и последовательность нажатий клавиш, считанная
 * из лога. Помогите Васе выяснить, справился ли пользователь с заданием!
 * -
 * Формат ввода
 *     Даны две строки a и b, разделённые переводом строки, — задание пользователя и последовательность нажатий клавиш
 *     (1 ≤ |a|, |b| ≤ 1000).
 * -
 * Формат вывода
 *     Если пользователь справился с заданием, выведите "Yes" (без кавычек), в противном случае выведите "No".
 * -
 * Пример 1
 *     Ввод:
 *         hellochild
 *         helto<left><bspace>l<delete>ochilds<bspace>
 *     Вывод:
 *         Yes
 * -
 * Пример 2
 *     Ввод:
 *         programming
 *         programming<left><left><right><delete>
 *     Вывод:
 *         No
 */

public class TestTask {
    public String checkStrings(String str1, String str2) {

        StringBuilder build = new StringBuilder();

        int index = 0;
        int position = 0;

        while (index < str2.length()) {

            if (str2.charAt(index) == '<' && str2.charAt(index + 1) == 'l') {
                index += 6;
                if (validPos(position, build)) {
                    position--;
                }
                continue;
            }
            if (str2.charAt(index) == '<' && str2.charAt(index + 1) == 'd') {
                index += 8;
                if (validPos(position, build)) {
                    build.deleteCharAt(position);
                }
                continue;
            }
            if (str2.charAt(index) == '<' && str2.charAt(index + 1) == 'r') {
                index += 7;
                if (validPos(position, build)) {
                    position++;
                }
                continue;
            }
            if (str2.charAt(index) == '<' && str2.charAt(index + 1) == 'b') {
                index += 8;
                if (validPos(position, build)) {
                    build.deleteCharAt(position - 1);
                    position--;
                }
                continue;
            }
                build.insert(position, str2.charAt(index));
                index++;
                position++;
        }

        return str1.contentEquals(build) ? "Yes" : "No";
    }

    private static boolean validPos(int position, StringBuilder build) {
        return position > 0 && position <= build.length();
    }
}
