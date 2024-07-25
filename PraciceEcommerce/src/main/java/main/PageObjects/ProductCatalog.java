package main.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import dev.failsafe.internal.util.Assert;
import main.AbstractComponent.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);//this will take care of constructing driver object for what will send from the other methods
		
	}
	
//List<WebElement> list=driver.findElements(By.cssSelector(".mb-3"));;//below Pagefactory concept will replace this 
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner ;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(productsBy);
		return products;
		
	}
	public WebElement getProductByName(String productName)
	{
		WebElement prod=getProductsList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
		
	}
	public void addToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	
}
