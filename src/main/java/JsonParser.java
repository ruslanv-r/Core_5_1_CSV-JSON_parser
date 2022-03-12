import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class JsonParser {

    public static String readString(String jsonName) {

        StringBuilder result = new StringBuilder();

        try (FileReader fileReader = new FileReader(jsonName);
             BufferedReader br = new BufferedReader(fileReader)) {
//чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                result.append(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage(
            ));
        }

        return result.toString();

    }
}
