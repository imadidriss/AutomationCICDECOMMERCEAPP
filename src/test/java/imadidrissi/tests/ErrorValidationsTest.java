package imadidrissi.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import imadidrissi.TestComponents.BaseTest;
import imadidrissi.TestComponents.Retry;
import imadidrissi.pageobjects.CartPage;
import imadidrissi.pageobjects.CheckoutPage;
import imadidrissi.pageobjects.ConfirmationPage;
import imadidrissi.pageobjects.LandingPage;
import imadidrissi.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	    @Test(groups= {"ErrorHandling"}, retryAnalyzer= Retry.class)
	    public void LoginErrorValidation() throws IOException, InterruptedException
	    {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
		
		landingPage.loginApplication("alexandreboireau171@gmail.com", "lkedfgh7");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage() );
		
		
	}
	    @Test
	    public void ProductErrorValidation() throws IOException, InterruptedException
	    {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue =landingPage.loginApplication("alaouiadam387@gmail.com", "lkedfgh7B");
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	    
		CartPage cartPage =	productCatalogue.goToCartPage();
	   
	   Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
	 
	  
	   Assert.assertFalse(match);
	   
	  
	}   

}
