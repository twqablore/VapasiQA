package tests;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SimpleLoginTestWithSnapshotOfTestFailure {

    WebDriver driver;


    String currentUsersWorkingDir = System.getProperty("user.dir");
    private String resultsDir;

    @BeforeTest
    public void setUp(ITestContext context){

        System.out.println("Dir is " + currentUsersWorkingDir);
        System.setProperty("webdriver.chrome.driver",currentUsersWorkingDir+"/src/test/resources/chromedriver");
        driver = new ChromeDriver();
        resultsDir = currentUsersWorkingDir+ "/src/test/snapshot";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    public void testLogin(){
        driver.navigate().to("https://spree-vapasi.herokuapp.com");
        driver.findElement(By.id("link-to-login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys("spree@example.com");
        driver.findElement(By.id("spree_user_password")).sendKeys("spree123");
        driver.findElement(By.name("commit")).click();
    }


    @AfterMethod
    public void tearDown(ITestResult result){

        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result);
        }

        driver.close();
        driver.quit();

    }

    private void takeScreenshot(ITestResult result) {

        System.out.println("Taking screenshot");
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(resultsDir + "/" + result.getName() + ".png"));
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());

        }
    }



}
