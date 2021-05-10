package com.everbridge.page;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.everbridge.base.TestBase;

 
public class Contact extends TestBase{

	static Logger logger = Logger.getLogger(Contact.class.getName());
	@FindBy(xpath = "//*[@id=\"center_column\"]/form/fieldset/h3")
    private WebElement send_a_message;
	@FindBy(xpath = "//*[@id=\"id_contact\"]")
    private WebElement subject_heading;
	@FindBy(xpath = "//*[@id=\"email\"]")
    private WebElement email_address;
	@FindBy(xpath = "//*[@id=\"id_order\"]")
    private WebElement order_ref;
	@FindBy(xpath = "//*[@id=\"fileUpload\"]")
    private WebElement Attach_file;
	@FindBy(xpath = "//*[@id=\"message\"]")
    private WebElement message;
	@FindBy(xpath = "//*[@id=\"submitMessage\"]")
    private WebElement send;
	@FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement succes_Message;
	
	public Contact() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public WebElement getSendAMessage(){
        return send_a_message;
    }
	public WebElement getSubjectHeading(){
        return subject_heading;
    }
	public WebElement getEmailAddress(){
        return email_address;
    }
	public WebElement getOrderRef(){
        return order_ref;
    }
	public WebElement getAttachFile(){
        return Attach_file;
    }
	public WebElement getMessage(){
        return message;
    }
	public WebElement getSuccesMessage(){
        return succes_Message;
    }
	
	public void selectSubjectHeading(String heading) throws InterruptedException {

		Select heading_Selection = new Select(subject_heading);
		heading_Selection.selectByVisibleText(heading);
		
	}
	
	public void enterEmailAddress(String email) {
		email_address.sendKeys(email);
	}
	
	public void enterOrderRef(String orderRef) {
		order_ref.sendKeys(orderRef);
	}
	
	public void SelectAndAttachFile() {
		System.out.println("SelectAndAttachFile");
		//Attach_file.click();
		System.out.println("SelectAndAttachFile");
		Attach_file.sendKeys(System.getProperty("user.dir")+"\\src\\test\\java\\com\\everbridge\\testdata\\target.jpg");
	}
	
	public void enterMessage(String msg) {
		message.sendKeys(msg);
	}
	
	public void clickSend() {
		send.click();
	}
	
	public String sendSuccesMessage() {
		return succes_Message.getText();
	}
	
}
