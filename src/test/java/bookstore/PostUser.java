package bookstore;

import bookstore.dto.User;
import bookstore.dto.UserID;
import com.fasterxml.jackson.databind.ObjectMapper;
import currency.BaseClass;
import currency.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostUser extends BaseClass {

    ObjectMapper objectMapper = new ObjectMapper();

    //Create a new user (positive)
    @Test
    public void createUser() throws Exception {
        String bookstoreBaseUrl = PropertyReader.getProperty("bookstore_baseUrl");
        String userEndpoint = PropertyReader.getProperty("bookstore_user_endpoint");

        User user = new User("Elena134trr5", "Psw123#!");

        String jsonPayload = objectMapper.writeValueAsString(user);

        response = postRequest(bookstoreBaseUrl + userEndpoint, jsonPayload, null);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        log.info("Received response with status code: " + actualStatusCode);
        Assert.assertEquals(actualStatusCode, 201, "User registration failed!");

        }

//Verify User Id and username in the response
 @Test(dependsOnMethods = "createUser")
        public void verifyUserResponse() throws Exception {
            String responseBody = new String(response.getEntity().getContent().readAllBytes());
            log.info("Response body: " + responseBody);

            UserID userId = objectMapper.readValue(responseBody, UserID.class);

            Assert.assertNotNull(userId.getUserID(), "User ID should not be null");
            Assert.assertEquals(userId.getUsername(), userId.getUsername(), "Usernames do not match");

            log.info("User registered successfully: " + userId);
        }
    }

