package mate.blasko.apihelper.util;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;

import java.util.ArrayList;

public class Display {

    public static void printLogsToConsole(ArrayList<ArrayList<String>> logs){
        for (ArrayList<String> log : logs){
            printLogToConsole(log);
        }
    }

    public static void printLogToConsole(ArrayList<String> log){

    }

    public static void printHelp() {
        System.out.println(LoggerDaoMem.getInstance().LOGGER_FILE_PATH + " is set as default Logger file path.");
    }

    public static void printMenu() {
        System.out.println("1) send post request\n2) send multiple post requests from file\n3) print recently sent requests\n4) print all sent requests\n5) exit");
    }


    public static void clearConsole(){
        // TODO:
    }
}
