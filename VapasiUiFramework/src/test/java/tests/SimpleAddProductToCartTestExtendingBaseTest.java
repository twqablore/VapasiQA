package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class SimpleAddProductToCartTestExtendingBaseTest extends BaseTestCase {

    @Test
    public void testAddProductToCart(){

        login("spree@example.com", "spree123");

        String aProduct = "Ruby on Rails Bag";
        addProductToCart("Bags", aProduct);
        assertTrue(isProductInCart(aProduct + "wohoo"), "Product " + aProduct + " not added in Cart");
    }

    private void addProductToCart(String category, String aProduct) {
        selectProductWithInACategory(category, aProduct);
        addToCart();
    }

    private boolean isProductInCart(String aProduct) {
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

    private void addToCart() {
        driver.findElement(By.id("add-to-cart-button")).click();
    }

    private void selectProductWithInACategory(String sCategory, String sProduct) {
        WebElement category = getCategory(sCategory);
        category.click();

        WebElement product = getProduct(sProduct);
        product.click();
    }

    private void login(String username, String password) {
        driver.navigate().to("https://spree-vapasi.herokuapp.com");
        driver.findElement(By.id("link-to-login")).click();
        driver.findElement(By.id("spree_user_email")).sendKeys(username);
        driver.findElement(By.id("spree_user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();
    }

    private WebElement getProduct(String product) {
        List<WebElement> links = driver.findElements(By.tagName("img"));
        System.out.println("Length is " +  links.size());
        for (Iterator<WebElement> iterator = links.iterator(); iterator.hasNext(); ) {
            WebElement link = iterator.next();
            System.out.println(link.getAttribute("alt"));
            if(link.getAttribute("alt").equals(product)) return link;
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
