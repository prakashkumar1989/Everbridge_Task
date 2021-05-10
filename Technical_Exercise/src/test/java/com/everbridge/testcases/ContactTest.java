package com.everbridge.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.everbridge.base.TestBase;
import com.everbridge.page.Contact;
import com.everbridge.utils.ReadXl;



public class ContactTest extends TestBase{
	Contact contact;
	String sheet = "Contact";
	public ContactTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		contact = new Contact();
	}
	
	@SuppressWarnings("deprecation")
	//We can make use of data provider mechanism to test it against multiple sets of data
	//@Test(dataProvider = dataSet)
	@Test
	public void sendMessage() throws InterruptedException {
		Assert.assertEquals("Contact us - My Store",contact.getTitle());
		contact.selectSubjectHeading(CONFIG.getProperty("Webmaster"));
		contact.enterEmailAddress(CONFIG.getProperty("email"));
		contact.enterOrderRef(CONFIG.getProperty("OrderId"));
		contact.SelectAndAttachFile();
		contact.enterMessage(CONFIG.getProperty("message"));
		contact.clickSend();
		Assert.assertEquals("Your message has been successfully sent to our team.", contact.sendSuccesMessage());
	}
	@DataProvider
	public Object[][] dataSet() throws InvalidFormatException, IOException {
		Object data[][] = ReadXl.ReadXlsx(sheet);
		return data;
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
