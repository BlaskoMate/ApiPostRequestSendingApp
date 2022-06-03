import mate.blasko.apihelper.util.apidata.RequestObj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestRequestObj {

    @Test
    public void TestRequestObjCreationWithCorrectBodySyntax(){
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList("url", "a1:b2", "c3:d4"));
        Assertions.assertEquals(new RequestObj(requestInfo).getBody(), "{\"a1\":\"b2\",\"c3\":\"d4\"}");
    }

    @Test
    public void TestRequestObjCreationWithMalformedBodySyntax(){
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList("url", "customer customerId"));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> new RequestObj(requestInfo));
    }
}
