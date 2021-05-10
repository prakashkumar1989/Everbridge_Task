package com.everbridge.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.everbridge.base.TestBase;
public class TestUtils {
	
	public static void screenShot() {
		File screenShot;
		
		File scrFile = ((TakesScreenshot)TestBase.driver).getScreenshotAs(OutputType.FILE);
		
		String faliureSS =System.getProperty("user.dir")+"\\src\\test\\java\\com\\everbridge\\screenshot\\" + "-" + System.currentTimeMillis()+ ".png";
		screenShot=new File(faliureSS);
		try {
			String filePath = screenShot.toString();
			FileUtils.copyFile(scrFile,	screenShot);
			
		} catch (IOException e) {
			 System.err.println("Unable to capture screenshot...");
			e.printStackTrace();
		}

	}
}
