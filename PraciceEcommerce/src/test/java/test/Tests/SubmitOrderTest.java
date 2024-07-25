package test.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.PageObjects.CartPage;
import main.PageObjects.CheckOutPage;
import main.PageObjects.ConfirmationPage;
import main.PageObjects.OrdersPage;
import main.PageObjects.ProductCatalog;
import test.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
//once we trigger the test it will check parent classs first so before method will run first 
//this entire method will be be considered as one testcase
	 String productName="ZARA COAT 3";
   @Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException 
	{
	  
       String countryName="India";
       String orderConfirmationText="THANKYOU FOR THE ORDER.";
	
       ProductCatalog productCatalog=loginDetails.loginApplication(input.get("email"),input.get("password"));
		//add to cart
       List<WebElement> products= productCatalog.getProductsList();
		productCatalog.addToCart(input.get("product"));
		
		//navigate to cart and verify item added
		CartPage cartpage=productCatalog.gotoCart();
		Boolean isItemAdded=cartpage.verifyItemAdded(input.get("product"));
		Assert.assertTrue(isItemAdded);
		
	    //enter country and click on Placeorder
		CheckOutPage checkout=cartpage.goToCheckout();
		checkout.placeOrder(countryName);
		ConfirmationPage cp=checkout.submitOrder();
	
	    //Verify order got placed
		String finaltext=cp.isOrderPlaced();
		Assert.assertTrue(finaltext.equalsIgnoreCase(orderConfirmationText));
	    
	   
	}
   
   @Test(dependsOnMethods= {"submitOrder"})
   public void checkOrders()
   {
	   ProductCatalog productCatalog=loginDetails.loginApplication("reshma98@gmail.com","Rs1234!@#$");
	   OrdersPage orderspage=productCatalog.gotoOrders();
	   Assert.assertTrue(orderspage.checkOrder(productName));
	   
   }
   
   @DataProvider
   public Object[][] getData() throws IOException
   {
	   //return new Object [][] {{"reshma98@gmail.com","Rs1234!@#$","ZARA COAT 3"},{"resh@gmail.com","Iamqueen@000","ADIDAS ORIGINAL"}};

//	    HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "reshma98@gmail.com");
//		map.put("password", "Rs1234!@#$");
//		map.put("product", "ZARA COAT 3");
//		
//    	HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
	   
	   List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\test\\Data\\PurchasrOrder.json");
       return new Object[][] {{data.get(0)},{data.get(1)}};
   }
   
   


}
