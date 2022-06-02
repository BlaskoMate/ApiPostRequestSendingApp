package dao.implementation;


import dao.LoggerDao;

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

}
