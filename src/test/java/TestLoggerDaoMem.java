import controller.ApiPostRequestSender;
import dao.implementation.LoggerDaoMem;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import util.Util;

import java.io.IOException;
import java.time.LocalDateTime;

public class TestLoggerDaoMem {

    @Test
    public void TestBulkSendFeature() throws IOException {
        LoggerDaoMem logger = LoggerDaoMem.getInstance("src/test/resources/TestLogger.csv");
        ApiPostRequestSender sender = new ApiPostRequestSender(true);
        LocalDateTime now = LocalDateTime.now();
        sender.bulkSend("src/test/resources/TestPostRequests.csv", now);
        Assertions.assertEquals(Util.getCSVDataString(logger.LOGGER_FILE_PATH), "");
    }

}
