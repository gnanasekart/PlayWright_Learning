package TestNG;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {


    @BeforeSuite
    public void setUp() {
        System.out.println("BaseTest: Setting up before the test suite.");
    }

    @AfterSuite
    public void tearDown() {
        System.out.println("BaseTest: Tearing down after the test suite.");
    }
}
