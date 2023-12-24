package com.pages;

import java.util.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.MethodsUtility;

public class CatlogPage {

	WebDriver driver;

	public CatlogPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public static Logger log = Logger.getLogger(LogInPage.class);

	@FindBy(xpath = "//div[contains(@class, 'col-lg-4 col-md-')]")
	List<WebElement> products;
	
	@FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
	WebElement orderButton;

	@FindBy(xpath = "//div[text()=' Product Added To Cart ']")
	WebElement toastMsg;

	@FindBy(xpath = "(//button[@class='btn btn-custom'])[3]")
	WebElement cartBtn;

	By firstProduct = By.xpath("//b[text()='zara coat 3']");

	public void clickOnProduct(String product1) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(firstProduct).getText().equalsIgnoreCase(product1)).findFirst()
				.orElse(null);

		prod.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
	}

	public boolean verifyToastMsg() {
		MethodsUtility.waiForElement(toastMsg);
		String msg = toastMsg.getText();
		System.out.println(msg);
		if (msg.contains("Product Added To Cart")) {
			return true;
		} else {
			return false;
		}
	}

	public CheckoutPage navigateToCheckoutPage(WebDriver driver) throws InterruptedException {
		// WebElement cartBtn = driver.findElement(By.xpath("(//button[@class='btn
		// btn-custom'])[3]"));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//		wait.until(ExpectedConditions.visibilityOf(cartBtn));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(firstProduct).getText().equalsIgnoreCase("zara coat 3"))
				.findFirst().orElse(null);

		prod.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click();
		Thread.sleep(5000l);
		MethodsUtility.waiForElement(cartBtn);
		cartBtn.click();
		return new CheckoutPage(driver);
	}
	
	public OrderPage navigateToOrderPAge() {
		orderButton.click();
		log.info("User Logged In Successfully Navigate to Order Page");
		return new OrderPage(driver);
	}

}
