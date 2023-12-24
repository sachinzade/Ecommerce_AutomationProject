package com.utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.baseclass.BaseClass;


public class DriverUtility extends BaseClass{


	public static String getScreenshot(String name)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot/" + name + ".jpg";
		File dest = new File(path);
		
		try {
			FileUtils.copyFile(src, dest);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return path;

	}
	
}
