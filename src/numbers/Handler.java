package numbers;

import numbers.number.Number;
import numbers.number.NumberType;
import numbers.util.Array;
import numbers.util.Common;

class Handler {
    public static Number[] handle(
        String[] params
    ) throws IllegalArgumentException {
        if (params == null || params.length == 0) {
            return new Number[0];
        }

        if (params.length > 2) {
            String[] types = Array.slice(params, 2);
            return sequence(params[0], params[1], types);
        } else if (params.length > 1) {
            return sequence(params[0], params[1]);
        }

        return new Number[]{new Number(Long.parseLong(params[0]))};
    }

    private static Number[] sequence(
        String number1, String number2
    ) throws IllegalArgumentException {
        long num1 = Long.parseLong(number1);
        long num2 = Long.parseLong(number2);
        Number[] numbers = new Number[0];

        for (int i = 0; i < num2; i++) {
            numbers = Array.add(numbers, new Number(num1));
            num1++;
        }

        return numbers;
    }

    private static Number[] sequence(
        String number1, String number2, String[] types
    ) throws IllegalArgumentException {
        long num1 = Long.parseLong(number1);
        long num2 = Long.parseLong(number2);
        Number[] numbers = new Number[0];

        int i = 0;
        while (i < num2) {
            Number number = new Number(num1);
            if (isMatched(number, types)) {
                numbers = Array.add(numbers, number);
                i++;
            }
            num1++;
        }

        return numbers;
    }

    private static boolean isMatched(
        Number number, String[] types
    ) throws IllegalArgumentException {
        boolean wrongParams = number == null ||
                              types == null ||
                              types.length == 0;
        if (wrongParams) {
            return false;
        }

        for (String type : types) {
            boolean isInverted = false;
            if (Common.isInvertedType(type)) {
                type = Common.normalizeType(type);
                isInverted = true;
            }

            boolean numberIs = number.is(NumberType.valueOf(type));
            if (isInverted && numberIs || !isInverted && !numberIs) {
                return false;
            }
        }

        return true;
    }
}
