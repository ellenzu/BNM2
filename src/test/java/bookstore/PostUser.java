package bookstore;

import currency.BaseClass;
import currency.PropertyReader;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostUser extends BaseClass {

    //Create a new user (positive)
    @Test
    public void createUser() throws IOException {
        String bookstoreBaseUrl = PropertyReader.getProperty("bookstore_baseUrl");
        String userEndpoint = PropertyReader.getProperty("bookstore_user_endpoint");


        String jsonPayload = "{ \"userName\": \"Elena222222222\", \"password\": \"Psw123#!\"}";
        StringEntity entity = new StringEntity(jsonPayload);

        HttpPost request = new HttpPost(bookstoreBaseUrl + userEndpoint);
        request.setHeader("Content-Type", "application/json");
        request.setEntity(entity);

        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 201, "User registration failed!");
    }
}
