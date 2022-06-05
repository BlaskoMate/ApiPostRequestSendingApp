package mate.blasko.apihelper;

import mate.blasko.apihelper.controller.ApiPostRequestSenderApp;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApiPostRequestSenderApp app = new ApiPostRequestSenderApp();
        app.run(args);
    }
}
