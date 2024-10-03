package bookstore;

import currency.BaseClass;
import currency.PropertyReader;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PostBook extends BaseClass {

    //Check user with non-valid token cannot create a new book
    @Test
    public void createBook() throws IOException {
        String bookstoreBaseUrl = PropertyReader.getProperty("bookstore_baseUrl");
        String booklistEndpoint = PropertyReader.getProperty("retrieve_books_endpoint");
        String token = PropertyReader.getProperty("user_token");
        String jsonPayload = "{ \"userId\": \"f430bfdf-2b6b-49f7-8e38-b5be71cca713\", \"collectionOfIsbns\": [{ \"isbn\": \"123455596fjd03dkdkd\" }] }";
        StringEntity entity = new StringEntity(jsonPayload);

        HttpPost request = new HttpPost(bookstoreBaseUrl + booklistEndpoint);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", "Bearer " + token);
        request.setEntity(entity);
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 401, "The book was created");
    }
}
