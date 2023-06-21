package Automation.SeleniumFrameWork.StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Automation.SeleniumFrameWork.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameWork.pageObjects.CheckOutPage;
import seleniumFrameWork.pageObjects.ConfirmationPage;
import seleniumFrameWork.pageObjects.LoginPage;
import seleniumFrameWork.pageObjects.LoginandCheckOut;
import seleniumFrameWork.pageObjects.ProductCatalogue;

public class StepDefinationImplementation extends BaseTest {

	public LoginPage LoginPage;
	public ProductCatalogue ProductCatalogue;
	public CheckOutPage CheckOutPage;
	public ConfirmationPage ConfirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		LoginPage = launchApplication();
	}
	
	@Given("^Login with Username(.+) and password (.+)$")
	public void login_with_username_and_password(String username, String Password)
	{
		ProductCatalogue = LoginPage.loginApplication(username,Password);
	}
	
	@When("^I add the product (.+) from cart$")
	public void i_add_the_product_from_cart(String ProductName)
	{
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(ProductName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String ProductName)
	{
		seleniumFrameWork.pageObjects.LoginandCheckOut LoginandCheckOut = new LoginandCheckOut(driver);
		LoginandCheckOut.goToCart();
		//Boolean match = LoginandCheckOut.verifyProductDisplay(productName);
		//Assert.assertTrue(match);
		LoginandCheckOut.goToCheckout();
		CheckOutPage CheckOutPage = new CheckOutPage(driver);
		CheckOutPage.inputCreditInfo("5656", "Swathi Amsanipally", "rahulshettyacademy");
		CheckOutPage.selectCountry("India");
		ConfirmationPage ConfirmationPage = CheckOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_is_displayed_on_confirmationPage(String string)
	{
		String confirmMsg = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		
		// tidy gherkin plugin for chrome is useful for step definations, so add it to chrome.
		//start it and add the .feature code in there and check for step defination java code.
	}
	
	
}
