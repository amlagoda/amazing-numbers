package numbers;

import numbers.number.Number;
import numbers.number.NumberType;
import numbers.validation.Error;
import numbers.validation.ErrorType;
import numbers.util.Array;

import java.util.StringJoiner;

class Informer {

    public static String welcome() {
        return "\nWelcome to Amazing Numbers!";
    }

    public static String ask() {
        return "\nEnter a request:";
    }

    public static String bye() {
        return "Goodbye!";
    }

    public static String instruction() {
        return """
            \nSupported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be printed;
            - two natural numbers and properties to search for;
            - a property preceded by minus must not be present in numbers;
            - separate the parameters with one space;
            - enter 0 to exit.""";
    }

    public static String result(Number[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }

        if (numbers.length > 1) {
            StringJoiner result = new StringJoiner("\n");
            for (Number number : numbers) {
                StringJoiner types = new StringJoiner(", ");

                for (NumberType type : NumberType.values()) {
                    if (number.is(type)) {
                        types.add(type.name().toLowerCase());
                    }
                }

                result.add(
                    String.format("%d is %s", number.getNumber(), types)
                );
            }

            return result.toString();
        }

        Number number = numbers[0];
        StringJoiner result = new StringJoiner("\n");
        result.add(String.format("%nProperties of %d", number.getNumber()));

        for (NumberType type : NumberType.values()) {
            String record = String.format(
                "  %s: %b", type.name().toLowerCase(), number.is(type)
            );
            result.add(record);
        }

        return result.toString();
    }

    public static String tryAgain(Error[] errors) {
        Error[] invalidNumbers = only(errors, ErrorType.INVALID_NUMBER);
        Error[] invalidTypes = only(errors, ErrorType.INVALID_TYPE);
        Error[] conflictTypes = only(errors, ErrorType.CONFLICT_TYPES);

        if (invalidNumbers.length > 0) {
            return invalidNumbers(invalidNumbers);
        } else if (invalidTypes.length > 0) {
            return invalidTypes(invalidTypes);
        } else if (conflictTypes.length > 0) {
            return conflictTypes(conflictTypes);
        }

        return error();
    }

    public static String error() {
        return "Something went wrong, try again";
    }

    private static String conflictTypes(Error[] errors) {
        boolean wrongParams = errors == null ||
                              errors.length == 0 ||
                              errors[0] == null;
        if (wrongParams) {
            return "";
        }

        String[] types = errors[0].getValue().split(" ");

        if (types.length < 2) {
            return "";
        }

        return String.format("""
            The request contains mutually exclusive properties: [%s, %s]
            There are no numbers with these properties.
            """, types[0], types[1]);
    }

    private static String invalidTypes(Error[] errors) {
        if (errors == null || errors.length == 0) {
            return "";
        }

        StringJoiner properties = new StringJoiner(", ");

        for (Error error : errors) {
            properties.add(error.getValue());
        }

        StringJoiner result = new StringJoiner("\n");

        if (errors.length > 1) {
            result.add(
                String.format("The properties [%s] are wrong.", properties)
            );
        } else {
            result.add(
                String.format("The property [%s] is wrong.", properties)
            );
        }

        result.add(String.format("Available properties: [%s]", types()));
        
        return result.toString();
    }

    private static String types() {
        StringJoiner types = new StringJoiner(", ");

        for (NumberType type : NumberType.values()) {
            types.add(type.name());
        }

        return types.toString();
    }

    private static String invalidNumbers(Error[] errors) {
        if (errors == null || errors.length == 0) {
            return "";
        }

        String ordinal = errors[0].getOrdinal() == 1 ? "first" : "second";

        return String.format(
            "The %s parameter should be a natural number or zero.",
            ordinal
        );
    }

    private static Error[] only(Error[] errors, ErrorType type) {
        if (errors == null || errors.length == 0) {
            return new Error[0];
        }

        Error[] only = new Error[0];
        for (Error error : errors) {
            if (error.getType() == type) {
                only = Array.add(only, error);
            }
        }

        return only;
    }
}
