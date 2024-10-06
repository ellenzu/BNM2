package currency;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties = LoadProperties();
    private static Properties LoadProperties() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("config.properties")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    //    public static void main(String[] args){
//        System.out.println(properties.get("bnmUrl"));
    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
