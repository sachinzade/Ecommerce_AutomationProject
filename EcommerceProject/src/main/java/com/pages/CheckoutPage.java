package com.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.utilities.MethodsUtility;

public class CheckoutPage {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static Logger log = Logger.getLogger(LogInPage.class);
	
	@FindBy(xpath = "//h3[text()='zara coat 3']")
	WebElement productCart;

	@FindBy(xpath = "//button[text()='Buy Now']")
	WebElement buyNow;

	@FindBy(xpath = "//div[@class='user__address']//input[@class='input txt text-validated']")
	WebElement addCountry;

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']//span[text()=' India']")
	WebElement countryName;

	@FindBy(xpath = "//div[@class='actions']//a[text()='Place Order ']")
	WebElement placeOrderBtn;

	@FindBy(xpath = "//h1[text()=' Thankyou for the order. ']")
	WebElement orderCompleteMsg;

//	Boolean bn = productCart.stream().anyMatch(
//			s -> s.findElement(By.cssSelector("//h3[text()='zara coat 3']")).getText().equalsIgnoreCase("zara coat 3"));

	public boolean verifyProductName(String productName) {
		String pName = productCart.getText();
		if (pName.contains(productName)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean clickOnBuyNow(String country, String successMsg) {
		MethodsUtility.waiForElement(buyNow);
		buyNow.click();
		addCountry.sendKeys(country);
		countryName.click();
//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", buyNowbtn);
//	    wait.until(ExpectedConditions.visibilityOf(OrderComplete));
		MethodsUtility.waiForElement(placeOrderBtn);
		MethodsUtility.javascriptClick(placeOrderBtn);
		MethodsUtility.waiForElement(orderCompleteMsg);
		String OrderComplete = orderCompleteMsg.getText();
		if (OrderComplete.contains(successMsg)) {
			return true;
		} else {
			return false;
		}
	}
}
