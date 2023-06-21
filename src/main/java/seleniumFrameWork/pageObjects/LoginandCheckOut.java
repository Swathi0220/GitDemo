package seleniumFrameWork.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LoginandCheckOut extends AbstractComponents{
	
	WebDriver driver;
	
	public LoginandCheckOut(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//PageFactory 
    @FindBy(css="button[routerlink*='cart']")
    WebElement cartPage;
    
    @FindBy(css="li[class='totalRow'] button")
    WebElement checkOut;
    
    @FindBy(css="div[class='cartSection'] h3")
    List<WebElement> cartProducts;
    
    
	public Boolean verifyProductDisplay(String productName)
	{
		 boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		 return match;
	}
	
	
	public void goToCart() // you can put this in abstractcomponents class also as this CartPage is commonly and frequently used.
	{
		cartPage.click();
	}
	
	
	public void goToCheckout()   
	{
		checkOut.click(); 
		
		
	}
	
	
	
}
