package numbers.validation;

public class Error {
    private ErrorType type;
    private int ordinal = 0;
    private String value = "";

    public Error(ErrorType type) {
        if (type != null) {
            this.type = type;
        }
    }

    public Error(ErrorType type, String value) {
        if (type != null) {
            this.type = type;
        }
        if (value != null) {
            this.value = value;
        }
    }

    public Error(ErrorType type, String value, int ordinal) {
        if (type != null) {
            this.type = type;
        }
        if (value != null) {
            this.value = value;
        }
        this.ordinal = ordinal;
    }

    public ErrorType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public int getOrdinal() {
        return this.ordinal;
    }
}
