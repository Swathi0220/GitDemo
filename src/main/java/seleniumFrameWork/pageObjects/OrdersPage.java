package seleniumFrameWork.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public boolean verifyOrderDisplay(String productName)
	{
		 boolean match = productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
		 return match;
	}
}
