package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public NegativeRadiusException(String message) {
        super(message);
    }
}
// END
