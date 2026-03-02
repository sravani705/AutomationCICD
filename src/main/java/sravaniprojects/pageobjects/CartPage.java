package sravaniprojects.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import sravaniprojects.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;

	public  CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver; //initialization
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class='cartSection'] h3")
	private List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	private WebElement checkOutEle;	
	
	
    public Boolean VerifyProductDisplay(String productname) {
    	Boolean match=productTitles.stream().anyMatch(cartitem-> cartitem.getText().equalsIgnoreCase(productname));
    	return match;
    }
    
    public  CheckOutPage goTOCheckOut() {
    	checkOutEle.click();
    	return new CheckOutPage(driver);
    }
}
