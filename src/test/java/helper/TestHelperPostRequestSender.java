package helper;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class TestHelperPostRequestSender extends TestHelperLogger {

    @BeforeEach
    public void clearTestLogFile() throws IOException {
        LoggerDaoMem.getInstance().clearLogs();
    }
}
