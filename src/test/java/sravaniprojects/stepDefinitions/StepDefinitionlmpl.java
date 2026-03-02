package sravaniprojects.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sravaniprojects.TestComponents.BaseTest;
import sravaniprojects.pageobjects.CartPage;
import sravaniprojects.pageobjects.CheckOutPage;
import sravaniprojects.pageobjects.LandingPage;
import sravaniprojects.pageobjects.ProductCatalogue;
import sravaniprojects.pageobjects.confirmpage;

public class StepDefinitionlmpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckOutPage checkoutpage;
	public confirmpage confirmPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		 landingpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingpage.loginapplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productname) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductslist();

		productCatalogue.addProductToCart(productname);
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productname) {
		cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productname);
		Assert.assertTrue(match);

		checkoutpage = cartPage.goTOCheckOut();
		checkoutpage.selectCountry("india");
		confirmPage = checkoutpage.submitOrder();

	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_on_ConfirmationPage(String string) {
		String Cfrmmsg = confirmPage.getConfirmationmsg();
		System.out.println(Cfrmmsg);

		Assert.assertTrue(Cfrmmsg.equalsIgnoreCase(string));
		driver.close();

	}
	
	@Then("{string} message is displayed on LoginPage")
	public void message_displayed_on_LoginPage(String string) {
		Assert.assertEquals(string, landingpage.getErroronlogin());
		driver.close();

	}
}
