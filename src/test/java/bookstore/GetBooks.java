package bookstore;

import currency.BaseClass;
import currency.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetBooks extends BaseClass {

    // Get a list of all books
    @Test
    public void retrieveBooks() throws IOException {

        String bookstoreBaseUrl = PropertyReader.getProperty("bookstore_baseUrl");
        String booklistEndpoint = PropertyReader.getProperty("retrieve_books_endpoint");
        response = getRequest(bookstoreBaseUrl + booklistEndpoint);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "The request failed");
        log.info("Request to retrieve books was successful. Status code: " + actualStatusCode);
    }
}
