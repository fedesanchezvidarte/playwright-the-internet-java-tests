package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

    @Test
    public void verifyHomePageTitle() {
        page.navigate("https://the-internet.herokuapp.com/");
        String title = page.title();
        Assert.assertEquals(title,
                "The Internet",
                "Page title does not match!"
        );
    }
}
