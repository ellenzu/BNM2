package github;

import utils.BaseClass;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;



public class Get401 extends BaseClass {

    @DataProvider
    private Object[][] endpointVal() {
        return new Object[][]{
                {"/user"},
                {"/user/followers"},
                {"/notifications"}
        };
    }

       @Test(dataProvider = "endpointVal")
       public void getStatus401(String endpoint) throws IOException{
      HttpGet request = new HttpGet("http://api.github.com" + endpoint);
              BaseClass.response = client.execute(request);
      Assert.assertEquals(response.getStatusLine().getStatusCode(), 401);
       }
    }
