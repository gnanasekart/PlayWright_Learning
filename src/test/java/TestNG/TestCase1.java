package TestNG;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase1 extends BaseTest{

    @BeforeTest
    public void createDBConnection() {
        System.out.println("TestCase1: Creating database connection before tests.");
    }

    @AfterTest
    public void closeDBConnection() {
        System.out.println("TestCase1: Closing database connection after tests.");
    }

    @BeforeMethod
    public void launchBrowser() {
        System.out.println("TestCase1: Launching browser before each test method.");
    }

    @AfterTest
    public void closeBrowser() {
        System.out.println("TestCase1: Closing browser after tests.");
    }

    @Test(priority = 1, groups = "functional")
    public void testUserRegistration() {
        System.out.println("TestCase1: Executing test method 1.");
    }

    @Test(priority = 2, groups = "functional")
    public void testUserLogin() {
        System.out.println("TestCase1: Executing test method 2.");
    }
}
