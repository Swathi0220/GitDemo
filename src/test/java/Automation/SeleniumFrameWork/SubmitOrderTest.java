package Automation.SeleniumFrameWork;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameWork.pageObjects.CheckOutPage;
import seleniumFrameWork.pageObjects.ConfirmationPage;
import seleniumFrameWork.pageObjects.LoginPage;
import seleniumFrameWork.pageObjects.LoginandCheckOut;
import seleniumFrameWork.pageObjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		String productName = "ZARA COAT 3";
		
		LoginPage LoginPage = new LoginPage(driver);
		LoginPage.goTo();
		LoginPage.loginApplication("swathiswats0@gmail.com", "Amsani@801");
		
		ProductCatalogue ProductCatalogue = new ProductCatalogue(driver);
	    List<WebElement>products=ProductCatalogue.getProductList(); // instead of creating new object for each class , you can instead create that in the pom classes only.
	   // ProductCatalogue onject can be created in LoginPage class since we know that after LoginPage next page it goes to ProductCatalogue
	    ProductCatalogue.addProductToCart(productName);
		
	    seleniumFrameWork.pageObjects.LoginandCheckOut LoginandCheckOut = new LoginandCheckOut(driver);
	    LoginandCheckOut.goToCart();
	    Boolean match = LoginandCheckOut.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        LoginandCheckOut.goToCheckout();
        CheckOutPage CheckOutPage = new CheckOutPage(driver);
        CheckOutPage.inputCreditInfo("5656", "Swathi Amsanipally", "rahulshettyacademy");
	    CheckOutPage.selectCountry("India");
        ConfirmationPage ConfirmationPage = CheckOutPage.submitOrder();
        String confirmMsg=ConfirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER." );
        
        
       
       
       
        
     
       
	}

}
