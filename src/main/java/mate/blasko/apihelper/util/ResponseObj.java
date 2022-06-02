package mate.blasko.apihelper.util;

public class ResponseObj extends ApiData{

    private int status;
    private String message;

    public ResponseObj(RequestObj requestObj, int status, String message) {
        super(requestObj.getUrl(), requestObj.getBody());
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
