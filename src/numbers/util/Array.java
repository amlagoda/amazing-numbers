package numbers.util;

import numbers.validation.Error;
import numbers.number.Number;
import numbers.number.NumberType;

import java.util.Objects;

public class Array {

    public static String[] slice(String[] array, int startIndex) {
        boolean hasWrongParams = array == null ||
                                 array.length == 0 ||
                                 startIndex < 0 ||
                                 startIndex >= array.length;

        if (hasWrongParams) {
            return new String[0];
        }

        int length = array.length - startIndex;
        String[] slice = new String[length];

        for (int i = startIndex; i < array.length; i++) {
            slice[i - startIndex] = array[i];
        }

        return slice;
    }

    public static String[] slice(String[] array, int startIndex, int endIndex) {
        boolean hasWrongParams = array == null ||
                                 array.length == 0 ||
                                 startIndex < 0 ||
                                 startIndex >= array.length ||
                                 startIndex > endIndex ||
                                 endIndex >= array.length;

        if (hasWrongParams) {
            return new String[0];
        }

        int length = array.length - startIndex - (array.length - endIndex - 1);
        String[] slice = new String[length];

        for (int i = startIndex; i <= endIndex; i++) {
            slice[i - startIndex] = array[i];
        }

        return slice;
    }

    public static String[] filter(String[] array, String discard) {
        boolean hasWrongParams = array == null || array.length == 0;

        if (hasWrongParams) {
            return new String[0];
        }

        int length = 0;
        for (String value : array) {
            if (!Objects.equals(value, discard)) {
                length++;
            }
        }

        String[] filtered = new String[length];

        int next = 0;
        for (String value : array) {
            if (!Objects.equals(value, discard)) {
                filtered[next] = value;
                next++;
            }
        }

        return filtered;
    }

    public static String[] filter(String[] array, String[] discards) {
        boolean hasWrongParams = array == null || array.length == 0;

        if (hasWrongParams) {
            return new String[0];
        }

        if (discards == null || discards.length == 0) {
            String[] copy = new String[array.length];

            for (int i = 0; i < array.length; i++) {
                copy[i] = array[i];
            }

            return copy;
        }

        int length = 0;
        for (String value : array) {
            boolean allowed = true;

            for (String discard : discards) {
                if (Objects.equals(value, discard)) {
                    allowed = false;
                    break;
                }
            }

            if (allowed) {
                length++;
            }
        }

        String[] filtered = new String[length];

        int next = 0;
        for (String value : array) {
            boolean allowed = true;

            for (String discard : discards) {
                if (Objects.equals(value, discard)) {
                    allowed = false;
                    break;
                }
            }

            if (allowed) {
                filtered[next] = value;
                next++;
            }
        }

        return filtered;
    }

    public static boolean has(String[] array, String needle) {
        boolean hasWrongParams = array == null || array.length == 0;

        if (hasWrongParams) {
            return false;
        }

        for (String value : array) {
            if (Objects.equals(value, needle)) {
                return true;
            }
        }

        return false;
    }

    public static boolean has(NumberType[] array, NumberType needle) {
        boolean hasWrongParams = array == null || array.length == 0;

        if (hasWrongParams) {
            return false;
        }

        for (NumberType value : array) {
            if (Objects.equals(value, needle)) {
                return true;
            }
        }

        return false;
    }

    public static boolean has(long[] array, long needle) {
        boolean hasWrongParams = array == null || array.length == 0;

        if (hasWrongParams) {
            return false;
        }

        for (long value : array) {
            if (Objects.equals(value, needle)) {
                return true;
            }
        }

        return false;
    }

    public static String[] toUpperCase(String[] array) {
        boolean hasWrongParams = array == null || array.length == 0;

        if (hasWrongParams) {
            return new String[0];
        }

        String[] casted = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            casted[i] = array[i] == null ? array[i] : array[i].toUpperCase();
        }

        return casted;
    }

    public static Error[] add(Error[] array, Error added) {
        if (array == null || array.length == 0) {
            return new Error[]{added};
        }

        Error[] newArray = new Error[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = added;

        return newArray;
    }

    public static Number[] add(Number[] array, Number added) {
        if (array == null || array.length == 0) {
            return new Number[]{added};
        }

        Number[] newArray = new Number[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = added;

        return newArray;
    }

    public static NumberType[] add(NumberType[] array, NumberType added) {
        if (array == null || array.length == 0) {
            return new NumberType[]{added};
        }

        NumberType[] newArray = new NumberType[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = added;

        return newArray;
    }

    public static long[] add(long[] array, long added) {
        if (array == null || array.length == 0) {
            return new long[]{added};
        }

        long[] newArray = new long[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = added;

        return newArray;
    }

    public static Error[] merge(Error[] array1, Error[] array2) {
        boolean isAllNull = array1 == null && array2 == null;
        boolean isAllEmpty = array1 != null &&
                             array1.length == 0 &&
                             array2 != null &&
                             array2.length == 0;
        if (isAllNull || isAllEmpty) {
            return new Error[0];
        }

        boolean isFirstEmpty = array1 == null || array1.length == 0;
        if (isFirstEmpty) {
            return array2;
        }

        boolean isSecondEmpty = array2 == null || array2.length == 0;
        if (isSecondEmpty) {
            return array1;
        }

        int length = array1.length + array2.length;
        Error[] newArray = new Error[length];
        for (int i = 0; i < array1.length; i++) {
            newArray[i] = array1[i];
        }
        for (int i = 0; i < array2.length; i++) {
            newArray[array1.length + i] = array2[i];
        }

        return newArray;
    }
}
