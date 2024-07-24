package test.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import main.PageObjects.CartPage;
import main.PageObjects.CheckOutPage;
import main.PageObjects.ConfirmationPage;
import main.PageObjects.LoginPage;
import main.PageObjects.ProductCatalog;
import test.TestComponents.BaseTest;

public class StepDefinitionsImp extends BaseTest {
	public LoginPage logingpage;
	public ProductCatalog productCatalog;
	public ConfirmationPage cp;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		logingpage=launchApplication();
	}
	@Given("^Logged in with username(.+) and password(.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		 productCatalog=loginDetails.loginApplication(username,password);
		
	}
	@When("^I add product(.+) to cart$")
	public void add_product_from_cart_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products= productCatalog.getProductsList();
		productCatalog.addToCart(productName);
	}
	 @When("^checkout(.+)and submit the order$")
	 public void checkout_and_submit_the_order(String productName)
	 {
		 CartPage cartpage=productCatalog.gotoCart();
			Boolean isItemAdded=cartpage.verifyItemAdded(productName);
			Assert.assertTrue(isItemAdded);
			CheckOutPage checkout=cartpage.goToCheckout();
			checkout.placeOrder("India");
			cp=checkout.submitOrder();
		 
	 }
	 @Then("{string} message is displayed on confirmation page")
	 public void message_is_displayed_on_confirmation(String string)
	 {
		 String finaltext=cp.isOrderPlaced();
		 Assert.assertTrue(finaltext.equalsIgnoreCase(string));
		 driver.close();
	 }
	 
	

}
