package reflex;

public class JsonConverter {
    public static void main(String[] args) {
        Employee employee = new Employee("Serezha", "Dergachev", 25);
        String convertedJson = convert(employee);
    }

    public static String convert(Object obj) {
//        return "{firstName:\"Serezha\",age:25}";
        return "{name:\"Serezha\",age:25}";
    }

    public static String convert(Object[] obj) {
        return "[{name:\"Serezha\",age:25},{name:\"Serezha\",age:25}]";
    }

    public static String convert(Object obj, boolean pretty) {
        return "{name:\"Serezha\",age:25}";
    }
}
