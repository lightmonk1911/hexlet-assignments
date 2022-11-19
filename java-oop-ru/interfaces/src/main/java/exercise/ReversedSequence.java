package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final StringBuilder stringBuilder;

    public ReversedSequence(String str) {
        stringBuilder = new StringBuilder(str.length());

        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(str.length() - 1 - i));
        }
    }

    @Override
    public int length() {
        return stringBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return stringBuilder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return stringBuilder.subSequence(start, end);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
// END
