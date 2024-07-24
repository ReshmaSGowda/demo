package main.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.AbstractComponent.AbstractComponents;

public class LoginPage extends AbstractComponents {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// this will take care of constructing driver object for what will send
												// from the other methods

	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));
	// PageFactory

	@FindBy(id ="userEmail")
	WebElement userEmail;

	@FindBy(id ="userPassword")
	WebElement passwordEle;

	@FindBy(id ="login")
	WebElement submit;

	@FindBy(css ="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalog loginApplication(String email, String password) 
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog=new ProductCatalog(driver); 
		return productCatalog;
		 
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");

	}

	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();

	}
}
