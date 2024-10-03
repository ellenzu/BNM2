package github;

import currency.BaseClass;
import currency.PropertyReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Properties;


public class TestResponseHeaders extends BaseClass {
    String baseUrl = PropertyReader.getProperty("baseUrl");

        @Test
        public void verifyServerHeader() throws IOException {

            HttpGet request = new HttpGet(baseUrl);
            CloseableHttpResponse response = client.execute(request);
            String headerValue = GitHubUtility.getHeader(response,"Server");
            Assert.assertEquals(headerValue, "github.com");
            response.close();
        }
        @Test
        public void headerIsPresent() throws IOException {
            HttpGet request = new HttpGet(baseUrl);
            CloseableHttpResponse response = client.execute(request);
            Boolean headerIsPresent = GitHubUtility.headerIsPresent(response, "Server");
            Assert.assertTrue(headerIsPresent, "Header is not present");

        }
    }
