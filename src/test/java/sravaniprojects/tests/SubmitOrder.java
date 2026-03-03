package sravaniprojects.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sravaniprojects.TestComponents.BaseTest;
import sravaniprojects.pageobjects.CartPage;
import sravaniprojects.pageobjects.CheckOutPage;
import sravaniprojects.pageobjects.LandingPage;
import sravaniprojects.pageobjects.OrderPage;
import sravaniprojects.pageobjects.ProductCatalogue;
import sravaniprojects.pageobjects.confirmpage;

public class SubmitOrder  extends BaseTest{

	String productname="ZARA COAT 3";			
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//WebDriverManager.ChromeDriver().setup();
		
		//ello
		ProductCatalogue productCatalogue=landingpage.loginapplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=productCatalogue.getProductslist();
		
		productCatalogue.addProductToCart(input.get("productname"));
		
		CartPage cartPage=productCatalogue.goToCartPage();
				
		Boolean match=cartPage.VerifyProductDisplay(input.get("productname"));
				
		Assert.assertTrue(match);
		
		CheckOutPage checkoutpage=cartPage.goTOCheckOut();
		checkoutpage.selectCountry("india");
		confirmpage confirmPage=checkoutpage.submitOrder();
				
		String Cfrmmsg=confirmPage.getConfirmationmsg();
		System.out.println(Cfrmmsg);
		
		Assert.assertTrue(Cfrmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
			

	}
	
	@Test(dependsOnMethods="submitOrder")
	public void OrderCheck()  {
		// TODO Auto-generated method stub
		
		//WebDriverManager.ChromeDriver().setup();
		
		ProductCatalogue productCatalogue=landingpage.loginapplication("sravani.chegu003@gmail.com", "Ludwik@2017");
		
		OrderPage orderpage=productCatalogue.goToOrderPage();
		
		Assert.assertTrue(orderpage.VerifyProductInOrders(productname));		
 
	}
	
	//Extent Reports - 
	
	
		@DataProvider
		public Object[][] getData() throws IOException
		{
			//SeleniumFrameWorkDesign\src\test\java\sravaniprojects\data\PurchaseOrder.json
			
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//sravaniprojects//data//PurchaseOrder.json");
			return new Object[][]  {{data.get(0)}, {data.get(1) } };
			
		}
		
		
		
	
//	@DataProvider
//	public Object[][] getData(){
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "sravani.chegu003@gmail.com");
//		map.put("password", "Ludwik@2017");
//		map.put("productname", "ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "loro@gmail.com");
//		map1.put("password", "Test@123");
//		map1.put("productname", "ADIDAS ORIGINAL");
//		
//		
//		return new Object[][] {{map},{map1}};
//	}
//	
//	@DataProvider
//	public   Object[][] getData() {
//		
//		return new Object[][] {{"sravani.chegu003@gmail.com","Ludwik@2017","ZARA COAT 3"},{"loro@gmail.com","Test@123","ADIDAS ORIGINAL"}};
//		
//	}

}
