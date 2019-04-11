package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    private final WebDriver webdriver;

    @FindBy(id = "link-to-login")
    private WebElement login_link;

    @FindBy(id = "spree_user_email")
    private WebElement email;

    @FindBy(id = "spree_user_password")
    private WebElement password;

    @FindBy(name = "commit")
    private WebElement submit;

   public LoginPage(WebDriver webdriver){
       this.webdriver = webdriver;
       PageFactory.initElements(webdriver, this);

   }

   public ProductListingPage login(String userame , String password){
       login_link.click();
       email.sendKeys(userame);
       this.password.sendKeys(password);
       submit.click();
       return new ProductListingPage(webdriver);

   }

}


