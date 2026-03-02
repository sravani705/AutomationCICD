package sravaniprojects.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sravaniprojects.pageobjects.CartPage;
import sravaniprojects.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement Cart;
	@FindBy(css="[routerlink*='myorders']")
	WebElement Orders;
	
	public void waitForElementToAppear(By findBy) {

	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	public void waitForWebElementToAppear(WebElement ele) {

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOf(ele));
		
		}
	
	public void waitForElementToBeClicable(WebElement ele) {

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		
		}
	
	public CartPage goToCartPage()
	{
		Cart.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage()
	{
		Orders.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(2000);

		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
		
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		
		}
}
