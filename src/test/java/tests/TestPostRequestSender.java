package tests;

import helper.TestHelperPostRequestSender;
import mate.blasko.apihelper.controller.PostRequestSender;
import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Display;
import mate.blasko.apihelper.util.Util;
import mate.blasko.apihelper.util.apidata.ResponseObj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestPostRequestSender extends TestHelperPostRequestSender {

    PostRequestSender sender = new PostRequestSender();


    @Test
    public void TesSendPostRequestLog() throws IOException {
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList(
                "https://example.com/login", "customer:customerId api_key:api_key_"));
        sender.send(requestInfo);

        String actualResult = "404;Not Found;https://example.com/login;{\"customer\":\"customerId\",\"api_key\":\"api_key_\"}";
        ArrayList<String> logs = Util.getCSVDataList(LoggerDaoMem.getInstance().getLOGGER_FILE_PATH());
        Assertions.assertEquals(logs.size(), 1);
        Assertions.assertEquals(logs.get(0), actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "not_an_url customer:customerId",
            "https://example.com/login customer:customerId api_key",
            "https://example.com/login customer customerId"})
    public void TestNoLogCreatedWhenSendingPostRequestWithMalformedData(String command) throws IOException {
        ArrayList<String> requestInfo = new ArrayList<>(Arrays.asList(command.split(" ")));
        sender.send(requestInfo);

        ArrayList<String> logs = Util.getCSVDataList(LoggerDaoMem.getInstance().getLOGGER_FILE_PATH());
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
                Util.getCSVDataList(logger.getLOGGER_FILE_PATH()), actualResult);
    }

}
