package ua.foxminded.javaspring.CharManagerTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ua.foxminded.javaspring.CharCalculator.CharCalculator;
import ua.foxminded.javaspring.CharManager.CharManager;
import ua.foxminded.javaspring.CharManager.OutputHandler;
import ua.foxminded.javaspring.charCounter.CharCounter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CharManagerTest {
    @Test
    public void process_testCacheHitsAndMisses() {
        CharCalculator calculatorMock = Mockito.mock(CharCalculator.class);
        CharCounter cacheMock = Mockito.mock(CharCounter.class);
        CharManager manager = new CharManager();
        manager.setCalculator(calculatorMock);
        manager.setCache(cacheMock);

        String inputString = "Hello, World!";
        String expected = "'H' - 1\n' ' - 1\n'W' - 1\n'o' - 2\n'r' - 1\n'l' - 3\n',' - 1\n'd' - 1\n'!' - 1\n'e' - 1\n";

        // Cache hit
        when(cacheMock.get(inputString)).thenReturn(expected);
        manager.countUniqueChars(inputString);
        verify(calculatorMock, never()).countUniqueChars(any());

        // Cache miss
        when(cacheMock.get(inputString)).thenReturn(null);
        when(calculatorMock.countUniqueChars(inputString)).thenReturn(expected);
        manager.countUniqueChars(inputString);
        verify(calculatorMock, times(1)).countUniqueChars(inputString);
    }

    @Test
    public void testCacheHit() {
        CharCalculator calculatorMock = Mockito.mock(CharCalculator.class);
        CharCounter cacheMock = Mockito.mock(CharCounter.class);
        CharManager manager = new CharManager();
        manager.setCalculator(calculatorMock);
        manager.setCache(cacheMock);

        String inputString = "Hello, World!";
        String expected = "'H' - 1\n' ' - 1\n'W' - 1\n'o' - 2\n'r' - 1\n'l' - 3\n',' - 1\n'd' - 1\n'!' - 1\n'e' - 1\n";

        // Cache hit
        when(cacheMock.get(inputString)).thenReturn(expected);
        String result = manager.countUniqueChars(inputString);

        verify(calculatorMock, never()).countUniqueChars(any());
        assertEquals(expected, result);
    }

    @Test
    public void testCacheMiss() {
        CharCalculator calculatorMock = Mockito.mock(CharCalculator.class);
        CharCounter cacheMock = Mockito.mock(CharCounter.class);
        CharManager manager = new CharManager();
        manager.setCalculator(calculatorMock);
        manager.setCache(cacheMock);

        String inputString = "Hello, World!";
        String expected = "'H' - 1\n' ' - 1\n'W' - 1\n'o' - 2\n'r' - 1\n'l' - 3\n',' - 1\n'd' - 1\n'!' - 1\n'e' - 1\n";

        // Cache miss
        when(cacheMock.get(inputString)).thenReturn(null);
        when(calculatorMock.countUniqueChars(inputString)).thenReturn(expected);
        String result = manager.countUniqueChars(inputString);

        verify(calculatorMock, times(1)).countUniqueChars(inputString);
        assertEquals(expected, result);
    }

    @Test
    public void testCacheCorrectness() {
        CharCalculator calculatorMock = Mockito.mock(CharCalculator.class);
        CharCounter cacheMock = Mockito.mock(CharCounter.class);
        CharManager manager = new CharManager();
        manager.setCalculator(calculatorMock);
        manager.setCache(cacheMock);

        String inputString = "Hello, World!";
        String expected = "'H' - 1\n' ' - 1\n'W' - 1\n'o' - 2\n'r' - 1\n'l' - 3\n',' - 1\n'd' - 1\n'!' - 1\n'e' - 1\n";

        // Cache miss
        when(cacheMock.get(inputString)).thenReturn(null);
        when(calculatorMock.countUniqueChars(inputString)).thenReturn(expected);
        manager.countUniqueChars(inputString);

        verify(calculatorMock, times(1)).countUniqueChars(inputString);

        // Cache hit
        when(cacheMock.get(inputString)).thenReturn(expected);
        String result = manager.countUniqueChars(inputString);

        verify(calculatorMock, times(1)).countUniqueChars(inputString);
        assertEquals(expected, result);
    }

    @Test
    public void testCountCorrectness() {
        CharCalculator calculatorMock = Mockito.mock(CharCalculator.class);
        CharCounter cacheMock = Mockito.mock(CharCounter.class);
        CharManager manager = new CharManager();
        manager.setCalculator(calculatorMock);
        manager.setCache(cacheMock);

        String inputString = "Lorem ipsum dolor sit amet";
        String expected = "'L' - 1\n'o' - 3\n'r' - 2\n'e' - 2\n'm' - 2\n' ' - 4\n'i' - 2\n'p' - 1\n's' - 1\n'u' - 1\n'd' - 1\n'l' - 1\n't' - 1\n'a' - 1\n";

        when(cacheMock.get(inputString)).thenReturn(null);
        when(calculatorMock.countUniqueChars(inputString)).thenReturn(expected);

        String result = manager.countUniqueChars(inputString);

        verify(cacheMock, times(1)).get(inputString);
        verify(cacheMock, times(1)).put(inputString, expected);
        verify(calculatorMock, times(1)).countUniqueChars(inputString);
        assertEquals(expected, result);
    }

    @Test
    public void testOutput() {
        OutputHandler outputHandler = new OutputHandler();
        String inputString1 = "Hello, World!";
        String inputString2 = "Lorem ipsum dolor sit amet";
        String expectedOutput1 = "Hello, World! 'H' - 1\n' ' - 1\n'W' - 1\n'o' - 2\n'r' - 1\n'l' - 3\n',' - 1\n'd' - 1\n'!' - 1\n'e' - 1\n";
        String expectedOutput2 = "Lorem ipsum dolor sit amet 'L' - 1\n'o' - 3\n'r' - 2\n'e' - 2\n'm' - 2\n' ' - 4\n'i' - 2\n'p' - 1\n's' - 1\n'u' - 1\n'd' - 1\n'l' - 1\n't' - 1\n'a' - 1\n";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        outputHandler.printResult(inputString1, expectedOutput1);
        outputHandler.printResult(inputString2, expectedOutput2);

        String output = outputStream.toString();
        assertTrue(output.contains(expectedOutput1));
        assertTrue(output.contains(expectedOutput2));
    }
}
