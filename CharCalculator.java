package ua.foxminded.javaspring.CharCalculator;

import java.util.HashMap;
import java.util.Map;

public class CharCalculator {
    public String countUniqueChars(String inputString) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : inputString.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (char c : charCountMap.keySet()) {
            int count = charCountMap.get(c);
            resultBuilder.append("'").append(c).append("' - ").append(count).append("\n");
        }

        return resultBuilder.toString();
    }
}
