package sravaniprojects.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sravaniprojects.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	WebDriver driver;

	public  CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver; //initialization
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	private WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectcountry;
	
	
	
	public void selectCountry(String Countryname) {
		
		Actions a=new Actions(driver);
		a.sendKeys(country, Countryname).build().perform();
		
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectcountry.click();
	}
	
	public confirmpage submitOrder() {
		waitForElementToBeClicable(submit);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
		//submit.click();
		return new confirmpage(driver);
	}
	
	
	
}
