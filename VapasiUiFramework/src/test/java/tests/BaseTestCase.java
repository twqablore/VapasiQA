package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTestCase {

    WebDriver driver;
    ConfigReader config = new ConfigReader();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        setDriver();

        System.out.println("Set up driver");
        System.out.println("Driver is " +  driver);
        driver.navigate().to("https://spree-vapasi.herokuapp.com");
    }


    public  void setDriver() {

        String browser = config.readProperty("browser");
        if(browser.equalsIgnoreCase("chrome")) {
            String currentUsersWorkingDir = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", currentUsersWorkingDir + "/src/test/resources/chromedriver");
            driver =  new ChromeDriver();
        }
        if(browser.equalsIgnoreCase("firefox")){
            driver =  new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @AfterMethod(alwaysRun = true)
    public void captureScreenshotIfFailed(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result);
        }

    }

    private void takeScreenshot(ITestResult result) {

        System.out.println("Taking screenshot");
        String currentUsersWorkingDir = System.getProperty("user.dir");
        String resultsDir = currentUsersWorkingDir+ "/src/test/snapshot";
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(resultsDir + "/" + result.getName() + ".png"));
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());

        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        System.out.println("Tear down driver");
        driver.close();
        driver.quit();

    }


}
