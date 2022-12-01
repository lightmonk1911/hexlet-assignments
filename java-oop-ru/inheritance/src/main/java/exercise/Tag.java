package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String name;
    protected Map<String, String> attributes;

    protected Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    protected String attributesToString() {
        if (attributes.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" ");

        for (var entry : attributes.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append("\"");
            sb.append(entry.getValue());
            sb.append("\"");
            sb.append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    protected String openingTagToString() {
        return "<" + name + attributesToString() + ">";
    }

    @Override
    public abstract String toString();
}
// END
