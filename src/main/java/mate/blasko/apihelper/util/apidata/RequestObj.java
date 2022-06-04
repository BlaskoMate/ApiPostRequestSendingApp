package mate.blasko.apihelper.util.apidata;

import java.util.List;

public class RequestObj extends ApiData{

    public static final int URL_INDEX = 0;
    public static final int BODY_Start_INDEX = 1;

    public RequestObj(List<String> requestInfo) {
        super(requestInfo.get(URL_INDEX), requestInfo.get(BODY_Start_INDEX));
    }


}
