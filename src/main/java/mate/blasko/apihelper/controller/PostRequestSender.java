package mate.blasko.apihelper.controller;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Display;
import mate.blasko.apihelper.util.apidata.ApiDataFormatter;
import mate.blasko.apihelper.util.apidata.RequestObj;
import mate.blasko.apihelper.util.apidata.ResponseObj;
import mate.blasko.apihelper.util.Util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.apache.commons.validator.routines.UrlValidator;

public class PostRequestSender {

    private LoggerDaoMem logger;


    public PostRequestSender(){
        this.logger = LoggerDaoMem.getInstance();
    }

    public void bulkSend(String path) throws IOException {
        ArrayList<ArrayList<String>> requests = Util.getCSVSplitDataList(path);
        for (ArrayList<String> requestInfo : requests){
            send(requestInfo);
        }
    }

    public void send(ArrayList<String> requestInfo) throws IOException {
        if (! validRequestInfo(requestInfo)){
            return;
        }
        ArrayList<String> formattedRequest = ApiDataFormatter.formatRequestInfo(requestInfo);
        RequestObj request = new RequestObj(formattedRequest);
        ResponseObj response = sendPostRequest(request);
        logger.appending(response);
        Display.printLog(response);
    }

    public boolean validRequestInfo(ArrayList<String> requestInfo) {
        if (requestInfo.size() == 2){
            if (isValidUrl(requestInfo.get(RequestObj.URL_INDEX))){
                if (isValidPostRequestBody(requestInfo.get(RequestObj.BODY_INDEX))){
                    return true;
                }
                Display.invalidPostRequestBody();
            } else {
                Display.invalidPostRequestUrl();
            }
        } else {
            Display.InvalidPostRequestParameterAmount();
        }
        return false;
    }

    public boolean isValidPostRequestBody(String Body) {
        String[] args = Body.split(ApiDataFormatter.BODY_ARG_DELIMITER);
        for (String arg : args){
            String[] pair = arg.split(ApiDataFormatter.BODY_KEY_VALUE_DELIMITER);
            if (pair.length != 2){
                return false;
            }
            if (pair[0].equals("") || pair[1].equals("")){
                return false;
            }
        }
        return true;
    }

    public boolean isValidUrl(String url){
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
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
