package sravaniprojects.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import sravaniprojects.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;

	public  OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver; //initialization
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productnames;
	
	
	
    public Boolean VerifyProductInOrders(String productname) {
    	Boolean match=productnames.stream().anyMatch(productnam-> productnam.getText().equalsIgnoreCase(productname));
    	return match;
    }
    
    
}
