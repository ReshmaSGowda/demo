package main.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.AbstractComponent.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> cardProducts;
	
	@FindBy(css=".subtotal button")
	WebElement checkOut;
	
	public boolean verifyItemAdded(String productName)
	{
		boolean itemPresent=cardProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return itemPresent;
		
	}
	public CheckOutPage goToCheckout()
	{
		checkOut.click();
		CheckOutPage checkout=new CheckOutPage(driver);
		return checkout;
	}
	

}
