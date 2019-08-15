import java.io.*;
import java.util.ArrayList;

public class FileHelper {

    public static ArrayList<String> readArrayFromFile(String fileName) {
        ArrayList<String> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String s;
            while ((s = br.readLine()) != null) { // пока в файле есть строки
                String[] arr = s.split(" "); // читаем массив данных из строки
                for (String string : arr)
                    result.add(string); // и пишем в результирующий список
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeArrayToFile(String fileName, ArrayList<String> array) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String s : array) {
                bw.write(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
