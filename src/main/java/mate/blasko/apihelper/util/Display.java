package mate.blasko.apihelper.util;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.apidata.ApiDataFormatter;
import mate.blasko.apihelper.util.apidata.ResponseObj;

import java.io.IOException;

public final class Display {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void printLogs(String[] logs){
        for (String log : logs){
            ResponseObj responseObj = ApiDataFormatter.revertLogToResponseObj(log);
            printLog(responseObj);
        }
    }

    public static void printLog(ResponseObj log){
        System.out.println(getLogToPrint(log));
    }

    public static String getLogToPrint(ResponseObj log){
        String result = getResultStrForResponseStatus(log.getStatus());
        String color = log.getStatus() == 200 ? ANSI_GREEN : ANSI_RED;
        String body = String.join("\n" + " ".repeat(11), log.getBody().split(","));
        return String.format(
                """
                  
                    %sPOST request %s!%s
                    
                        - URL: %s
                        - Body:\040
                              %s
                        - Status: %s
                        - Message: %s
                                            
                    """, color, result, ANSI_RESET, log.getUrl(), body, log.getStatus(), log.getMessage());
    }

    public static String getResultStrForResponseStatus(int status) {
        return status == 200 ? "was successful" : "failed";
    }

    public static void printHelp() {
        System.out.printf(
                """
                    File path for logging requests is set to: %s ...
                    Command,   Description   < Arguments >
                    
                    -h, --help,     Get help for commands
                    -d, --data,     HTTP POST data   < key1:value1 key2:value2 ...>
                    -b, --bulk,     Bulk send POST data from file   < file_path >
                    -r, --recent,   Print recent logs since launching the app
                    -a, --all,      Print all logs from log file
                    -c, --clear     Clear console
                    -e, --exit      Stop the application
                    """, LoggerDaoMem.getInstance().getLOGGER_FILE_PATH());
    }

    public static void getHelp() {
        System.out.println("Type in a command. For command list type \"help\":");
    }

    public static void clearConsole(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ignored) {
        }
    }

    public static void invalidCommand() {
        System.out.println("invalid command. Try \"help\"");
    }

    public static void invalidPostRequestUrl() {
        System.out.println("Invalid URL for post request.");
    }

    public static void invalidPostRequestBody() {
        System.out.println(
                "Invalid body syntax for POST request.\n" +
                "Try: \"-d <url> <key1:value1> <key2:value2>\"");
    }

    public static void InvalidPostRequestParameterAmount() {
        System.out.println("Invalid parameter count for POST request.");
    }

    public static void filePathNotExist() {
        System.out.println("File path does not exist.");
    }

    public static void invalidBulkSendPostRequest() {
        System.out.println(
                "Invalid body syntax for bulk post request send.\n" +
                        "Try: \"-b <filePath>\"");
    }
}
