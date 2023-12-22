package demo.util;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public abstract class Util {

    public static String normalizeWildcard(String string) {
        if (string == null || string.isBlank()) {
            return null;
        }
        return Arrays.stream(string.toLowerCase(Locale.ROOT).split("\\s"))
                .filter(s -> !s.isBlank())
                .collect(Collectors.joining("%", "%", "%"));
    }
}
