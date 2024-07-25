package test.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.TestComponents.BaseTest;
import test.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {
//once we trigger the test it will check parent classs first so before method will run first 
//this entire method will be be considered as one testcase
   //@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)//we have to uselike this if we want to rerun any testcase
	@Test(groups= {"ErrorHandling"})
	public void errorValidation() throws InterruptedException, IOException 
	{
	   
       loginDetails.loginApplication("reshma98@gmail.com","s1234!@#$");
       Assert.assertEquals("Incorrect email or password.", loginDetails.getErrorMessage());
  

		
		
	    
	   
	}


}
