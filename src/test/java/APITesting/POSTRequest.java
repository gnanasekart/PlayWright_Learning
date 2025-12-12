package APITesting;

import Base.BaseFunctionAPI;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

public class POSTRequest extends BaseFunctionAPI {

    /*
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
    }
     */

    @Test
    void testAPIPOSTRequest() {
        String URL = "https://restful-booker.herokuapp.com/booking";

        Map<String, Object> map = new HashMap<>();
        map.put("firstname", "Gnana");
        map.put("lastname", "Sekar");
        map.put("totalprice", 111);
        map.put("depositpaid", true);

        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        map.put("bookingdates", bookingdates);
        map.put("additionalneeds", "Breakfast");

        var apiResponse = POSTRequest(URL, map);
        assertThat(apiResponse).isOK();
        int status = apiResponse.status();
        assertEquals(status, 200, "Status code is not 200 OK");
    }

}
