package db.helpers;

public class PostgresqlTableAttribute {
    public static String toPostgresAttribute(String attribute) {
        StringBuilder stringBuilder = new StringBuilder();
        int begin = 0;
        int end = 0;
        for (int i = 0; i < attribute.length(); ++i) {
            if (Character.isUpperCase(attribute.charAt(i))) {
                end = i;
                stringBuilder.append(attribute.substring(begin, end) + "_");
                begin = end;
            }
        }
        stringBuilder.append(attribute.substring(begin));
        return stringBuilder.toString().toLowerCase();
    }

    public static String toPostgresFKAttribute(String attribute) {
        return attribute.toLowerCase() + "_id";
    }
}
