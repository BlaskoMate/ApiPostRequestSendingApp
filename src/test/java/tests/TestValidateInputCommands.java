package tests;

import mate.blasko.apihelper.controller.PostRequestSender;
import mate.blasko.apihelper.util.Display;
import mate.blasko.apihelper.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestValidateInputCommands {

    PostRequestSender sender = new PostRequestSender();

    @Test
    public void TestSuccessfulStatusPrint(){
        Assertions.assertEquals(Display.getResultStrForResponseStatus(200), "was successful");
    }

    @Test
    public void TestUnSuccessfulStatusPrint(){
        Assertions.assertEquals(Display.getResultStrForResponseStatus(404), "failed");
    }

    @Test
    public void TestValidPostRequestBody(){
        Assertions.assertTrue(sender.isValidPostRequestBody(
                "customer:customerId api_key:api_key_"));
    }

    @Test
    public void TestValidUrl(){
        Assertions.assertTrue(sender.isValidUrl(
                "https://example.com/login"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "customer: customerId",
            "customer:customerId api_key",
            "customer customerId",
            ":value",
            ":"})
    public void TestInvalidPostRequestBody(String body){
        Assertions.assertFalse(sender.isValidPostRequestBody(body));
    }

    @Test
    public void TestInvalidUrl(){
        Assertions.assertFalse(sender.isValidUrl(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"src", "src/test/resources/non_existing_file.csv"})
    public void TestInvalidFilePath(){
        Assertions.assertFalse(Util.doesFilePathExist("src"));
    }

}
