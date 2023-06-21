package seleniumFrameWork.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmailID = driver.findElement(By.id("userEmail"));
	
	//PageFactory 
	@FindBy(id="userEmail")
	WebElement userEmailID;
    
	@FindBy(css="input[placeholder='enter your passsword']")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")       // this is when incorrect login credentials are given, u see popup which disappears quickly
	WebElement errorMsg;        
	
	public ProductCatalogue loginApplication(String email, String pwd)

	{
		userEmailID.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		return null;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMsg()
	{
		return errorMsg.getText();
	}
}
