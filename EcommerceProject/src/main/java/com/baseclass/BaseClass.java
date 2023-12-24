package com.baseclass;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import com.pages.LogInPage;
import com.utilities.MethodsUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(listners.Listnrs.class)
public class BaseClass {

	public static WebDriver driver = null;
	public WebDriverWait wait;
	
	public static Logger log = Logger.getLogger("BaseClass");
	
	public static void intialization() throws Exception {
		String browser = MethodsUtility.readProperty("browser");
		log.info(" browser found " + browser);
		if (browser.equalsIgnoreCase("chrome")) {
			log.info("launching the chrome browser");
			// System.setProperty("webdriver.chrome.driver",
			// "D:/chromedriver.exe");
			 WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("chrome browser launched ");
		} // if ends

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:/geckodriver.exe");
			driver = new FirefoxDriver();
		} // if ends

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(MethodsUtility.readProperty("url"));
		log.info("url found");
	}

	public LogInPage loadlogInPage() {
		LogInPage logInPage = new LogInPage(driver);
		return logInPage;
	}

}
