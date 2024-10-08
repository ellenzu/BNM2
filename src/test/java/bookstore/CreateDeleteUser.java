package bookstore;

import bookstore.dto.User;
import bookstore.dto.UserID;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.BaseClass;
import currency.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateDeleteUser extends BaseClass {

    ObjectMapper objectMapper = new ObjectMapper();
    String bookstoreUserUrl = PropertyReader.getProperty("bookstore_baseUrl") + PropertyReader.getProperty("bookstore_user_endpoint");
    String generateTokenUrl = PropertyReader.getProperty("bookstore_baseUrl") + PropertyReader.getProperty("bookstore_generateToken_endpoint");

    private static String token;
    private static String userId;


    //Create a new user (positive)
    @Test
    public void createUser() throws Exception {

        User user = new User(User.USERNAME, User.PASSWORD);

        //Serialization
        String jsonPayload = objectMapper.writeValueAsString(user);

        response = postRequest(bookstoreUserUrl, jsonPayload, null);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        log.info("Received response with status code: " + actualStatusCode);
        Assert.assertEquals(actualStatusCode, 201, "User registration failed!");

    }

    //Verify UserId and username in the response
    @Test(dependsOnMethods = "createUser")
    public void verifyUserResponse() throws IOException {
        String responseBody = new String(response.getEntity().getContent().readAllBytes());
        log.info("Response body: " + responseBody);

        //Deserialization
        UserID userIdResponse = objectMapper.readValue(responseBody, UserID.class);

        Assert.assertNotNull(userIdResponse.getUserID(), "User ID should not be null");
        Assert.assertEquals(userIdResponse.getUsername(), User.USERNAME, "Usernames do not match");

        userId = userIdResponse.getUserID();
        log.info("User registered successfully: " + userIdResponse);
    }

    @Test(dependsOnMethods = {"verifyUserResponse"})
    public void generateToken() throws Exception {
        User user = new User(User.USERNAME, User.PASSWORD);

        String jsonPayload = objectMapper.writeValueAsString(user);

        response = postRequest(generateTokenUrl, jsonPayload);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        log.info("Received response with status code: " + actualStatusCode);
        Assert.assertEquals(actualStatusCode, 200, "Token registration failed!");

        String contentResponse = new String(response.getEntity().getContent().readAllBytes());
        log.info("Response body: " + contentResponse);

        JsonNode jsonResponse = objectMapper.readTree(contentResponse);
        token = jsonResponse.get("token").asText();
        log.info("Token was saved");
    }

    @Test(dependsOnMethods = {"createUser", "verifyUserResponse", "generateToken"})
    public void deleteUser() throws Exception {
        response = deleteRequest(bookstoreUserUrl + "/" + userId, token);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 204, "Deletion failed!");
        log.info("Received response with status code: " + actualStatusCode);
    }
}

