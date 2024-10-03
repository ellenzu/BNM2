package bookstore;

import currency.BaseClass;
import currency.PropertyReader;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import org.apache.log4j.Logger;

public class GetBooks extends BaseClass {

    private static final Logger logger = Logger.getLogger(GetBooks.class.getName());

    // Get a list of all books
    @Test
    public void retrieveBooks() throws IOException {

        String bookstoreBaseUrl = PropertyReader.getProperty("bookstore_baseUrl");
        String booklistEndpoint = PropertyReader.getProperty("retrieve_books_endpoint");
        HttpGet request = new HttpGet(bookstoreBaseUrl + booklistEndpoint);
        response = client.execute(request);

        int actualStatusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "The request failed");
        logger.info("Request to retrieve books was successful. Status code: " + actualStatusCode);
    }
}
