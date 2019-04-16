package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.ShoppingCartPage;

import static org.testng.Assert.assertTrue;


public class AddProductToCartWithPOPatternTest extends BaseTestCase {

    @Test(groups="smoke")
    public void testAddProductToCart(){
        HomePage homePage = new HomePage(driver);
        String aProduct = "Ruby on Rails Bag";
        String category = "Bags";
        ShoppingCartPage shoppingCartPage = homePage.navigateToLoginPage().login("spree@example.com", "spree123").
                addProductToCart(category, aProduct);
        assertTrue(shoppingCartPage.isProductInCart(aProduct) , "Product " + aProduct + " not present in cart");

    }


}
