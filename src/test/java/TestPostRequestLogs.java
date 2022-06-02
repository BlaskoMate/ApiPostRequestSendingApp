import mate.blasko.apihelper.controller.ApiPostRequestSender;
import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestPostRequestLogs {

    ApiPostRequestSender sender = new ApiPostRequestSender();

    @BeforeAll
    static void setupTests(){
        LoggerDaoMem.getInstance("src/test/resources/TestLogger.csv");
    }

    @BeforeEach
    public void clearTestLogFile() throws IOException {
        LoggerDaoMem.getInstance().clearLogs();
    }


    @Test
    public void TestSingleSendLog() throws IOException {
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList(
                "https://example.com/login", "customer:customerId", "api_key:api_key"));
        sender.send(requestInfo);
        Assertions.assertEquals(Util.getCSVDataString(LoggerDaoMem.getInstance().LOGGER_FILE_PATH)
                , "404,Not Found,https://example.com/login,{\"customer\":\"customerId\",\"api_key\":\"api_key\"}");
    }




}
