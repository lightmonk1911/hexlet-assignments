package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path path, Car car) throws IOException {
        String serialized = car.serialize();
        byte[] strToBytes = serialized.getBytes();

        Files.write(path, strToBytes);
    }

    public static Car extract(Path path) throws IOException {
        String serialized = Files.readString(path);
        return Car.unserialize(serialized);
    }
}
// END
