package mate.blasko.apihelper.controller;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Display;
import mate.blasko.apihelper.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MyApp {

    Scanner scanner = new Scanner(System.in);
    ApiPostRequestSender sender = new ApiPostRequestSender();

    public void run(String[] args) throws IOException {
        Display.clearConsole();
        if (args.length > 1){
            sender.send(Arrays.asList(args));
//            Display.printLogsToConsole(LoggerDaoMem.getInstance().getLogs());
            return;
        }

        Display.printHelp();
        while (true){
            int feature = chooseFeature();
            executeFeature(feature);
        }


    }

    private int chooseFeature() {
        Display.printMenu();
        while (true) {
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input > 0 && input < 6){
                    return input;
                }
            }
            scanner.nextLine();
            Display.clearConsole();
            // System.out.println("error");
            Display.printMenu();
        }
    }

    private void executeFeature(int feature) throws IOException {
        switch (feature){
            case 1:
                // send
                break;
            case 2:
                // bulk send
                break;
            case 3:
//                ArrayList<String> logs = LoggerDaoMem.getInstance().getLogs();
//                Display.printLogsToConsole(logs);
                break;
            case 4:
                List<List<String>> AllLogs = Util.getCSVDataList(LoggerDaoMem.getInstance().LOGGER_FILE_PATH);
                // Display.printLogs
                break;
            case 5:
                System.exit(1);
        }
    }


}
