import mate.blasko.apihelper.controller.ApiPostRequestSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestValidatePostRequestArguments {

    ApiPostRequestSender sender = new ApiPostRequestSender();

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
            ":"})
    public void TestInvalidPostRequestBodys(String body){
        Assertions.assertFalse(sender.isValidPostRequestBody(body));
    }

    @Test
    public void TestInvalidUrl(){
        Assertions.assertFalse(sender.isValidUrl(""));
    }

}
