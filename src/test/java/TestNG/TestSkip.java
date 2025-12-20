package TestNG;

import org.testng.annotations.Test;

public class TestSkip extends BaseTest{

    @Test
    public void testSkip(){
        System.out.println("TestSkip: Executing test method - testSkip.");
        // Simulating a skipped test
        throw new org.testng.SkipException("Skipping this test intentionally.");
    }
}
