package imadidrissi.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import imadidrissi.TestComponents.BaseTest;
import imadidrissi.pageobjects.CartPage;
import imadidrissi.pageobjects.CheckoutPage;
import imadidrissi.pageobjects.ConfirmationPage;
import imadidrissi.pageobjects.LandingPage;
import imadidrissi.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest {
	public LandingPage landingPage;
	 public ProductCatalogue productCatalogue;
	 public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecomerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue=landingPage.loginApplication(username, password);
	}
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void chechout_submit_order(String productName)
	{
		CartPage cartPage =	productCatalogue.goToCartPage();
		   
		   Boolean match = cartPage.VerifyProductDisplay(productName);
		 
		  
		   Assert.assertTrue(match);
		   CheckoutPage checkoutPage = cartPage.goToCheckout();
		   
		   checkoutPage.selectCountry("india");
		   confirmationPage = checkoutPage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		  
		  Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		  driver.close();
	}
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
    	driver.close();
    }
}	