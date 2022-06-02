package dao;

import util.ResponseObj;

public interface LoggerDao {

    void printRecentLogsToConsole();
    String writeLogToFile(String log, boolean append);
    String formatLog(ResponseObj responseObj);
    void addLog(ResponseObj responseObj, boolean append);
}
