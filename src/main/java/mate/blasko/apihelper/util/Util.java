package mate.blasko.apihelper.util;

import mate.blasko.apihelper.util.apidata.ApiDataFormatter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Util {



    public static ArrayList<ArrayList<String>> getCSVSplitDataList(String filePath) throws IOException {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = br.readLine()) != null) {
            if (!line.equals("") && line.charAt(0) != '*'){
                result.add(new ArrayList<>(Arrays.asList(line.split(ApiDataFormatter.CSV_DELIMITER))));
            }
        }
        br.close();
        return result;
    }

    public static ArrayList<String> getCSVDataList(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = br.readLine()) != null) {
            if ( ! String.valueOf(result).equals("") ){
                result.append("\n").append(line);
            } else {
                result.append(line);
            }
        }
        br.close();
        return new ArrayList<>(List.of(String.valueOf(result).split("\n")));
    }

    public static void writeToCSVFile(String filePath, String data, boolean append){
        try {
            FileWriter writer = new FileWriter(filePath, append);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write("\n");
            bwr.write(String.valueOf(data));
            bwr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCsvFileContent(String path) throws IOException {
        new FileWriter(path, false).close();
    }

    public static boolean doesFilePathExist(String path){
        try {
            new BufferedReader(new FileReader(path));
            return true;
        }catch (FileNotFoundException e){
            return false;
        }
    }

    public static boolean anyMatch(String[] array, String str){
        return Arrays.stream(array).anyMatch(str::contains);
    }
}