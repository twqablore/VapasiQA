package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by jaggu on 4/4/19.
 */
public class SimpleLoginTestExtendingBaseTest extends BaseTestCase {

    @Test
    public void testLogin(){
        driver.navigate().to("https://spree-vapasi.herokuapp.com");
        driver.findElement(By.id("link-to-login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys("spree@example.com");
        driver.findElement(By.id("spree_user_password")).sendKeys("spree123");
        driver.findElement(By.name("commit")).click();
        assertTrue(driver.findElement(By.linkText("My Account")).isDisplayed());
    }

}
