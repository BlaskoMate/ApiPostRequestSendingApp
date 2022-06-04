package mate.blasko.apihelper.util.apidata;

import java.util.ArrayList;

public class ResponseObj extends ApiData{

    private int status;
    private String message;
    private static final int STATUS_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;
    private static final int URL_INDEX = 2;
    private static final int BODY_INDEX = 3;

    public ResponseObj(RequestObj requestObj, int status, String message) {
        super(requestObj.getUrl(), requestObj.getBody());
        this.status = status;
        this.message = message;
    }


    public ResponseObj(ArrayList<String> logInfo) {
        super(logInfo.get(URL_INDEX), logInfo.get(BODY_INDEX));
        this.status = Integer.valueOf(logInfo.get(STATUS_INDEX));
        this.message = logInfo.get(MESSAGE_INDEX);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
