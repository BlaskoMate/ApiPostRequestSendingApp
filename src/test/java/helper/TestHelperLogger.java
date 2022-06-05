package helper;

import mate.blasko.apihelper.dao.mem.LoggerDaoMem;
import org.junit.jupiter.api.BeforeAll;

public class TestHelperLogger {


    @BeforeAll
    static void setupTests(){
        LoggerDaoMem.getInstance().setLOGGER_FILE_PATH("src/test/resources/TestLogger.csv");
    }

}
