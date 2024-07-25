package main.AbstractComponent;

import java.awt.Window;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.PageObjects.CartPage;
import main.PageObjects.OrdersPage;

public class AbstractComponents {
	WebDriver driver;
	public  AbstractComponents(WebDriver driver)
	{
		this.driver=driver;
	}
	
	@FindBy(css="button[routerlink*='/dashboard/cart']")
	WebElement clickCart;
	@FindBy(css="button[routerlink=\"/dashboard/myorders\"]")
	WebElement clickOrders;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		//as its waiting for 4s for hidden spinner and its not required hence going with below mwthod
			Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public void waitForElementToClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	public void windowScrollDown()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
	}
	public CartPage gotoCart()
	{
		clickCart.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage; 
	}
	
	public OrdersPage gotoOrders()
	{
		clickOrders.click();
		OrdersPage orderspage=new OrdersPage(driver);
		return orderspage; 
	}
	
}
