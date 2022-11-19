package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String filePath;

    public FileKV(String filePath, Map<String, String> initial) {
        this.filePath = filePath;
        String serialized = Utils.serialize(initial);
        Utils.writeFile(filePath, serialized);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = read();
        map.put(key, value);
        save(map);
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = read();
        map.remove(key);
        save(map);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = read();
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = read();
        return new HashMap<>(map);
    }

    private void save(Map<String, String> map) {
        Utils.writeFile(filePath, Utils.serialize(map));
    }

    private Map<String, String> read() {
        return Utils.unserialize(Utils.readFile(filePath));
    }
}
// END
