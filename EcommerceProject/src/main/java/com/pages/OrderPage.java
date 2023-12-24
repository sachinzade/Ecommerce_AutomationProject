package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.MethodsUtility;

public class OrderPage {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Delete']")
	WebElement deleteOrderButton;

	@FindBy(xpath = "//h1[text()='Your Orders']")
	WebElement yoursOrder;

	@FindBy(xpath = "//button[text()='View']")
	WebElement viewOrderButton;

	@FindBy(xpath = "//div[text()=' order summary ']")
	WebElement orderSummeryText;

	public boolean verifyOrder() {
		MethodsUtility.waiForElement(viewOrderButton);
		viewOrderButton.click();
		MethodsUtility.waiForElement(orderSummeryText);
		String text = orderSummeryText.getText();
		if (text.matches("ORDER SUMMARY")) {
			return true;
		} else {
			return false;
		}
	}

}
