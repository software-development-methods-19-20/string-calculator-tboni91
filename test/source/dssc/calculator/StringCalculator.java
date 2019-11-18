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

            int sum = 0;
            String defaultDel = "[\n\r/,]+";
            String usedDel = defaultDel;
            int size = numbers.length();

            if (numbers.contains("//")) {
                int[] longDel = checkLongDel(numbers);
                if ((longDel[0] != 0) && (longDel[1] != 0)) {
                    if ((longDel[2] != 0) && (longDel[3] != 0)) {
                        usedDel = "[" + numbers.substring(longDel[0]+1,longDel[1]) + numbers.substring(longDel[2]+1,longDel[3]) +"/,]+";
                        numbers = numbers.substring(longDel[3]+1,size).replaceAll("\r\n|\r|\n","");
                        //System.out.println("usedDel: "+usedDel);
                        //System.out.println("number: "+numbers);
                    } else {
                        usedDel = "[" + numbers.substring(longDel[0]+1,longDel[1]) +"/,]+";
                        numbers = numbers.substring(longDel[1]+1,size).replaceAll("\r\n|\r|\n","");
                    }
                }
                else {
                    usedDel = "[" + numbers.substring(2,3) +"/,]+";
                    numbers = numbers.substring(3,size).replaceAll("\r\n|\r|\n","");
                }
            }

            String[] tokens = numbers.split(usedDel);
            int[] intNumbers = new int[tokens.length];
            for (int i=0; i<tokens.length; i++)
                intNumbers[i] = Integer.parseInt(tokens[i]);

            IntStream st = Arrays.stream(intNumbers);

            if (st.anyMatch(x -> x<0)) {
                IntStream negative = Arrays.stream(intNumbers).filter(x -> x<0);
                int[] neg = negative.toArray();
                String negativeNumber = "";
                for (int i:neg) {
                    negativeNumber += i + " ";
                }
                //System.out.println(negativeNumber);
                throw new RuntimeException("Negative not allowed: "+negativeNumber);
            } else {
                for (int num:intNumbers) {
                    if (num <= 1000) {
                        sum += num;
                    }
                }
                return sum;
            }
            //List<Integer> negative = (List<Integer>) st.filter(x->x<0);
            //negative.forEach(System.out::println);
            //Stream<Integer> negative = (Stream<Integer>) streamNumber;
            //IntStream allNegative = streamNumber;
            //List<Integer> allNegative = (List<Integer>) streamNumber.filter(x -> x<0).collect(Collectors.toList());
            //IntStream token = Arrays.stream(numbers.replaceAll("\r\n|\r|\n","").split(",")).mapToInt(x-> Integer.parseInt(x));
            //IntStream negative = Arrays.stream(numbers.replaceAll("\r\n|\r|\n","").split(",")).mapToInt(x-> Integer.parseInt(x)).filter(x->x<0);
            //negative.forEach(System.out::println);
            //return token.reduce(0,(x,y)->x+y);*/
        }
    }

    private static int[] checkLongDel(String numbers) {
        int[] index = new int[4];
        for (int i=0, j=0; i<numbers.length(); i++) {
            if ((numbers.charAt(i)) == '[') {
                index[j++] = i;
            }
            if ((numbers.charAt(i)) == ']') {
                index[j++] = i;
            }
        }
        return index;
    }

}
