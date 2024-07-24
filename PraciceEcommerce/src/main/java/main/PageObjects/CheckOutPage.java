package main.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.AbstractComponent.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectIndia;
	
	@FindBy(css="a.btnn")
	WebElement clickSubmit;
	
	By countryList=By.cssSelector(".list-group");
	
	public void placeOrder(String countryName)
	{
		Actions a= new Actions(driver);
	    a.sendKeys(selectCountry, countryName).build().perform();
	    waitForElementToAppear(countryList);
	    selectIndia.click();
	    
	}
	public ConfirmationPage submitOrder()
	{
		windowScrollDown();
	    waitForElementToClickable(clickSubmit);
	    clickSubmit.click();
	    ConfirmationPage cp=new ConfirmationPage(driver);
	    return cp;
	}
}
