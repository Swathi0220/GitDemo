package seleniumFrameWork.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//PageFactory 
    @FindBy(xpath="(//input[@type='text'])[2]")
    WebElement cvv;
    
    @FindBy(xpath="(//input[@type='text'])[3]")
    WebElement nameOnCard;
    
    @FindBy(xpath="(//input[@type='text'])[4]")
    WebElement couponValue;
    
    @FindBy(xpath="//button[@type='submit']")
    WebElement coupon;
    
    @FindBy(css="div[class*='field'] p")
    WebElement loadCouponMsg;
    
    @FindBy(css="input[placeholder*='Select']")  
    WebElement  country;
    
    @FindBy(xpath="(//button[@type='button'])[2]")  
    WebElement selectCountry;
    
    @FindBy(css=".btnn.action__submit.ng-star-inserted")
    WebElement submit;
    
  
    By countriesList = By.cssSelector("button[class*='ta-item']");
    
    
    public void inputCreditInfo(String cvvnum, String name,String code)
    {
    	cvv.sendKeys(cvvnum);
    	nameOnCard.sendKeys(name);
    	couponValue.sendKeys(code);
    	coupon.click();
    	waitForCouponMsg(loadCouponMsg);
    }
     
    public void selectCountry(String countryName)
    {
    	Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(countriesList);
        selectCountry.click();
        
    }
    
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
        
	}
}
