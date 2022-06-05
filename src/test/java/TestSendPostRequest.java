import mate.blasko.apihelper.controller.ApiPostRequestSender;
import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                "https://example.com/login", "customer:customerId api_key:api_key_"));
        sender.send(requestInfo);

        ArrayList<String> actualResult = new ArrayList<>(List.of(
                "404;Not Found;https://example.com/login;{\"customer\":\"customerId\",\"api_key\":\"api_key_\"}"));
        Assertions.assertEquals(Util.getCSVDataList(LoggerDaoMem.getInstance().LOGGER_FILE_PATH), actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "not_an_url customer:customerId",
            "https://example.com/login customer:customerId api_key",
            "https://example.com/login customer customerId"})
    public void TestNoLogCreatedWhenSendingPostRequestWithMalformedData(String command) throws IOException {
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList(command.split(" ")));
        sender.send(requestInfo);

        ArrayList<String> logs = Util.getCSVDataList(LoggerDaoMem.getInstance().LOGGER_FILE_PATH);
        Assertions.assertEquals(logs.size(), 1);
        Assertions.assertEquals(logs.get(0), "");
    }

    @Test
    public void TestBulkSendPostRequestLogs() throws IOException {
        LoggerDaoMem logger = LoggerDaoMem.getInstance();
        sender.bulkSend("src/test/resources/TestPostRequests.csv");

        ArrayList<String> actualResult = new ArrayList<>(Arrays.asList(
                "404;Not Found;https://example.com/login;{\"customer\":\"customerId\",\"api_key\":\"api_key\"}",
                "404;Not Found;https://example.com/login;{\"customer\":\"customerId2\",\"api_key\":\"api_key2\"}"));
        Assertions.assertEquals(
                Util.getCSVDataList(logger.LOGGER_FILE_PATH), actualResult);
    }

}
