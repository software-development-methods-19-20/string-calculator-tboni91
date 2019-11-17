package dssc.calculator;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {
    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            //IntStream token = Arrays.stream(numbers.replaceAll("\r\n|\r|\n","").split(",")).mapToInt(x-> Integer.parseInt(x));
            //IntStream negative = Arrays.stream(numbers.replaceAll("\r\n|\r|\n","").split(",")).mapToInt(x-> Integer.parseInt(x)).filter(x->x<0);
            //negative.forEach(System.out::println);
            //return token.reduce(0,(x,y)->x+y);*/
            int sum = 0;
            String defaultDel = "[\n\r/,]+";
            String usedDel = defaultDel;
            if (numbers.contains("//")) {
                usedDel = "[" + numbers.substring(2,3) +"/,]+";
                numbers = numbers.substring(3,numbers.length()).replaceAll("\r\n|\r|\n","");
            }

            String[] tokens = numbers.split(usedDel);
            //System.out.println(tokens[0]+tokens[1]);
            //String[] tokens = numbers.replaceAll("\r\n|\r|\n","").split(",");
            for (String num:tokens) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }
    }

}
