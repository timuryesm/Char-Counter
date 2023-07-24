package ua.foxminded.javaspring.CharManagerTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ua.foxminded.javaspring.CharCalculator.CharCalculator;
import ua.foxminded.javaspring.CharManager.CharManager;
import ua.foxminded.javaspring.charCounter.CharCounter;

import static org.mockito.Mockito.*;

public class CharManagerTest {
    @Test
    public void testCacheHitsAndMisses() {
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
}