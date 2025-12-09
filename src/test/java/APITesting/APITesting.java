package APITesting;

import Base.BaseFunctionAPI;
import com.microsoft.playwright.APIResponse;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import static org.testng.Assert.assertEquals;

public class APITesting extends BaseFunctionAPI {

    @Test
    void testAPIGetRequest() {
        String URL = "https://restful-booker.herokuapp.com/booking";
        APIResponse apiResponse = requestGet(URL);
        assertThat(apiResponse).isOK();
        int status = apiResponse.status();
        assertEquals(status, 200, "Status code is not 200 OK");
    }

}
