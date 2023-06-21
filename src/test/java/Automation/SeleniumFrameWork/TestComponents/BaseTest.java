package Automation.SeleniumFrameWork.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameWork.pageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage LoginPage;

	public WebDriver initializeDriver() throws IOException {
		// In java there is Properties class, but first create global properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"/Users/Mahi/eclipse-Seleniumworkspace/SeleniumFrameWork/src/main/java/seleniumFrameWork/resources/GlobalData.properties");
		prop.load(fis); // this is location of globalData properties file .
						// "/Users/Mahi/eclipse-Seleniumworkspace/SeleniumFrameWork" -
						// System.getProperty("user.dir)+ "this can be given instead" followed by "//src
		
		String browserName =	System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser"); // this step is used when you want to use global parameters using Maven , else comment this and use below step instead.
		//String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {    // if you want to run in headless mode you can do that by below steps at the end.
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		return driver;

	}
	
	

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports" + testcaseName + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "//reports" + testcaseName + ".png";
		}
	
	
	
	@BeforeMethod(alwaysRun = true) // this will run for all cases even when u dont specify group name if any. But
									// when you instead add this - //(groups= {"errorHandling"}) - it will run for
									// that group but will not run for any other groups.
	public LoginPage launchApplication() throws IOException

	{
		driver = initializeDriver();
		LoginPage = new LoginPage(driver);
		LoginPage.goTo();
		return LoginPage;

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
}


/*
 *        if (browserName.contains("Chrome")){
 *        ChromeOptions options = new ChromeOptions();
 *        WebDriver Manager.chromedriver().setup();
 *        if(browserName.contains("headless"){
 *        options.addArguments("headless");
 *        }
 *        driver = new ChromeDriver(options);
 *        
 *        // when we want browser to open in full screen , use this instead of window().maximize() as this might not work as expected sometimes
 *        driver.manage().window().setSize(new Dimention()1440,900));  - here the dinmentions depends on your system.
 *        
 *        // aslo u can scheudle jobs using jenkins. via build triggers > build periodically
 *        //notes in JENKINS SECTION - #185
 * 
 * 
 * 
 * 
 */
