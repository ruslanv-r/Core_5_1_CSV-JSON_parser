import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    public static List<Employee> jsonToList(String jsonName) {
        List<Employee> employeeList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try {
            Object obj = parser.parse(jsonName);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object o : jsonArray) {
                employeeList.add(gson.fromJson(((JSONObject) o).toJSONString(), Employee.class));

            }

        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return employeeList;

    }


}
