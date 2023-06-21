package Automation.SeleniumFrameWork;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.SeleniumFrameWork.TestComponents.BaseTest;
import seleniumFrameWork.pageObjects.CheckOutPage;
import seleniumFrameWork.pageObjects.ConfirmationPage;
import seleniumFrameWork.pageObjects.LoginPage;
import seleniumFrameWork.pageObjects.LoginandCheckOut;
import seleniumFrameWork.pageObjects.OrdersPage;
import seleniumFrameWork.pageObjects.ProductCatalogue;

public class SubmitOrderTest2 extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })

	// public void submitOrder(String email, String password, String productName)
	// throws IOException - this is when u give data as sets in below dataprovider
	// method.

	public void submitOrder(HashMap<String, String> input) throws IOException {

		LoginPage.loginApplication(input.get("email"), input.get("password"));
		ProductCatalogue ProductCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(input.get("product"));

		seleniumFrameWork.pageObjects.LoginandCheckOut LoginandCheckOut = new LoginandCheckOut(driver);
		LoginandCheckOut.goToCart();
		//Boolean match = LoginandCheckOut.verifyProductDisplay(productName);
		//Assert.assertTrue(match);
		LoginandCheckOut.goToCheckout();
		CheckOutPage CheckOutPage = new CheckOutPage(driver);
		CheckOutPage.inputCreditInfo("5656", "Swathi Amsanipally", "rahulshettyacademy");
		CheckOutPage.selectCountry("India");
		ConfirmationPage ConfirmationPage = CheckOutPage.submitOrder();
		String confirmMsg = ConfirmationPage.getConfirmationMessage();
		AssertJUnit.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		LoginPage.loginApplication("swathiswats0@gmail.com", "Amsani@801");
		OrdersPage OrdersPage = new OrdersPage(driver);
		OrdersPage.orderHeader();
		Assert.assertTrue(OrdersPage.verifyOrderDisplay(productName));

	}
	
	
	

	@DataProvider
	    public Object[][] getData() throws IOException
	    {
	
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Automation//SeleniumFrameWork//Data//PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
	    }	
			
		
	
	
	
	
	
	
			/* Method type - 1
			@DataProvider
	     	public Object[][] getData()
		    {
			return new Object[][] {{"swathiswats0@gmail.com","Amsani@801","ZARA COAT 3"},{"swathiswats0@gmail.com","Amsani@801","ADIDAS ORIGINAL"}};
			}
			// this is one method else below is other method when there is lot of data with many values. you can use hashmap instead of sending data as sets  as when u have more data like 15 values or many
			*/
		   		
		    /* Method type - 2  ( we instead of manually hashmapping , will use json to pull the data and convert to hashmaps. 
	    	@DataProvider
		    public Object[][] getData()
		    {
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("email", "swathiswats0@gmail.com");
			map.put("password", "Amsani@801");
			map.put("product", "ZARA COAT 3");
			
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email", "swathiswats0@gmail.com");
			map1.put("password", "Amsani@801");
			map1.put("product", "ADIDAS ORIGINAL");
			
			return new Object[][] {{map},{map1}};
		}
		*/
			
}
