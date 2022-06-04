import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.apidata.ApiDataFormatter;
import mate.blasko.apihelper.util.apidata.ResponseObj;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;

public class TestLoggerDaoMem {

    @BeforeAll
    public static void setup(){
        LoggerDaoMem.getInstance("src/test/resources/TestLogger.csv");
    }

    @Test
    public void TestFormatLog(){
        ArrayList<String> responseInfo = new ArrayList<>(Arrays.asList("999", "message", "url", "customer:customerId"));
        ArrayList<String> formattedResponse = ApiDataFormatter.formatResponseInfo(responseInfo);

        ResponseObj responseObj = new ResponseObj(formattedResponse);
        String log = ApiDataFormatter.formatLog(responseObj);
        Assertions.assertEquals(log, "999;message;url;{\"customer\":\"customerId\"}");
    }
}
