import mate.blasko.apihelper.util.apidata.ApiDataFormatter;
import mate.blasko.apihelper.util.apidata.RequestObj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestRequestObj {

    @Test
    public void TestRequestObjCreationWithMultipleBodyArgs(){
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList("url", "customerId:customerIdId", "api_key:api_key_"));
        ArrayList<String> formattedRequest = ApiDataFormatter.formatRequestInfo(requestInfo);
        Assertions.assertEquals(new RequestObj(formattedRequest).getBody(), "{\"customerId\":\"customerIdId\",\"api_key\":\"api_key_\"}");
    }

    @Test
    public void TestThrowIndexOutOfBoundForRequestObjCreationWithMalformedBodySyntax(){
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList("url", "customer customerId"));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> ApiDataFormatter.formatRequestInfo(requestInfo));
    }
}
