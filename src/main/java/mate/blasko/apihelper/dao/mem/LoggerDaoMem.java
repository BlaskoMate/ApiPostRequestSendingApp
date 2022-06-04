package mate.blasko.apihelper.dao.mem;


import mate.blasko.apihelper.util.apidata.ResponseObj;
import mate.blasko.apihelper.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoggerDaoMem{


    public static final String CSV_DELIMITER = ",";

    private static LoggerDaoMem instance;
    private ArrayList<String> logs = new ArrayList<>();
    public final String LOGGER_FILE_PATH;

    private LoggerDaoMem(){
        LOGGER_FILE_PATH = "src/main/resources/Logger.csv";
    }

    private LoggerDaoMem(String filePath) {
        this.LOGGER_FILE_PATH = filePath;
    }

    public static LoggerDaoMem getInstance(String filePath){
        if (instance == null){
            instance = new LoggerDaoMem(filePath);
        }
        return instance;
    }

    public static LoggerDaoMem getInstance(){
        if (instance == null){
            instance = new LoggerDaoMem();
        }
        return instance;
    }


    public void appending(ResponseObj response) {
        String log = createLog(response);
        Util.writeToCSVFile(LOGGER_FILE_PATH, log, true);
    }

    public String createLog(ResponseObj responseObj) {
        String log = formatLog(responseObj);
        logs.add(log);
        return log;
    }


    public String formatLog(ResponseObj responseObj){
        String del = CSV_DELIMITER;
        int status = responseObj.getStatus();
        String message = responseObj.getMessage();
        String url = responseObj.getUrl();
        String body = responseObj.getBody();

        return String.format("%s%s%s%s%s%s%s", status, del, message, del, url, del, body);
    }

    public ResponseObj revertLog(String log){
        ArrayList<String> logInfo = new ArrayList<>(Arrays.asList(log.split(CSV_DELIMITER)));
        return new ResponseObj(logInfo);
    }

    public void clearLogs() throws IOException {
        Util.deleteCsvFileContent(LOGGER_FILE_PATH);
    }

//    public ArrayList<ArrayList<String>> getLogs() {
//        return logs;
//    }
}
