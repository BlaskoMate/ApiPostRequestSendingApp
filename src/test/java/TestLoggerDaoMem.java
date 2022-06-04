import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
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
        ArrayList<String> testData = new ArrayList<>(Arrays.asList("999", "message", "url", "body:body"));
        ResponseObj responseObj = new ResponseObj(testData);
        String log = LoggerDaoMem.getInstance().formatLog(responseObj);
        Assertions.assertEquals(log, "999;message;url;{\"body\":\"body\"}");
    }
}
