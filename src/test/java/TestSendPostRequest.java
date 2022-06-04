import mate.blasko.apihelper.controller.ApiPostRequestSender;
import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestSendPostRequest {

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
    public void TesSendPostRequestLog() throws IOException {
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList(
                "https://example.com/login", "customer:customerId", "api_key:api_key_"));
        sender.send(requestInfo);
        Assertions.assertEquals(Util.getCSVDataString(LoggerDaoMem.getInstance().LOGGER_FILE_PATH)
                , "404;Not Found;https://example.com/login;{\"customer\":\"customerId\",\"api_key\":\"api_key_\"}");
    }

    @Test
    public void TestBulkSendPostRequestLogs() throws IOException {
        LoggerDaoMem logger = LoggerDaoMem.getInstance();
        sender.bulkSend("src/test/resources/TestPostRequests.csv");
        Assertions.assertEquals(Util.getCSVDataString(logger.LOGGER_FILE_PATH)
                , "404;Not Found;https://example.com/login;{\"customer\":\"customerId\",\"api_key\":\"api_key\"}\n" +
                        "404;Not Found;https://example.com/login;{\"customer\":\"customerId2\",\"api_key\":\"api_key2\"}");
    }

    @Test
    public void TestThrowMalformedUrlExceptionForSendPostRequestWithMalformedUrl(){
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList("not an url"));
        Assertions.assertThrows(MalformedURLException.class, () -> sender.send(requestInfo));
    }

}
