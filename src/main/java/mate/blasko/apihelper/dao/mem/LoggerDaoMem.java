package mate.blasko.apihelper.dao.mem;


import mate.blasko.apihelper.util.Util;
import mate.blasko.apihelper.util.apidata.ApiDataFormatter;
import mate.blasko.apihelper.util.apidata.ResponseObj;

import java.io.IOException;
import java.util.ArrayList;

public class LoggerDaoMem{



    private static LoggerDaoMem instance;
    private final ArrayList<String> logs = new ArrayList<>();
    private String LOGGER_FILE_PATH;

    private LoggerDaoMem(){
        LOGGER_FILE_PATH = "src/main/resources/Logger.csv";
    }

    public static LoggerDaoMem getInstance(){
        if (instance == null){
            instance = new LoggerDaoMem();
        }
        return instance;
    }

    public String getLOGGER_FILE_PATH() {
        return LOGGER_FILE_PATH;
    }

    public void setLOGGER_FILE_PATH(String LOGGER_FILE_PATH) {
        this.LOGGER_FILE_PATH = LOGGER_FILE_PATH;
    }

    public void appending(ResponseObj response) {
        String log = createLog(response);
        Util.writeToCSVFile(LOGGER_FILE_PATH, log, true);
    }

    public String createLog(ResponseObj responseObj) {
        String log = ApiDataFormatter.formatLog(responseObj);
        logs.add(log);
        return log;
    }


    public void clearLogs() throws IOException {
        Util.deleteCsvFileContent(LOGGER_FILE_PATH);
    }

    public String[] getLogsFromCsv() throws IOException {
        ArrayList<String> logs = Util.getCSVDataList(LOGGER_FILE_PATH);
        return logs.toArray(new String[0]);
    }

    public String[] getLogsFromMemory() {
        return logs.toArray(new String[0]);
    }
}
