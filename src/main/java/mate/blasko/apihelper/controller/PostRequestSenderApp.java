package mate.blasko.apihelper.controller;

import mate.blasko.apihelper.util.Display;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PostRequestSenderApp {

    Scanner scanner = new Scanner(System.in);
    ApiPostRequestSender sender = new ApiPostRequestSender();
    String[] commands = new String[] {"-h", "help", "-p", "post", "-b", "bulk", "-r", "recent", "-a", "all", "-c", "clear"};

    public void run(String[] args) throws IOException {
        Display.clearConsole();
        if (args.length >= 1){
            // validateArgs(){
                //  sender.send(Arrays.asList(args));
                //  Display.printLogsToConsole(LoggerDaoMem.getInstance().getLogs());
                //  return;
            // }
        } else {
            while (true){
                Display.getHelp();
                String feature = chooseFeature();
                executeFeature(feature);
            }
        }



    }

    private String chooseFeature() throws IOException {
        while (true) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (Arrays.stream(commands).anyMatch(input::contains)){
                    executeFeature(input);
                }
            }
            scanner.nextLine();
            // System.out.println("invalid command, try \"help\"");
            Display.invalidCommand();
        }
    }

    private void sendPostRequest() {
        //  Display.sendPostRequestDetails();
        String input = scanner.nextLine();
        //  validate input
    }

    private void executeFeature(String feature) throws IOException {
        switch (feature){
            case "-h", "help":
                // help
                break;
            case "-p", "post":
                // send
                break;
            case "-b", "bulk":
                // bulk send
                break;
            case "-r", "recent":
                // recentLogs
                break;
            case "-a", "all":
                // allLogs
                break;
            case "-c", "clear":
                // clear
                break;
            case "-e", "exit":
                System.exit(1);
        }
    }


}
