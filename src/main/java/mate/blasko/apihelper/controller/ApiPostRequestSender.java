package mate.blasko.apihelper.controller;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.RequestObj;
import mate.blasko.apihelper.util.ResponseObj;
import mate.blasko.apihelper.util.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiPostRequestSender {

    private LoggerDaoMem logger;
    private static final String CSV_DELIMITER = ",";


    public ApiPostRequestSender(){
        this.logger = LoggerDaoMem.getInstance();
    }

    public void bulkSend(String path) throws IOException {
        List<List<String>> requests = Util.getCSVDataList(path, CSV_DELIMITER);
        for (List<String> requestInfo : requests){
            send(requestInfo);
        }
    }

    public void send(List<String> requestInfo) throws IOException {
        RequestObj request = new RequestObj(requestInfo);
        ResponseObj response = sendPostRequest(request);
        logger.appending(response);
    }


    public ResponseObj sendPostRequest(RequestObj requestObj) throws IOException {
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

        return new ResponseObj(requestObj, http.getResponseCode(), http.getResponseMessage());

    }


}
