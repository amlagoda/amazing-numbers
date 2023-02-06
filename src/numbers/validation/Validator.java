package numbers.validation;

import numbers.number.NumberType;
import numbers.util.Array;
import numbers.util.Common;

public class Validator {

    public static Error[] validate(String[] params) {
        if (params == null) {
            return new Error[]{new Error(ErrorType.NULLABLE_PARAMS)};
        }

        if (params.length == 0) {
            return new Error[]{new Error(ErrorType.EMPTY_PARAMS)};
        }

        Error[] errors = new Error[0];
        NumberType[] types = NumberType.values();

        for (int i = 0; i < params.length; i++) {
            boolean invalidNumber = (i <= 1) && !isValidNumber(params[i]);
            boolean invalidType = i > 1 && !isValidType(params[i], types);

            if (invalidNumber) {
                Error error = new Error(
                    ErrorType.INVALID_NUMBER, params[i], i + 1
                );
                errors = Array.add(errors, error);
            }
            if (invalidType) {
                Error error = new Error(
                    ErrorType.INVALID_TYPE, params[i], i + 1
                );
                errors = Array.add(errors, error);
            }
        }

        String[] paramTypes = Array.slice(params, 2);

        return Array.merge(errors, conflicts(paramTypes));
    }

    private static Error[] conflicts(String[] types) {
        if (types == null || types.length == 0) {
            return new Error[0];
        }

        Error[] errors = new Error[0];

        for (String type : types) {
            if (Common.isInvertedType(type)) {
                continue;
            }

            String inverted = Common.invertType(type);
            if (Array.has(types, inverted)) {
                String value = String.format("%s %s", type, inverted);
                errors = Array.add(
                    errors, new Error(ErrorType.CONFLICT_TYPES, value)
                );
            }
        }

        String[][] conflicts = new String[][] {
            {NumberType.EVEN.name(), NumberType.ODD.name()},
            {NumberType.DUCK.name(), NumberType.SPY.name()},
            {NumberType.SUNNY.name(), NumberType.SQUARE.name()},
            {NumberType.HAPPY.name(), NumberType.SAD.name()},
            {
                String.format("-%s", NumberType.EVEN.name()),
                String.format("-%s", NumberType.ODD.name()),
            },
            {
                String.format("-%s", NumberType.HAPPY.name()),
                String.format("-%s", NumberType.SAD.name())
            },
        };

        for (String[] conflict : conflicts) {
            boolean hasConflict = Array.has(types, conflict[0]) &&
                                  Array.has(types, conflict[1]);
            if (hasConflict) {
                String value = String.format("%s %s", conflict[0], conflict[1]);
                Error error = new Error(ErrorType.CONFLICT_TYPES, value);
                errors = Array.add(errors, error);
            }
        }

        return errors;
    }

    private static boolean isValidType(String type, NumberType[] numberTypes) {
        if (type == null || numberTypes == null) {
            return false;
        }

        boolean isValid = false;

        for (NumberType numberType : numberTypes) {
            if (numberType.name().equals(Common.normalizeType(type))) {
                return true;
            }
        }

        return isValid;
    }

    private static boolean isValidNumber(String number) {
        if (number == null) {
            return false;
        }

        try {
            long num = Long.parseLong(number);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
