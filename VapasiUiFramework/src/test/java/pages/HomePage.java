package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jaggu on 4/11/19.
 */
public class HomePage {

    @FindBy(id = "link-to-login")
    private WebElement login_link;


    private WebDriver webdriver;

    public HomePage(WebDriver webdriver){
        this.webdriver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public LoginPage navigateToLoginPage(){
        login_link.click();
        return new LoginPage(webdriver);
    }
}
