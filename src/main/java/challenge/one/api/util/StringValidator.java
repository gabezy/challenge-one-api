package challenge.one.api.util;

public class StringValidator {
    public static boolean isNullOrEmpty (String string) {
        return string == null || string.trim().isEmpty();
    }
}
