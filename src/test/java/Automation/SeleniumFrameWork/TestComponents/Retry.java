package Automation.SeleniumFrameWork.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	
	int count = 0;
	int maxcount = 1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxcount)
		{
			count++;
			return true; // when its true the failed case will be re-run . until the condition is false. 
		}
		return false;
	}
	
	
   // so u should give this - retryAnalyzer=Retry.class for which ever test you want to rerun for which are failed. 
}
