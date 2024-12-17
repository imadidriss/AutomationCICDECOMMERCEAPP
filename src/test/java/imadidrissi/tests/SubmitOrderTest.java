package imadidrissi.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import imadidrissi.TestComponents.BaseTest;
import imadidrissi.pageobjects.CartPage;
import imadidrissi.pageobjects.CheckoutPage;
import imadidrissi.pageobjects.ConfirmationPage;
import imadidrissi.pageobjects.LandingPage;
import imadidrissi.pageobjects.OrderPage;
import imadidrissi.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	    @Test(dataProvider="getData", groups= {"Purchase"})
	    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	    {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue =landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
	    
		CartPage cartPage =	productCatalogue.goToCartPage();
	   
	   Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
	 
	  
	   Assert.assertTrue(match);
	   CheckoutPage checkoutPage = cartPage.goToCheckout();
	   
	   checkoutPage.selectCountry("india");
	  ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	  
	  
	
	  String confirmMessage = confirmationPage.verifyConfirmationMessage();
	  
	  Assert.assertTrue(confirmMessage.equalsIgnoreCase("TANKYOU FOR THE ORDER."));
	  
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest(){
		ProductCatalogue productCatalogue =landingPage.loginApplication("alexandreboireau171@gmail.com", "lkedfgh7B");
		OrderPage ordersPage =  productCatalogue.goToOrdersPage();
		
		ordersPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{ 
		List <HashMap<String,String>>    data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)},{data.get(1)}};
	}
		
	 

}
