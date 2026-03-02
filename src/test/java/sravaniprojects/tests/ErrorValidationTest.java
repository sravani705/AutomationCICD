package sravaniprojects.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import sravaniprojects.TestComponents.BaseTest;
import sravaniprojects.TestComponents.Retry;
import sravaniprojects.pageobjects.CartPage;
import sravaniprojects.pageobjects.CheckOutPage;
import sravaniprojects.pageobjects.LandingPage;
import sravaniprojects.pageobjects.ProductCatalogue;
import sravaniprojects.pageobjects.confirmpage;

public class ErrorValidationTest  extends BaseTest{
	
	String productname="ZARA COAT 3";
	//this is just change

	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//WebDriverManager.ChromeDriver().setup();
		
			
		landingpage.loginapplication("sravani.chegu003@gmail.com", "Ludwik2017");
		
		System.out.println(landingpage.getErroronlogin());
		
		Assert.assertEquals("Incorrect email or password.", landingpage.getErroronlogin());
		
	}
	
	@Test
	public void ProductOrderCheck() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//WebDriverManager.ChromeDriver().setup();			
		
		ProductCatalogue productCatalogue=landingpage.loginapplication("sravani.chegu003@gmail.com", "Ludwik@2017");
		
		List<WebElement> products=productCatalogue.getProductslist();
		
		productCatalogue.addProductToCart(productname);
		
		CartPage cartPage=productCatalogue.goToCartPage();
				
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
				
		Assert.assertFalse(match);
		
				

	}
}
