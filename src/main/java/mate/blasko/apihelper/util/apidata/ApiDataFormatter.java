package mate.blasko.apihelper.util.apidata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ApiDataFormatter {

    public static final String CSV_DELIMITER = ";";

    public static final String BODY_KEY_VALUE_DELIMITER = ":";
    public static final String BODY_ARG_DELIMITER = " ";
    private static final int BODY_KEY_INDEX = 0;
    private static final int BODY_VALUE_INDEX = 1;

    public static ArrayList<String> formatRequestInfo(ArrayList<String> requestInfo) {
        String formattedBody = formatRequestBody(requestInfo.get(RequestObj.BODY_INDEX));
        requestInfo.set(RequestObj.BODY_INDEX, formattedBody);
        return requestInfo;
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

    public static String formatRequestBody(String body) {
        StringBuilder result = new StringBuilder("{");

        result.append(Arrays
                .stream(body.split(BODY_ARG_DELIMITER))
                .map(e -> {
                    String[] pair = e.split(BODY_KEY_VALUE_DELIMITER);
                    String key = pair[BODY_KEY_INDEX];
                    String value = pair[BODY_VALUE_INDEX];
                    return String.format("\"%s\"%s\"%s\"", key, BODY_KEY_VALUE_DELIMITER, value);
                }).collect(Collectors
                        .joining(",")));

        result.append("}");
        return String.valueOf(result);
    }

}
