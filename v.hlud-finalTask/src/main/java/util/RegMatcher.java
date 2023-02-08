package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegMatcher {
    public static final String PATTERN = "[0-9]+";

    public static String findIdMatches(String originalString) {
        String tmp;
        int index = 0;
        int index2 = 0;
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(originalString);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(0); //prints /{item}/
        }
        return result;
    }
}

