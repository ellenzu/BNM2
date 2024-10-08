package github;

import utils.BaseClass;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class Get200 extends BaseClass {

    public static String BASE_URL = "http://api.github.com";
    public static String RATE_LIMIT_ENDPOINT = "/rate_limit";
    public static String GISTS_PUBLIC_ENDPOINT = "/gists/public";

    @Test(dataProvider = "endpoint")
    public void firstTest(String endpoint) throws IOException {
        response = getRequest(BASE_URL + endpoint);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void secondTest() throws IOException {
        response = getRequest(BASE_URL + RATE_LIMIT_ENDPOINT);

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



