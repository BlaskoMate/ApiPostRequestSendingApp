package mate.blasko.apihelper.main;

import mate.blasko.apihelper.controller.PostRequestSenderApp;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PostRequestSenderApp app = new PostRequestSenderApp();
        app.run(args);
    }
}
