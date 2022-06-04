package mate.blasko.apihelper.util;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;

import java.util.ArrayList;

public class Display {

    public static void printLogs(String[] logs){
        for (String log : logs){
            ResponseObj responseObj = ApiDataFormatter.revertLogToResponseObj(log);
            printLog(responseObj);
        }
    }

    public static void printLog(ResponseObj log){
        boolean success = log.getStatus() == 200;
        String body = String.join("\n    ", log.getBody().split(","));
        System.out.printf(
                """
                    POST request was %s
                    URL: %s
                    Body:\040
                        %s
                    Status: %s
                    Message: %s
                    """, success, log.getUrl(), body, log.getStatus(), log.getMessage());

    }

    public static void printHelp() {
        System.out.printf(
                """
                    Default file path for logging requests is set to: %s ...
                    Command,   Description   < Arguments >
                    
                    -h, --help,     Get help for commands
                    -d, --data,     HTTP POST data   < key1:value1 key2:value2 ...>
                    -b, --bulk,     Bulk send POST data from file   < file_path >
                    -r, --recent,   Print recent logs since launching the app
                    -a, --all,      Print all logs from log file
                    -c, --clear     Clear console
                    -e, --exit      Stop the application");
                    """, LoggerDaoMem.getInstance().LOGGER_FILE_PATH);
    }

    public static void printMenu() {
        System.out.println("1) send post request\n2) send multiple post requests from file\n3) print recently sent requests\n4) print all sent requests\n5) exit");
    }


    public static void clearConsole(){
        // TODO:
    }

    public static void invalidCommand() {
        System.out.println("Invalid command.");
        getHelp();
    }

    public static void getHelp() {
        System.out.println("Type in a command.\nIf you need help, type \"-h\" or \"help\" for more information..");
    }
}
