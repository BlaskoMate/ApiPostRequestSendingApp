package util;

public class ResponseObj extends RequestObj{

    private int status;
    private String message;

    public ResponseObj(RequestObj requestObj, int status, String message) {
        super(requestObj.getUrl(), requestObj.getBody(), requestObj.getDate());
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
