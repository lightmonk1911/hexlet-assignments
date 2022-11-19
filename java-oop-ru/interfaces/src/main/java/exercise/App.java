package exercise;

import java.util.List;

// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> apartments, int n) {
        int normalizedLimit = Math.min(Math.max(n, 0), apartments.size());
        List<Home> subList = apartments.subList(0, normalizedLimit);
        subList.sort(Home::compareTo);
        return subList.stream().map(Object::toString).toList();
    }
}
// END
