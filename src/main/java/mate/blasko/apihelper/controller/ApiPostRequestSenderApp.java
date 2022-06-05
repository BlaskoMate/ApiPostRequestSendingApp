package mate.blasko.apihelper.controller;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Display;
import mate.blasko.apihelper.util.Util;
import mate.blasko.apihelper.util.apidata.ApiDataFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ApiPostRequestSenderApp {

    private final Scanner scanner = new Scanner(System.in);
    private final PostRequestSender sender = new PostRequestSender();
    private final String[] commands = new String[] {"-h", "help", "-d", "post", "-b", "bulk", "-r", "recent", "-a", "all", "-c", "clear", "-e", "exit"};
    private static final int COMMAND_INDEX = 0;
    private static final int BULK_PATH_INDEX = 1;

    public void run(String[] args) throws IOException {
        Display.clearConsole();
        if (args.length >= 1){
             if (validCommand(args[COMMAND_INDEX])){
                  executeCommand(args);
                  return;
             }
             Display.invalidCommand();
        } else {
            Display.getHelp();
            while (true){
                String[] command = chooseCommand();
                executeCommand(command);
            }
        }
    }

    private boolean validCommand(String command){
        return Util.anyMatch(commands, command);
    }

    private String[] chooseCommand(){
        while (true) {
            if (scanner.hasNextLine()) {
                String[] inputArray = scanner.nextLine().split(" ");
                if (validCommand(inputArray[COMMAND_INDEX])){
                    return inputArray;
                } else {
                    Display.invalidCommand();
                }
            }
        }
    }

    public void handlePostRequestCommand(String[] commandArray) throws IOException {
        if (isValidPostRequestCommandLength(commandArray)){
            String[] requestArray = Arrays.copyOfRange(commandArray, 1, commandArray.length);
            ArrayList<String> requestInfo = ApiDataFormatter.formatPostRequestCommand(requestArray);
            sender.send(requestInfo);
        } else {
            Display.invalidPostRequestBody();
        }
    }

    public boolean isValidPostRequestCommandLength(String[] commandArray){
        return commandArray.length >= 3;
    }

    public void handleBulkPostRequestCommand(String[] commandArray) throws IOException {
        if ( validBulkSendPostRequestCommand(commandArray)){
            sender.bulkSend(commandArray[BULK_PATH_INDEX]);
        }
    }

    private boolean validBulkSendPostRequestCommand(String[] commandArray){
        if (commandArray.length == 2){
            if (Util.doesFilePathExist(commandArray[BULK_PATH_INDEX])){
                return true;
            } else {
                Display.filePathNotExist();
            }
        } else {
            Display.invalidBulkSendPostRequest();
        }
        return false;
    }

    private void executeCommand(String[] commandArray) throws IOException {
        String command = commandArray[COMMAND_INDEX];
        switch (command){
            case "-h", "help":
                Display.printHelp();
                break;
            case "-d", "post":
                handlePostRequestCommand(commandArray);
                break;
            case "-b", "bulk":
                handleBulkPostRequestCommand(commandArray);
                break;
            case "-r", "recent":
                Display.printLogs(LoggerDaoMem.getInstance().getLogsFromMemory());
                break;
            case "-a", "all":
                Display.printLogs(LoggerDaoMem.getInstance().getLogsFromCsv());
                break;
            case "-c", "clear":
                Display.clearConsole();
                break;
            case "-e", "exit":
                System.exit(0);
                break;
        }
    }
}
