package numbers.util;

public class Common {
    public static boolean isInvertedType(String type) {
        if (type == null) {
            return false;
        }
        return type.charAt(0) == '-';
    }

    public static String invertType(String type) {
        return String.format("-%s", type);
    }

    public static String normalizeType(String invertedType) {
        if (invertedType == null) {
            return "";
        }
        return invertedType.replace('-', ' ').trim();
    }
}
