package TestNG;

import org.testng.annotations.Test;

public class TestCase2 extends BaseTest {

    @Test(groups = "smoke")
    public void testAddToCart() {
        System.out.println("TestCase2: Executing test method - Add to Cart.");
    }
}
