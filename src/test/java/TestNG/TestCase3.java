package TestNG;

import org.testng.annotations.Test;

public class TestCase3 extends BaseTest{

    @Test(priority = 1, groups = {"smoke", "functional"})
    public void testUserRegistration() {
        System.out.println("TestCase3: Executing test method 1.");
    }

    @Test(priority = 2, dependsOnMethods = "testUserRegistration", groups = {"functional", "smoke"})
    public void testUserLogin() {
        System.out.println("TestCase3: Executing test method 2.");
    }

    @Test(priority = 3, dependsOnMethods = "testUserRegistration", alwaysRun = true, groups = {"functional", "smoke"})
    public void testPasswordReset() {
        System.out.println("TestCase3: Executing test method 3.");
    }

    @Test(priority = 4, groups = "bvt")
    public void fourthTest() {
        System.out.println("TestCase3: Executing test method 4.");
    }
}
