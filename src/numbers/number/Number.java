package numbers.number;

import numbers.util.Array;

public class Number {

    private static final byte UNKNOWN = -1;
    private static final byte FALSE = 0;
    private static final byte TRUE = 1;

    private final long number;

    private byte isBuzz = UNKNOWN;
    private byte isDuck = UNKNOWN;
    private byte isPalindromic = UNKNOWN;
    private byte isGapful = UNKNOWN;
    private byte isEven = UNKNOWN;
    private byte isOdd = UNKNOWN;
    private byte isSpy = UNKNOWN;
    private byte isSquare = UNKNOWN;
    private byte isSunny = UNKNOWN;
    private byte isJumping = UNKNOWN;
    private byte isHappy = UNKNOWN;
    private byte isSad = UNKNOWN;

    public Number(long number) {
        this.number = number;
    }

    public long getNumber() {
        return this.number;
    }

    public boolean isBuzz() {
        if (this.isBuzz != UNKNOWN) {
            return this.isBuzz == TRUE;
        }

        long x = this.number % 7;
        long y = this.number - 7;
        boolean isBuzz = x == 0 || y % 10 == 0;

        this.isBuzz = isBuzz ? TRUE : FALSE;

        return isBuzz;
    }

    public boolean isDuck() {
        if (this.isDuck != UNKNOWN) {
            return this.isDuck == TRUE;
        }

        String num = String.valueOf(this.number);
        boolean isDuck = false;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                isDuck = true;
                break;
            }
        }

        this.isDuck = isDuck ? TRUE : FALSE;

        return isDuck;
    }

    public boolean isPalindromic() {
        if (this.isPalindromic != UNKNOWN) {
            return this.isPalindromic == TRUE;
        }

        String normal = String.valueOf(this.number);
        String reverse = new StringBuilder(String.valueOf(this.number))
            .reverse()
            .toString();

        boolean isPalindromic = normal.equals(reverse);
        this.isPalindromic = isPalindromic ? TRUE : FALSE;

        return isPalindromic;
    }

    public boolean isGapful () {
        if (this.isGapful != UNKNOWN) {
            return this.isGapful == TRUE;
        }

        String num = String.valueOf(this.number);

        if (num.length() < 3) {
            this.isGapful = FALSE;
            return false;
        }

        char x = num.charAt(0);
        char y = num.charAt(num.length() - 1);
        int divider = Integer.parseInt(String.format("%c%c", x, y));
        boolean isGapful = number % divider == 0;

        this.isGapful = isGapful ? TRUE : FALSE;

        return isGapful;
    }

    public boolean isEven() {
        if (this.isEven != UNKNOWN) {
            return this.isEven == TRUE;
        } else if (this.isOdd != UNKNOWN) {
            return this.isOdd == FALSE;
        }

        boolean isEven = this.number % 2 == 0;
        this.isEven = isEven ? TRUE : FALSE;
        this.isOdd = isEven ? FALSE : TRUE;

        return isEven;
    }

    public boolean isOdd() {
        if (this.isOdd != UNKNOWN) {
            return this.isOdd == TRUE;
        } else if (this.isEven != UNKNOWN) {
            return this.isEven == FALSE;
        }

        boolean isOdd = this.number % 2 != 0;
        this.isOdd = isOdd ? TRUE : FALSE;
        this.isEven = isOdd ? FALSE : TRUE;

        return isOdd;
    }

    public boolean isSpy() {
        if (this.isSpy != UNKNOWN) {
            return this.isSpy == TRUE;
        }

        String[] numbers = String.valueOf(this.number).split("");
        int sumNumbers = 0;
        int productNumbers = 1;

        for (String number : numbers) {
            int num = Integer.parseInt(number);
            sumNumbers += num;
            productNumbers *= num;
        }

        boolean isSpy = sumNumbers == productNumbers;
        this.isSpy = isSpy ? TRUE : FALSE;

        return isSpy;
    }

    public boolean isSquare() {
        if (this.isSquare != UNKNOWN) {
            return this.isSquare == TRUE;
        }

        double sqrt =  Math.sqrt(this.number);
        boolean isSquare = (long) sqrt * sqrt == this.number;
        this.isSquare = isSquare ? TRUE : FALSE;

        return isSquare;
    }

    public boolean isSunny() {
        if (this.isSunny != UNKNOWN) {
            return this.isSunny == TRUE;
        }

        double sqrt =  Math.sqrt(this.number + 1);
        boolean isSunny = (long) sqrt * sqrt == this.number + 1;
        this.isSunny = isSunny ? TRUE : FALSE;

        return isSunny;
    }

    public boolean isJumping() {
        if (this.isJumping != UNKNOWN) {
            return this.isJumping == TRUE;
        }

        if (this.number < 10) {
            this.isJumping = TRUE;
            return true;
        }

        String[] numbers = String.valueOf(this.number).split("");

        boolean isJumping = true;
        for (int i = 0; i < numbers.length - 1; i++) {
            int num1 = Integer.parseInt(numbers[i]);
            int num2 = Integer.parseInt(numbers[i + 1]);

            if (num1 - num2 != 1 && num2 - num1 != 1) {
                isJumping = false;
                break;
            }
        }
        this.isJumping = isJumping ? TRUE : FALSE;

        return isJumping;
    }

    public boolean isHappy() {
        if (this.isHappy != UNKNOWN) {
            return this.isHappy == TRUE;
        }

        boolean isHappy = happyOrSad(this.number) == 1;
        this.isHappy = isHappy ? TRUE : FALSE;
        this.isSad = isHappy ? FALSE : TRUE;

        return isHappy;
    }

    public boolean isSad() {
        if (this.isSad != UNKNOWN) {
            return this.isSad == TRUE;
        }

        boolean isSad = happyOrSad(this.number) != 1;
        this.isSad = isSad ? TRUE : FALSE;
        this.isHappy = isSad ? FALSE : TRUE;

        return isSad;
    }

    private long happyOrSad(long initial) {
        long[] sequence = new long[0];

        do {
            String[] numbers = String.valueOf(initial).split("");
            long sumOfSquares = 0;
            for (String number : numbers) {
                long num = Long.parseLong(number);
                sumOfSquares += num * num;
            }

            initial = sumOfSquares;
            if (initial == 1 || Array.has(sequence, initial)) {
                break;
            }
            sequence = Array.add(sequence, initial);
        } while (true);

        return initial;
    }

    public boolean is(NumberType type) {
        switch (type) {
            case JUMPING:
                return this.isJumping();
            case PALINDROMIC:
                return this.isPalindromic();
            case SQUARE:
                return this.isSquare();
            case GAPFUL:
                return this.isGapful();
            case SUNNY:
                return this.isSunny();
            case DUCK:
                return this.isDuck();
            case BUZZ:
                return this.isBuzz();
            case SPY:
                return this.isSpy();
            case EVEN:
                return this.isEven();
            case ODD:
                return this.isOdd();
            case HAPPY:
                return this.isHappy();
            case SAD:
                return this.isSad();
            default:
                return false;
        }
    }
}
