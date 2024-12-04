import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение: ");
        String input = scanner.nextLine();
        String getInput = input.replaceAll("\\s+", ""); // Удаляем пробелы
        System.out.println("Ваше выражение: " + getInput); // Вывод без пробелов
        try {
            String result = calc(getInput); // Передаем очищенное выражение
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close(); // Закрываем сканер
        }
    }

    static String calc(String input) {
        // Проверяем, что строка соответствует формату: "a op b"
        if (!input.matches("\\d{1,2}[+\\-*/]\\d{1,2}")) { // Изменено на {1,2} для поддержки 1-10
            throw new IllegalArgumentException("Неправильный формат выражения.");
        }

        // Извлекаем числа и оператор
        String[] parts = input.split("(?<=[-+*/])|(?=[-+*/])"); // Разделяем строку на части
        int a = Integer.parseInt(parts[0]); // Первое число
        char operator = parts[1].charAt(0); // Оператор
        int b = Integer.parseInt(parts[2]); // Второе число
        // Проверяем, что числа находятся в диапазоне от 1 до 10
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно.");
        }

        // Выполняем операцию в зависимости от оператора
        switch (operator) {
            case '+':
                return String.valueOf(a + b);
            case '-':
                return String.valueOf(a - b);
            case '*':
                return String.valueOf(a * b);
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Ошибка: деление на ноль.");
                }
                return String.valueOf(a / b);
            default:
                throw new IllegalArgumentException("Неподдерживаемый оператор: " + operator);
        }
    }
}
