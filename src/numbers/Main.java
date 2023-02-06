package numbers;

import numbers.validation.Error;
import numbers.validation.Validator;
import numbers.util.Array;

import java.util.Scanner;

class Main {

    private final static String EXIT = "0";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(Informer.welcome());
        System.out.println(Informer.instruction());

        do {
            System.out.println(Informer.ask());
            String[] params = parse(scanner.nextLine());

            if (params.length == 0) {
                continue;
            }

            if (EXIT.equals(params[0])) {
                System.out.println(Informer.bye());
                break;
            }

            Error[] errors = Validator.validate(params);

            if (errors.length > 0) {
                System.out.println(Informer.tryAgain(errors));
                continue;
            }

            try {
                System.out.println(Informer.result(Handler.handle(params)));
            } catch (IllegalArgumentException e) {
                System.out.println(Informer.error());
            }
        } while (true);
    }

    private static String[] parse(String input) {
        if (input == null) {
            return new String[0];
        }

        String[] params = input.toUpperCase().split(" ");

        return Array.filter(params, new String[]{"", " "});
    }
}
