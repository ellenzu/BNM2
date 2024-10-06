package currency;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class BaseClass {

    protected static CloseableHttpClient client;
    protected static CloseableHttpResponse response;

    protected static final Logger log = Logger.getLogger(BaseClass.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeTest
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterTest
    public void cleanUp() throws IOException {
        if (client != null) {
            client.close();
        }
        if (response != null) {
            response.close();
        }
    }

    public static CloseableHttpResponse postRequest(String requestUri, String requestPayload, String token) throws Exception {
        PropertyConfigurator.configure("log4j.properties");
        log.info("Request URI: " + requestUri);
        log.info("Request Payload: " + requestPayload);

        HttpPost postRequest = new HttpPost(requestUri);
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("Authorization", "Bearer " + token);
        postRequest.setEntity(new StringEntity(requestPayload));

        return client.execute(postRequest);
    }

    public static CloseableHttpResponse getRequest(String requestUri) throws IOException {
        PropertyConfigurator.configure("log4j.properties");
        log.info("Request URI: " + requestUri);

        HttpGet getRequest = new HttpGet(requestUri);
        return client.execute(getRequest);
    }

    public static String getResponseKeyValue(String responseBody, String responseKey) throws Exception{
        JsonNode rootNode = objectMapper.readTree(responseBody);
        JsonNode keyNode = rootNode.path(responseKey);
        return keyNode.asText();
    }
}
