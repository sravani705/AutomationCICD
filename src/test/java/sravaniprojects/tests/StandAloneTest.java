package sravaniprojects.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import sravaniprojects.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//WebDriverManager.ChromeDriver().setup();
		
		String productname="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		LandingPage landingpage=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("sravani.chegu003@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ludwik@2017");
		driver.findElement(By.id("login")).click();
		List<WebElement> products=driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		
		WebElement prod= products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartitems=driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		
		Boolean match=cartitems.stream().anyMatch(cartitem-> cartitem.getText().equalsIgnoreCase(productname));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		WebElement element = wait.until(
		        ExpectedConditions.elementToBeClickable(
		                By.cssSelector(".btnn.action__submit.ng-star-inserted")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit.ng-star-inserted")));
		
		
		
		String Cfrmmsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());
		
		Assert.assertTrue(Cfrmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		
		
		

	}

}
