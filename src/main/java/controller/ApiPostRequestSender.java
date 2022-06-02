package controller;

import dao.implementation.LoggerDaoMem;

public class ApiPostRequestSender {


    private LoggerDaoMem logger;
    //  When false the csv file gets overwritten
    private final boolean APPEND_FILE;
    private static final String CSV_DELIMITER = ",";


    public ApiPostRequestSender(){
        this.logger = LoggerDaoMem.getInstance();
        APPEND_FILE = true;
    }

    public ApiPostRequestSender(boolean append){
        this.logger = LoggerDaoMem.getInstance();
        APPEND_FILE = append;
    }

}
