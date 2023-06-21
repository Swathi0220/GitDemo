package Automation.SeleniumFrameWork;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameWork.pageObjects.LoginPage;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("swathiswats0@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='enter your passsword']")).sendKeys("Amsani@801");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String productName = "ZARA COAT 3";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='col-lg-4']")));
	   
		List<WebElement> products=driver.findElements(By.cssSelector("div[class*='col-lg-4']"));
	    WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	    prod.findElement(By.cssSelector("button[class*='w-10'] i[class*='fa-shopping-cart']")).click();
       
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating - this locator will be given by dev when you are not able to find as its for loading products , very quick 
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));  - you can use either this or below step
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
        
        List<WebElement> cartProducts =driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        
        driver.findElement(By.cssSelector("li[class='totalRow'] button")).click(); //checkout
      
        driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("5656"); //cvv
        driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("swathi Amsanipally"); //name on card
        driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("rahulshettyacademy"); // enter coupon 
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000); // used thread.sleep instead of explicit wait bcoz we dont know the locator here where loading is happening.
        
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class*='field'] p")))); // coupon applied text is seen
       /* driver.findElement(By.cssSelector("input[placeholder*='Select']")).sendKeys("can"); // pass country name in autosuggestive input box
        List<WebElement> countries=driver.findElements(By.cssSelector("button[class*='ta-item']"));
        use for loop else go with actions class as below 
        */
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Select']")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("button[class*='ta-item']")))); //wait until list of countries is visible
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click(); //clicking on India from list
        driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click(); //place order
        
        String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText(); // get Text and compare as below
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER." );
        
	}

}
