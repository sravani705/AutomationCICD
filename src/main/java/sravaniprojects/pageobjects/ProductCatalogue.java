package sravaniprojects.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import sravaniprojects.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;

	public  ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver; //initialization
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
	
	@FindBy(xpath="//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.xpath("//div[contains(@class,'mb-3')]");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastmessage=By.id("toast-container");
	
	
	public List<WebElement> getProductslist() {
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod= products.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastmessage);
		waitForElementToDisappear(spinner);
		
		
	}
	
	
}
