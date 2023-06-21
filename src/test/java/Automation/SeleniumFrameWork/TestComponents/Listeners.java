package Automation.SeleniumFrameWork.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumFrameWork.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // threadsafe is useful when u run parallel tests , each test wont interrupt other 
	@Override
	public void onTestStart(ITestResult result)
	{
		test =extent.createTest(result.getMethod().getMethodName());  // path is test case name. //result will get method for execution and method name which is testcase and testcase name 
		extentTest.set(test);  //whenever this is set , it will create a unique ID for every test so each test will have own ID and so wont give incorrect details.
 	}
	
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed"); 
	}
	
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		// test.log(Status.FAIL, "Test Failed"); - this will just say test failed 
		extentTest.get().fail(result.getThrowable());  // this will throw the error too for you
		
		// to take screenshot and attach it to your report // see lecture no.177 you will know how to get try catch block.
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {      // this print whatever exceptions we get , just print it
			e1.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}
	
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
	}
	
	
	@Override
	public void onStart(ITestContext context)
	{
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush(); // this is import as this will only make sure reports are generated and seen on screen. 
	}
	

}
