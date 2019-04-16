package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;

public class ShoppingCartPage {


    private final WebDriver driver;

   public ShoppingCartPage(WebDriver webdriver){
       this.driver = webdriver;
       PageFactory.initElements(webdriver, this);

   }

    public boolean isProductInCart(String aProduct) {
        List<WebElement> lineItems = driver.findElement(By.id("cart-detail")).findElements(By.className("line-item"));
        System.out.println("No if line items is " +  lineItems.size());
        for (Iterator<WebElement> iterator = lineItems.iterator(); iterator.hasNext(); ) {
            WebElement item = iterator.next();
            String product = item.findElement(By.tagName("img")).getAttribute("alt");
            if(product.equals(aProduct)) {
                System.out.println("Product in cart ");
                return true;
            }
        }
        return false;

    }

}


