package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static List<List<String>> getCSVDataList(String filePath, String delimiter) throws IOException {
        List<List<String>> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = br.readLine()) != null) {
            if (!line.equals("") && line.charAt(0) != '*'){
                result.add(Arrays.asList(line.split(delimiter)));
            }
        }
        br.close();
        return result;
    }

    public static String getCSVDataString(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = br.readLine()) != null) {
            result.append(line);
        }
        br.close();
        return String.valueOf(result);
    }

    public static void writeToCSVFile(String filePath, String data, boolean append){
        try {
            FileWriter writer = new FileWriter(filePath, append);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write(String.valueOf(data));
            bwr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}