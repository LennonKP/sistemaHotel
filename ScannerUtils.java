import java.util.Scanner;
import java.util.function.Function;

public class ScannerUtils {
    public static <T> T getEntry(Scanner s, String prompt, Function<String, T> parser, Function<T, Boolean> validator) {
        T result = null;
        while (true) {
            System.out.print(prompt);
            String input = s.nextLine().trim();

            try {
                result = parser.apply(input);
                if (validator.apply(result)) {
                    break;
                } else {
                    System.out.println("Entrada inválida, tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida, tente novamente.");
            }
        }
        return result;
    }

    public static <E extends Enum<E>> E getEnumEntry(Scanner scanner, String prompt, Class<E> enumClass) {
        return getEntry(scanner, prompt, input -> Enum.valueOf(enumClass, input.toUpperCase()), enumValue -> true);
    }

    public static Double getDoubleEntry(Scanner scanner, String prompt, Function<Double, Boolean> validator) {
        return getEntry(scanner, prompt, Double::parseDouble, validator);
    }

    public static Integer getIntegerEntry(Scanner scanner, String prompt, Function<Integer, Boolean> validator) {
        return getEntry(scanner, prompt, Integer::parseInt, validator);
    }

    public static String getStringEntry(Scanner scanner, String prompt) {
        return getEntry(scanner, prompt, input -> input, input -> !input.isEmpty());
    }
}
