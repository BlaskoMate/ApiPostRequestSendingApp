package mate.blasko.apihelper.dao.mem;


import mate.blasko.apihelper.util.ResponseObj;
import mate.blasko.apihelper.util.Util;

import java.io.IOException;
import java.util.Arrays;

public class LoggerDaoMem{

    private static LoggerDaoMem instance;
    private String[] logger = new String[0];
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

    public void printRecentLogsToConsole(){
        for (String row : logger){
            System.out.println(row);
        }
    }

    public void appending(ResponseObj response) {
        String log = createLog(response);
        Util.writeToCSVFile(LOGGER_FILE_PATH, log, true);
    }

    public String createLog(ResponseObj responseObj) {
        String log = formatLog(responseObj);
        logger = Arrays.copyOf(logger, logger.length + 1);
        logger[logger.length - 1] = log;
        return log;
    }


    public String formatLog(ResponseObj responseObj){
        int status = responseObj.getStatus();
        String message = responseObj.getMessage();
        String url = responseObj.getUrl();
        String body = responseObj.getBody();

        return String.format("\n%s,%s,%s,%s", status, message, url, body);
    }

    public void clearLogs() throws IOException {
        Util.deleteCsvFileContent(LOGGER_FILE_PATH);
    }
}