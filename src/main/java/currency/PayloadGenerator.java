package currency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadGenerator {
    public static String generatorPayload(String fileName) throws IOException {

//        read the path of the project
        String filePath = System.getProperty("user.dir" + "/src/main/java/resources/schema" + fileName);

        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
