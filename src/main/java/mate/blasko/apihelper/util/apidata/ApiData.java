package mate.blasko.apihelper.util.apidata;

public class ApiData {

    private final String url;
    private final String body;


    public ApiData(String url, String body) {
        this.url = url;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }
}