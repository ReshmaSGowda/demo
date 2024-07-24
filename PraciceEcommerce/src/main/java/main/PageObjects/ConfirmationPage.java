package main.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.AbstractComponent.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationText;
	
	public String isOrderPlaced()
	{
		String finaltext=confirmationText.getText();
		return finaltext;
	}
	
	
	

}
