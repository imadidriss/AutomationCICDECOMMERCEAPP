package imadidrissi.tests;

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

import imadidrissi.pageobjects.LandingPage;

public class StandloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver","C:\\img\\msedgedriver.exe" );
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver= new EdgeDriver(options);
	    
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("alexandreboireau171@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("lkedfgh7B");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod	= products.stream().filter(product ->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	    prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	    driver.findElement(By.cssSelector("[routerlink*= 'cart']")).click();
	    List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	  Boolean match =  cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName)  );
	  Assert.assertTrue(match);
	  driver.findElement(By.cssSelector(".totalRow button")).click();
	  Actions a = new 	Actions(driver);
	  a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
	  WebElement element = driver.findElement(By.cssSelector(".action__submit"));
	 
	  Actions actions = new Actions(driver);
	  actions.moveToElement(element).click().build().perform();
	  driver.findElement(By.cssSelector(".action__submit")).click();
	
	  String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	  
	  Assert.assertTrue(confirmMessage.equalsIgnoreCase("TANKYOU FOR THE ORDER."));
	  
	}

}
