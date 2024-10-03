package github;

import currency.BaseClass;
import currency.PropertyReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class OptionTests extends BaseClass {

    @Test
    public void optionsTest() throws IOException {
        String expectedResponse = "GET, POST, PATCH, PUT, DELETE";
        String baseUrl = PropertyReader.getProperty("baseUrl");

        HttpOptions optionRequest = new HttpOptions(baseUrl);
        try (CloseableHttpResponse response = client.execute(optionRequest)) {
            String actualResponse = GitHubUtility.getHeader(response, "Allow");
            Assert.assertEquals(actualResponse, expectedResponse, "Expected methods do not match");
        }
    }
}
