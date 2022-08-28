import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        String fileCSV = "data/data.csv";
        String fileJson = "data/Emploee.json";

        List<Employee> listCSV = parseCSV(columnMapping, fileCSV);

         String json = listToJson(listCSV);

        createJasonFile(json, fileJson);

        List<Employee> listJSON = jsonToList(json);
        listJSON.forEach(System.out::println);

    }

    private static List<Employee> jsonToList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Employee>>()
        {}.getType();

        return gson.fromJson(json,listType);
    }

    private static void createJasonFile(String json, String fileDir) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileDir))
        ) {
            writer.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String listToJson(List<Employee> listEmploee) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(listEmploee);
        return json;
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(fileName))
        ) {

            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
