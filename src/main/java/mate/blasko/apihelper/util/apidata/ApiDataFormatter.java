package mate.blasko.apihelper.util.apidata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApiDataFormatter {

    public static final String CSV_DELIMITER = ";";

    private static final String BODY_ARG_DELIMITER = ":";
    private static final int BODY_KEY_INDEX = 0;
    private static final int BODY_VALUE_INDEX = 1;

    public static ArrayList<String> formatRequestInfo(List<String> requestInfo) {
        ArrayList<String> formattedRequest = new ArrayList<>(List.of(requestInfo.get(RequestObj.URL_INDEX)));
        ArrayList<String> body = new ArrayList<>(requestInfo.subList(RequestObj.BODY_Start_INDEX, requestInfo.size()));
        formattedRequest.add(formatBody(body));
        return formattedRequest;
    }

    public static ArrayList<String> formatResponseInfo(List<String> responseInfo) {
        ArrayList<String> formattedResponse = new ArrayList<>((responseInfo.subList(0, ResponseObj.BODY_INDEX)));
        ArrayList<String> body = new ArrayList<>(responseInfo.subList(ResponseObj.BODY_INDEX, responseInfo.size()));
        formattedResponse.add(formatBody(body));
        return formattedResponse;
    }

    public static String formatLog(ResponseObj responseObj){
        String del = CSV_DELIMITER;
        int status = responseObj.getStatus();
        String message = responseObj.getMessage();
        String url = responseObj.getUrl();
        String body = responseObj.getBody();

        return String.format("%s%s%s%s%s%s%s", status, del, message, del, url, del, body);
    }

    public static ResponseObj revertLogToResponseObj(String log){
        ArrayList<String> inputLogInfo = new ArrayList<>(Arrays.asList(log.split(CSV_DELIMITER)));
        return new ResponseObj(inputLogInfo);
    }

    public static String formatBody(List<String> body) {
        StringBuilder result = new StringBuilder("{");

        result.append(body
                .stream()
                .map(e -> {
                    String[] pair = e.split(BODY_ARG_DELIMITER);
                    String key = pair[BODY_KEY_INDEX];
                    String value = pair[BODY_VALUE_INDEX];
                    return String.format("\"%s\"%s\"%s\"", key, BODY_ARG_DELIMITER, value);
                }).collect(Collectors
                        .joining(",")));

        result.append("}");
        return String.valueOf(result);
    }

}
