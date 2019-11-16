package dssc.calculator;

import java.util.stream.Stream;

public class StringCalculator {
    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else if (numbers.contains(",") || (numbers.contains("\n")) || (numbers.contains("//"))) {
            if (numbers.contains("//")) {
                String del = numbers.substring(2,3);
                numbers = numbers.substring(3).replaceAll(del,",");
            }
            int sum = 0;

            String[] tokens = numbers.replaceAll("\r\n|\r|\n","").split(",");

            for (String num:tokens) {
                sum += Integer.valueOf(num);
            }
            return sum;
        } else {
            return Integer.valueOf(numbers);
        }
    }

}
