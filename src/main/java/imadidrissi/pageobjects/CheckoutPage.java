package imadidrissi.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import imadidrissi.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "[placeholder='Select Country']")
	 private WebElement country;
	@FindBy(css = ".action__submit")
	 private WebElement submit;
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	private WebElement selectCountry;
	private By results = By.cssSelector(".ta-results");
    public void selectCountry(String countryName) {
    	Actions a = new 	Actions(driver);
    	a.sendKeys(country,countryName).build().perform();
    	waitForElementToAppear(By.cssSelector(".ta-results"));
    	selectCountry.click();
    }
    public ConfirmationPage submitOrder()
    {
    	Actions actions = new Actions(driver);
    	actions.moveToElement(submit).click().build().perform();
    	return new ConfirmationPage(driver);
    }

}
