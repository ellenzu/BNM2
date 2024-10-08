package bookstore;

import bookstore.dto.Isbn;
import bookstore.dto.BookObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.BaseClass;
import currency.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class PostBook extends BaseClass {

    //Check user with non-valid token cannot create a new book
    @Test
    public void createBook() throws Exception {
        String bookstoreBaseUrl = PropertyReader.getProperty("bookstore_baseUrl");
        String booklistEndpoint = PropertyReader.getProperty("retrieve_books_endpoint");
        String token = PropertyReader.getProperty("user_token");

        Isbn isbn = new Isbn("123455596fjd03dkiidkd");
        BookObject book = new BookObject("f430bfdf-2b6b-49f7-8e38-b5be71cca713", Collections.singletonList(isbn));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(book);

        response = postRequest(bookstoreBaseUrl + booklistEndpoint, jsonPayload, token);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        log.info("Request URI: " + bookstoreBaseUrl + booklistEndpoint);
        log.info("Request Payload: " + jsonPayload);
        log.info("Token: " + token);
        Assert.assertEquals(actualStatusCode, 401);
    }
}
