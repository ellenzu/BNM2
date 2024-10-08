package github;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.BaseClass;
import currency.PropertyReader;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Instant;

public class CreateDeleteRepoTest extends BaseClass {

    int currentTime;

    @BeforeTest
    public void getCurrentTime() throws Exception {
        currentTime = (int) Instant.now().getEpochSecond();
    }

    @Test
    public void createRepo() throws Exception {
//        HttpPost request = new HttpPost(PropertyReader.getProperty("baseUrl") + PropertyReader.getProperty("user_repos"));
//        request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer" + PropertyReader.getProperty("token"));
        Repo requestPayload = new Repo();
        requestPayload.setName("testrepo");
        requestPayload.setDescription("Repository created");

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload);

//        String json = "{\"name\": \"generic-repo-name-" + currentTime + "\", \"private\": true)";
        String baseUrl = PropertyReader.getProperty("baseUrl") + PropertyReader.getProperty("user_repos");
        String token = PropertyReader.getProperty("token");

        response = postRequest(baseUrl, payload, token);

        String responseString = EntityUtils.toString(response.getEntity());
//        log.info("response: " + responseString);

        Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
        Assert.assertEquals("testrepo", getResponseKeyValue(responseString, "name"));

    }
}
