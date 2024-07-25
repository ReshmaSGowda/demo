package test.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.PageObjects.LoginPage;

public class BaseTest 
{
	public WebDriver driver;
	public LoginPage loginDetails;
	public WebDriver initializeDriver() throws IOException
	{
		
	//Prperties class- Parent class
	
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//main//Resources//GlobalData.properties");
	prop.load(fis);
	String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	//String browserName=prop.getProperty("browser");

	if (browserName.contains("chrome")) {
		ChromeOptions options=new ChromeOptions();
		//invoking chrome driver
		WebDriverManager.chromedriver().setup();
		if (browserName.contains("headless")) {
		options.addArguments("headless");	
		}
		 driver=new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));

	} else if (browserName.equalsIgnoreCase("firefox")) {
		//WebDriverManager.geckodriver().setup();
		System.setProperty("WebDriver.gecko.driver","C:\\Users\\RS097065\\geckodriver");
		 driver=new FirefoxDriver();
		// Firefox
	} else if (browserName.equalsIgnoreCase("edge")) {
		// Edge
		System.setProperty("WebDriver.edge.driver","/Users/rs097065//documents//geckodriver");
		 driver=new EdgeDriver();	
	}

	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicit wait
	driver.manage().window().maximize();
	return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Datbind
	
	  ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	//{map, map}
	}
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	   {
		   TakesScreenshot tc=(TakesScreenshot)driver;
		   File src=tc.getScreenshotAs(OutputType.FILE);
		   File file=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		   FileUtils.copyFile(src, file);
		   return System.getProperty("user.dir")+"//reports//"+testcaseName+ ".png";
	   }
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		loginDetails= new LoginPage(driver);
		loginDetails.goTo();
		return loginDetails;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
}
