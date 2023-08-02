package ua.foxminded.javaspring.CharCalculator;

import java.util.HashMap;
import java.util.Map;

public class CharCalculator {
    public String countUniqueChars(String inputString) {
        if (inputString == null) {
            return "";
        }

        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char character : inputString.toCharArray()) {
            charCountMap.put(character, charCountMap.getOrDefault(character, 0) + 1);
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (char character : charCountMap.keySet()) {
            int count = charCountMap.get(character);
            resultBuilder.append("'")
                         .append(character)
                         .append("' - ")
                         .append(count)
                         .append("\n");
        }

        return resultBuilder.toString();
    }
}
