package test;

import dssc.calculator.StringCalculator;
import org.junit.jupiter.api.Test;

import java.awt.event.TextEvent;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class AddNumbersTest {
    @Test
    void emptyString() {
        assertEquals(0, StringCalculator.add(""));
        assertThat(StringCalculator.add(""), is(0));
    }

    @Test
    void oneNumber() {
        assertThat(StringCalculator.add("1"), is(1));
    }

    @Test
    void twoNumbers() {
        assertThat(StringCalculator.add("1,2"), is(3));
    }

    @Test
    void manyNumbers() {
        assertThat(StringCalculator.add("1,2,3,4"), is(10));
    }

    @Test
    void newlineNumbers() {
        assertThat(StringCalculator.add("1\n,2"), is(3));
        assertThat(StringCalculator.add("1,\n2"), is(3));
    }

    @Test
    void delimetersNumbers() {
        assertThat(StringCalculator.add("//;\n1;2"), is(3));
        assertThat(StringCalculator.add("//.\n1.2"), is(3));
    }

    @Test
    void negativeNumbers() {
        assertThat(StringCalculator.add("1,-2,1,-5"), is(new RuntimeException()));
    }

    @Test
    void biggerThan1kNumbers() {
        assertThat(StringCalculator.add("1001,4"), is(4));
    }

    @Test
    void longDelNumbers() {
        assertThat(StringCalculator.add("//[:::]\n1:::2:::3"), is(6));
    }

    @Test
    void multipleDelNumbers() {
        assertThat(StringCalculator.add("//[:][°]\n1:2°3"), is(6));
        assertThat(StringCalculator.add("//[:][°][;]\n1:2°3;4"), is(10));
    }

    @Test
    void multipleLongDelNumbers() {
        assertThat(StringCalculator.add("//[:::][°°°]\n1:::2°°°3"), is(6));
    }

}
