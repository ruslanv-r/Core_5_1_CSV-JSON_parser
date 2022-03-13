import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


public class Main {

    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        System.out.println(list);

        String json =listToJson(list);
        writeString(json,"data.json");

        //Задача 2
        List<Employee> listXml = XmlToJson.parseXML("data.xml");
        String json2 =listToJson(listXml);
        writeString(json2,"data2.json");

        //Задача 3
        String fromJson = JsonParser.readString("new_data.json");
        System.out.println(fromJson);
        List<Employee> listFromJson = JsonParser.jsonToList(fromJson);
        for (Employee empl: listFromJson) {
            System.out.println(empl);
        }


    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {

            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping("id", "firstName", "lastName", "country", "age");

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            List<Employee> staff = csv.parse();
            staff.forEach((System.out::println));

            return staff;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String listToJson(List<Employee> list) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();

        return gson.toJson(list, listType);
    }

    private static void writeString(String json, String name2) {
       try (FileWriter writer = new FileWriter(name2,false)){
           writer.write(json);
           writer.flush();

       } catch (IOException ex){
           System.out.println(ex.getMessage());
           //ex.printStackTrace();
       }
    }
}