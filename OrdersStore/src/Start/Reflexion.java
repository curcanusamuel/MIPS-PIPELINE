package Start;

import java.lang.reflect.Field;
import java.util.StringJoiner;

/**
 * @Author: Samuel Adrian
 * The `Reflexion` class provides utility methods for reflection operations.
 */
public class Reflexion {

    /**
     * Retrieves the properties and values of an object.
     *
     * @param object the object to retrieve properties from
     * @return a string representing the properties and values of the object
     */
    public static String retrieveProperties(Object object) {
        String fields = "( ";
        String values = "( ";
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                String verify = field.getName();
                if (!verify.equals("id")) {
                    value = field.get(object);
                    fields += field.getName() + ",";
                    values += "'" + value + "',";
                }

            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fields += "|";
        values += "|";
        fields = fields.replace(",|", ")");
        values = values.replace(",|", ")");
        return fields + " values " + values;
    }

    /**
     * Generates a delete query for an object.
     *
     * @param object the object to generate the delete query for
     * @return a string representing the delete query
     */
    public static String delete(Object object) {
        StringJoiner conditions = new StringJoiner(" and ");
        String tableName = object.getClass().getSimpleName();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                String fieldName = field.getName();
                if (!fieldName.equals("id")) {
                    Object value = field.get(object);
                    conditions.add(fieldName + " = '" + value + "'");
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return "delete from " + tableName + " where " + conditions.toString();
    }
}
