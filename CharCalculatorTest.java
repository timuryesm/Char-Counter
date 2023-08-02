package ua.foxminded.javaspring.CharManagerTest;

import org.junit.jupiter.api.Test;

import ua.foxminded.javaspring.CharCalculator.CharCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharCalculatorTest {

    @Test
    public void countUniqueChars_shouldReturnEmptyString_whenInputIsNull() {
        CharCalculator calculator = new CharCalculator();
        String expected = "";

        String result = calculator.countUniqueChars(null);

        assertEquals(expected, result);
    }

    @Test
    public void countUniqueChars_shouldReturnEmptyString_whenInputIsEmpty() {
        CharCalculator calculator = new CharCalculator();
        String inputString = "";
        String expectedOutput = "";

        String output = calculator.countUniqueChars(inputString);

        assertEquals(expectedOutput, output);
    }

    @Test
    public void countUniqueChars_shouldHandleSingleSpaceInput() {
        CharCalculator calculator = new CharCalculator();
        String inputString = " ";
        String expectedOutput = "' ' - 1\n";

        String output = calculator.countUniqueChars(inputString);

        assertEquals(expectedOutput, output);
    }

    @Test
    public void countUniqueChars_shouldHandleMultipleSpacesInput() {
        CharCalculator calculator = new CharCalculator();
        String inputString = "    ";
        String expectedOutput = "' ' - 4\n";

        String output = calculator.countUniqueChars(inputString);

        assertEquals(expectedOutput, output);
    }

    @Test
    public void countUniqueChars_shouldHandleLargeInputString() {
        CharCalculator calculator = new CharCalculator();
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            inputBuilder.append('a');
        }
        String inputString = inputBuilder.toString();
        String expectedOutput = "'a' - 10000\n";

        String output = calculator.countUniqueChars(inputString);

        assertEquals(expectedOutput, output);
    }
}
