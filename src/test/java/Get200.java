import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class Get200 {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    public static String BASE_URL = "http://api.github.com";
    public static String RATE_LIMIT_ENDPOINT = "/rate_limit";
    public static String GISTS_PUBLIC_ENDPOINT = "/gists/public";

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResource() throws IOException {
        client.close();
        response.close();
    }

    @Test(dataProvider = "endpoint")
    public void firstTest(String endpoint) throws IOException {
        client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(BASE_URL + endpoint);
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void secondTest() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(BASE_URL + RATE_LIMIT_ENDPOINT);
        HttpResponse response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @DataProvider
    private Object[][] endpoint() {
        return new Object[][]{
                {""},
                {RATE_LIMIT_ENDPOINT},
                {GISTS_PUBLIC_ENDPOINT}
        };
    }
}

//    HttpClient client = HttpClients.createDefault();
//    HttpGet request = new HttpGet(PropertyReader.getProperty("bnmUrl"));
//    HttpResponse response = client.execute(request);


