package ua.foxminded.javaspring.charCounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter {
    private final int CACHE_SIZE = 100;
    private LinkedHashMap<String, String> cache;

    public CharCounter() {
        cache = new LinkedHashMap<String, String>(CACHE_SIZE, 0.75f, true) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > CACHE_SIZE;
            }
        };
    }

    public String get(String inputString) {
        return cache.get(inputString);
    }

    public void put(String inputString, String result) {
        cache.put(inputString, result);
    }
}
