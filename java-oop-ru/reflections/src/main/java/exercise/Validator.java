package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> failedFields = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            try {
                field.setAccessible(true);
                if (annotation != null && field.get(address) == null) {
                    failedFields.add(field.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return failedFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> failedFields = new HashMap<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
            MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
            try {
                field.setAccessible(true);
                if (notNullAnnotation != null && field.get(address) == null) {
                    String error = "can not be null";
                    pushError(failedFields, field, error);
                }

                if (minLengthAnnotation != null && (field.get(address) == null || ((String) field.get(address)).length() < minLengthAnnotation.minLength())) {
                    String error = "length less than " + minLengthAnnotation.minLength();
                    pushError(failedFields, field, error);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return failedFields;
    }

    private static void pushError(Map<String, List<String>> failedFields, Field field, String error) {
        if (failedFields.containsKey(field.getName()) && failedFields.get(field.getName()) != null) {
            failedFields.get(field.getName()).add(error);
        } else {
            ArrayList<String> errors = new ArrayList<>();
            errors.add(error);
            failedFields.put(field.getName(), errors);
        }
    }
}
// END
