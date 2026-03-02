package sravaniprojects.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sravaniprojects.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;

	public  LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwrd;
	
	@FindBy(id="login")
	WebElement submit;
	
	//ng-tns-c4-18 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	@FindBy(css="[class*='flyInOut']")
	WebElement getErrormsg;
	
	public ProductCatalogue loginapplication(String email, String password) {
		userEmail.sendKeys(email);
		passwrd.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErroronlogin() {
		waitForWebElementToAppear(getErrormsg);
		return getErrormsg.getText();
	}

	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	

	
	
}
