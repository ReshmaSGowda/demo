package main.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.AbstractComponent.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderNamesList;

	public Boolean checkOrder(String productName)
	{
		Boolean match=orderNamesList.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
}
