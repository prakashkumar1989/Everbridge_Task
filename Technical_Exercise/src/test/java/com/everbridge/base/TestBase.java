package com.everbridge.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.everbridge.utils.WebEventListener;




public class TestBase {
	static Logger logger = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver = null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static EventFiringWebDriver eDriver;
	public static WebEventListener eListener;
	
	public TestBase(){
		logger.info("in TestBase.java");
		if(driver==null){
			CONFIG = new Properties();
			OR = new Properties();
			try{
				//CONFIG
				FileInputStream fsConfig = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\everbridge\\config\\config.properties");
				CONFIG.load(fsConfig);
				
				//OR
				FileInputStream fsOR = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\everbridge\\config\\OR.properties");
				OR.load(fsOR);
			} catch (FileNotFoundException fnf){
				System.out.println("Encountered error in reading file");
				fnf.printStackTrace();
				return;
			}
			catch (Exception e){
				System.out.println("Encountered error in reading file");
				e.printStackTrace();
				return;
			}
			
			
		}
		return;
	}
	
	//initialization
	public void initialization(){
		String browserName = CONFIG.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			logger.info("Starting driver " + driver.getClass().getName());
		}
		
		eDriver = new EventFiringWebDriver(driver);
		eListener = new WebEventListener();
		eDriver.register(eListener);
		driver = eDriver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(CONFIG.getProperty("url"));
		logger.info("Opened URL " + CONFIG.getProperty("url"));
	}

//	//Click
//	public void click (String xpathKey){
//		try{
//		driver.findElement(By.xpath(OR.getProperty(xpathKey))).click();
//		}catch (Exception e){
//			//report error
//			logger.error("In click "+e);
//			e.printStackTrace();
//		}
//		
//	}
//	//Input
//	public void input (String xpathKey,String text){
//		try{
//		driver.findElement(By.xpath(OR.getProperty(xpathKey))).sendKeys(text);
//		System.out.println(xpathKey);
//		}catch (Exception e){
//			//report error
//			logger.error("In input "+e);
//			e.printStackTrace();
//		}
//		
//	}
	
//	//Verification
//	public boolean isElementPresent(String xpath){
//		
//		try{
//			driver.findElement(By.xpath(OR.getProperty(xpath)));
//		}catch (Exception e){
//			return false;
//		}
//		return true;
//	}
}
