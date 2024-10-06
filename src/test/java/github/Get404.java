package github;

import currency.BaseClass;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.*;


public class Get404 extends BaseClass {

    @DataProvider
    private Object[][] endpointVal() {
        return new Object[][]{
                {"/use"},
                {"/user/follower"},
                {"/notification"},
        };
    }

    @Test(dataProvider = "endpointVal")
    public void getStatus404(String endpoint) throws Exception {
        HttpGet request = new HttpGet("http://api.github.com" + endpoint);
        response = (client.execute(request));
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);
    }


}