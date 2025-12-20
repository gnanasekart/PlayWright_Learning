package TestNG;

import org.testng.annotations.Test;

public class TestFailure extends BaseTest{

    @Test
    public void doLogin() {
        System.out.println("TestFailure: Executing test method - doLogin.");
        // Simulating a test failure
        assert false : "Simulated failure in doLogin test.";
    }
}
