import mate.blasko.apihelper.controller.ApiPostRequestSenderApp;
import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import mate.blasko.apihelper.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestApiPostRequestSenderApp extends TestBasePostRequestSender{

    private final ApiPostRequestSenderApp app = new ApiPostRequestSenderApp();


    @Test
    public void TestPostRequestSendingWithCommandSyntax() throws IOException {
        String[] testData = new String[] {"-d", "https://example.com/login",  "customer:customerId", "type:command"};
        app.handlePostRequestCommand(testData);

        String actualResult = "404;Not Found;https://example.com/login;{\"customer\":\"customerId\",\"type\":\"command\"}";
        ArrayList<String> logs = Util.getCSVDataList(LoggerDaoMem.getInstance().getLOGGER_FILE_PATH());
        Assertions.assertEquals(logs.size(), 1);
        Assertions.assertEquals(logs.get(0), actualResult);
    }
}
