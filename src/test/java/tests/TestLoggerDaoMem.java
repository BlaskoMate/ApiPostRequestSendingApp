package tests;

import helper.TestHelperLogger;
import mate.blasko.apihelper.util.apidata.ApiDataFormatter;
import mate.blasko.apihelper.util.apidata.ResponseObj;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class TestLoggerDaoMem extends TestHelperLogger {

    @Test
    public void TestRevertLogToResponseObj(){
        ResponseObj responseObj = ApiDataFormatter.revertLogToResponseObj("999;message;url;{\"customer\":\"customerId\",\"api_key\":\"api_key\"}");
        Assertions.assertEquals(responseObj.getStatus(), 999);
        Assertions.assertEquals(responseObj.getMessage(), "message");
        Assertions.assertEquals(responseObj.getUrl(), "url");
        Assertions.assertEquals(responseObj.getBody(), "{\"customer\":\"customerId\",\"api_key\":\"api_key\"}");
    }
}
