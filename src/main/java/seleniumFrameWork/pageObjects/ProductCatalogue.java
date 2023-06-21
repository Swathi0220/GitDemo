package seleniumFrameWork.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css="div[class*='col-lg-4']")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productList = By.cssSelector("div[class*='col-lg-4']");
	By addToCart = By.cssSelector("button[class*='w-10'] i[class*='fa-shopping-cart']");
	By toastMessage = By.cssSelector("#toast-container");
	
	public  List<WebElement> getProductList()

	{
		waitForElementToAppear(productList);
		return products;
	}
	
	public WebElement getproductByName(String productName)
	{
		 WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	     return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getproductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
