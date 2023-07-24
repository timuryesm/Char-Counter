package ua.foxminded.javaspring.CharManager;

import ua.foxminded.javaspring.CharCalculator.CharCalculator;
import ua.foxminded.javaspring.charCounter.CharCounter;

public class CharManager {
    private CharCalculator calculator;
    private CharCounter cache;

    public CharManager() {
        calculator = new CharCalculator();
        cache = new CharCounter();
    }

    public String countUniqueChars(String inputString) {
        String cachedResult = cache.get(inputString);
        if (cachedResult != null) {
            return cachedResult;
        }

        String result = calculator.countUniqueChars(inputString);
        cache.put(inputString, result);
        return result;
    }

    public void setCalculator(CharCalculator calculator) {
        this.calculator = calculator;
    }

    public void setCache(CharCounter cache) {
        this.cache = cache;
    }
}
