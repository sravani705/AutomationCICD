package sravaniprojects.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sravaniprojects.AbstractComponents.AbstractComponents;

public class confirmpage extends AbstractComponents{
	
	WebDriver driver;

	public confirmpage(WebDriver driver) {
		super(driver);
		this.driver=driver; //initialization
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement Confirmationmsg;
	
	public String getConfirmationmsg() {
		return Confirmationmsg.getText();
	}

}
