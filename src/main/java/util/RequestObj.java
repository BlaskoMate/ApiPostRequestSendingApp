package util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RequestObj {

    private final String url;
    private final String body;
    private final LocalDateTime date;
    private static final String BODY_ARG_DELIMITER = ":";
    private static final int URL_INDEX = 1;
    private static final int BODY_START_INDEX = 2;
    private static final int BODY_KEY_INDEX = 0;
    private static final int BODY_VALUE_INDEX = 1;

    public RequestObj(List<String> requestInfo, LocalDateTime date) {
        url = requestInfo.get(URL_INDEX);
        body = formatRequestBody(requestInfo.subList(BODY_START_INDEX, requestInfo.size()));
        this.date = date;
    }

    public RequestObj(String url, String body, LocalDateTime date) {
        this.url = url;
        this.body = body;
        this.date = date;
    }

    private String formatRequestBody(List<String> body) {
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

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDate() {
        return date;
    }

}
