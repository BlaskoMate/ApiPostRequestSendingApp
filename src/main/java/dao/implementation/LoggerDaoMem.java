package dao.implementation;


import dao.LoggerDao;
import util.ResponseObj;
import util.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class LoggerDaoMem implements LoggerDao {

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

    public void addLog(ResponseObj responseObj, boolean append){
        String log = formatLog(responseObj);
        logger = Arrays.copyOf(logger, logger.length + 1);
        logger[logger.length - 1] = log;
        System.out.println(writeLogToFile(log, append));
    }

    public String writeLogToFile(String log, boolean append){
        Util.writeToCSVFile(LOGGER_FILE_PATH, log, append);
        return String.format("Writing \"%s\" to filePath: %s",
                log, LOGGER_FILE_PATH);
    }

    public String formatLog(ResponseObj responseObj){
        LocalDateTime date = responseObj.getDate();
        int status = responseObj.getStatus();
        String message = responseObj.getMessage();
        String url = responseObj.getUrl();
        String body = responseObj.getBody();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        return String.format("\n%s,%s,%s,%s,%s", dateFormat.format(date), status, message, url, body);
    }

}
