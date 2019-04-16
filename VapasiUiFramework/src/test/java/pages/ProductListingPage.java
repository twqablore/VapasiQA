package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by jaggu on 4/11/19.
 */
public class ProductListingPage {

    private WebDriver driver;

    @FindBy(id = "add-to-cart-button")
    private WebElement add_to_cart_button;

    public ProductListingPage(WebDriver webdriver) {
        this.driver = webdriver;
        PageFactory.initElements(webdriver, this);
    }

    public ShoppingCartPage addProductToCart(String category, String aProduct) {
        selectProductWithInACategory(category, aProduct);
        addToCart();
        return new ShoppingCartPage(driver);
    }

    private void addToCart() {
        add_to_cart_button.click();
    }

    private void selectProductWithInACategory(String sCategory, String sProduct) {
        WebElement category = getCategory(sCategory);
        category.click();

        WebElement product = getProduct(sProduct);
        product.click();
    }

    private WebElement getProduct(String productToBeAdded) {
        List<WebElement> products = driver.findElements(By.tagName("img"));
        System.out.println("Length is " +  products.size());
        for (Iterator<WebElement> iterator = products.iterator(); iterator.hasNext(); ) {
            WebElement product = iterator.next();
            if(product.getAttribute("alt").equals(productToBeAdded)) return product;
        }
        return null;
    }

    private WebElement getCategory(String categoryToBeSelected) {
        List<WebElement> categories = driver.findElements(By.className("list-group-item"));
        for (Iterator<WebElement> iterator = categories.iterator(); iterator.hasNext(); ) {
            WebElement category = iterator.next();
            System.out.println(category.getText());
            if(category.getText().equals(categoryToBeSelected)) return category;
        }
        return null;
    }



}
