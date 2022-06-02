package controller;

import dao.implementation.LoggerDaoMem;
import util.RequestObj;
import util.ResponseObj;
import util.Util;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class ApiPostRequestSender {



    //  When false the csv file gets overwritten
    private final boolean APPEND_FILE;

    private LoggerDaoMem logger;
    private static final String CSV_DELIMITER = ",";


    public ApiPostRequestSender(){
        this.logger = LoggerDaoMem.getInstance();
        APPEND_FILE = true;
    }

    public ApiPostRequestSender(boolean append){
        this.logger = LoggerDaoMem.getInstance();
        APPEND_FILE = append;
    }

    public void bulkSend(String path, LocalDateTime date) throws IOException {
        List<List<String>> requests = Util.getCSVDataList(path, CSV_DELIMITER);
        for (List<String> requestInfo : requests){
            RequestObj request = new RequestObj(requestInfo, date);
            System.out.println(sendPostRequest(request));
        }
    }


    public String sendPostRequest(RequestObj requestObj) throws IOException {
        URL url = new URL(requestObj.getUrl());
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);

        byte[] out = requestObj.getBody().getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.connect();
        try(OutputStream os = http.getOutputStream()) {
            os.write(out);
        }

        ResponseObj responseObj = new ResponseObj(requestObj, http.getResponseCode(), http.getResponseMessage());
        logger.addLog(responseObj, APPEND_FILE);
        return http.getResponseMessage();
    }


}
