package tests;

import org.testng.annotations.Test;
import pages.HomePage;


public class AddProductToCartWithPOPatternTest extends BaseTestCase {

    @Test(groups="smoke")
    public void testAddProductToCart(){
        HomePage homePage = new HomePage(driver);
        String aProduct = "Ruby on Rails Bag";
        String category = "Bags";
        homePage.navigateToLoginPage().login("spree@example.com", "spree123").
                addProductToCart(category, aProduct);

    }


}
