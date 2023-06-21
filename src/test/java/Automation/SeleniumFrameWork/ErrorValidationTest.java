package Automation.SeleniumFrameWork;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.SeleniumFrameWork.TestComponents.BaseTest;
import Automation.SeleniumFrameWork.TestComponents.Retry;
import seleniumFrameWork.pageObjects.CheckOutPage;
import seleniumFrameWork.pageObjects.ConfirmationPage;
import seleniumFrameWork.pageObjects.LoginPage;
import seleniumFrameWork.pageObjects.LoginandCheckOut;
import seleniumFrameWork.pageObjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	
		@Test(groups= {"errorHandling"},retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws IOException
		{
		
		String productName = "ZARA COAT 3";
		LoginPage.loginApplication("swathiswats@gmail.com", "Amsani801");	
		AssertJUnit.assertEquals(LoginPage.getErrorMsg(), "Incorrect email  password.");
   
       
	    }
		
		@Test
		public void ProductValidation() throws IOException
		{
		
		String productName = "ZARA COAT 3";
		LoginPage.loginApplication("swathiswats0@gmail.com", "Amsani@801");	
		ProductCatalogue ProductCatalogue = new ProductCatalogue(driver);
	    List<WebElement>products=ProductCatalogue.getProductList();
	    ProductCatalogue.addProductToCart(productName);
	    seleniumFrameWork.pageObjects.LoginandCheckOut LoginandCheckOut = new LoginandCheckOut(driver);
	    LoginandCheckOut.goToCart();
	    Boolean match = LoginandCheckOut.verifyProductDisplay("ZARA COAT 33");
        Assert.assertTrue(match);
	
        }

}