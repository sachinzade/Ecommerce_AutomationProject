package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.baseclass.BaseClass;
import com.pages.CatlogPage;
import com.pages.CheckoutPage;
import com.utilities.MethodsUtility;

public class CheckoutPageTest extends BaseClass {

	CatlogPage catlogPage;
	CheckoutPage checkoutPage;
	String product = "zara coat 3";
	String sheetPath = System.getProperty("user.dir") + "/src/main/java/com/testdata/testData.xlsx";

	@BeforeClass
	public void setUp() throws Exception {
		intialization();
		checkoutPage = loadlogInPage()
				.navigateToCatlogPage(MethodsUtility.readProperty("userName"), MethodsUtility.readProperty("password"))
				.navigateToCheckoutPage(driver);
		catlogPage = new CatlogPage(driver);
	}

	@Test(dataProvider = "getDataForCatlogPage")
	public void userShouldBuytheProduct(String country, String successMsg) {
		Assert.assertTrue(checkoutPage.clickOnBuyNow(country, successMsg));
	}

	@DataProvider
	public Object [][] getDataForCatlogPage() throws Exception{
		Object data[][] = MethodsUtility.getDataFromAnyExcelSheet(sheetPath, "Data2");
		return null;
		
	}
	@AfterClass
	public void tearDown() {
		MethodsUtility.closeBrowser();
	}
}
