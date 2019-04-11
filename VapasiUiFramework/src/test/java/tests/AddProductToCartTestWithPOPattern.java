package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class AddProductToCartTestWithPOPattern extends BaseTestCase {

    @Test
    public void testAddProductToCart(){

        HomePage homePage = new HomePage(driver);
        String aProduct = "Ruby on Rails Bag";
        String category = "Bags";
        driver.navigate().to("https://spree-vapasi.herokuapp.com");
        homePage.navigateToLoginPage().login("spree@example.com", "spree123").
                addProductToCart(category, aProduct);

    }


}
