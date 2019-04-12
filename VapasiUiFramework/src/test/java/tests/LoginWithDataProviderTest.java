package tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

/**
 * Created by jaggu on 4/11/19.
 */
public class LoginWithDataProviderTest extends BaseTestCase {

    @Test(dataProvider="getUserNameAndPassword",groups="smoke")
    public void testLogin(String username , String password){

        driver.findElement(By.id("link-to-login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys(username);
        driver.findElement(By.id("spree_user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();
        assertTrue(driver.findElement(By.linkText("My Account")).isDisplayed());
        driver.findElement(By.linkText("Logout")).click();
    }

    @DataProvider(name="getUserNameAndPassword")
    public Object[][] getUserNameAndPassword(){
        return new Object[][]
                {
                        { "spree@example.com", "spree123" }
                };
    }


    @Test(dataProvider="getInvalidUserNameAndPassword")
    public void testInvalidLogin(String username , String password){
        driver.navigate().to("https://spree-vapasi.herokuapp.com");
        driver.findElement(By.id("link-to-login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys(username);
        driver.findElement(By.id("spree_user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();
        assertTrue(driver.findElement(By.className("alert-error")).isDisplayed());
    }

    @DataProvider(name="getInvalidUserNameAndPassword")
    public Object[][] getInvalidUserNameAndPassword(){
        return new Object[][]
                {
                        { "spree@example.com", "wrongpassword" },
                        {"wrongusername@example.com" , "spree123"}
                };
    }

}
