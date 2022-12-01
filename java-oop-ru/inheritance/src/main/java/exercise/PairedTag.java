package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String content;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String content, List<Tag> children) {
        super(name, attributes);
        this.content = content;
        this.children = children;
    }

    @Override
    public String toString() {
        String opening = openingTagToString();
        StringBuilder sb = new StringBuilder();
        for (Tag tag : children) {
            sb.append(tag.toString());
        }
        String childrenString = sb.toString();

        return opening + content + childrenString + "</" + name + ">";
    }
}
// END
