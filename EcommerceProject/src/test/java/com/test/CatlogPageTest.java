package com.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;
import com.pages.CatlogPage;
import com.pages.LogInPage;
import com.utilities.MethodsUtility;

public class CatlogPageTest extends BaseClass {

	LogInPage loginpage;
	CatlogPage catlogPage;
	String sheetPath = System.getProperty("user.dir") + "/src/main/java/com/testdata/testData.xlsx";

	@BeforeClass
	public void setUp() throws Exception {
		intialization();
		catlogPage = loadlogInPage().navigateToCatlogPage(MethodsUtility.readProperty("userName"), MethodsUtility.readProperty("password"));
		loginpage = new LogInPage(driver);
	}

	@Test(dataProvider = "getDataForCatlogPage")
	public void verifyUserShgouldAbleToAddProduct(String productname ) {
		catlogPage.clickOnProduct(productname);
		Assert.assertTrue(catlogPage.verifyToastMsg());
	}

	@DataProvider
	public Object [][] getDataForCatlogPage() throws Exception{
		Object data[][] = MethodsUtility.getDataFromAnyExcelSheet(sheetPath, "Data1");
		return data;
		
	}
	
	@AfterClass
	public void tearDown() {
		MethodsUtility.closeBrowser();
	}
}
