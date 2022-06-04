package mate.blasko.apihelper.util.apidata;

import java.util.ArrayList;

public class ResponseObj extends ApiData{

    private final int status;
    private final String message;
    private static final int STATUS_INDEX = 0;
    private static final int MESSAGE_INDEX = 1;
    private static final int URL_INDEX = 2;
    private static final int BODY_INDEX = 3;

    public ResponseObj(RequestObj requestObj, int status, String message) {
        super(requestObj.getUrl(), requestObj.getBody());
        this.status = status;
        this.message = message;
    }


    public ResponseObj(ArrayList<String> inputLogInfo) {
        super(inputLogInfo.get(URL_INDEX), inputLogInfo.get(BODY_INDEX));
        this.status = Integer.parseInt(inputLogInfo.get(STATUS_INDEX));
        this.message = inputLogInfo.get(MESSAGE_INDEX);
    }


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
