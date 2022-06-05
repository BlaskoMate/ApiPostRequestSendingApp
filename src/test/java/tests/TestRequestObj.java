package tests;

import mate.blasko.apihelper.util.apidata.ApiDataFormatter;
import mate.blasko.apihelper.util.apidata.RequestObj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestRequestObj {

    @Test
    public void TestRequestObjCreationWithMultipleBodyArgsAsSingleString(){
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList(
                "url", "customerId:customerIdId api_key:api_key_"));
        ArrayList<String> formattedRequest = ApiDataFormatter.formatRequestInfo(requestInfo);

        String actualResult = "{\"customerId\":\"customerIdId\",\"api_key\":\"api_key_\"}";
        Assertions.assertEquals(new RequestObj(formattedRequest).getBody(), actualResult);
    }

}
