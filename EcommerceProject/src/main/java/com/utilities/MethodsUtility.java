package com.utilities;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.baseclass.BaseClass;

public class MethodsUtility extends BaseClass {

	public static FileInputStream fis = null;
	public static Properties prop = null;
	public static Actions act = null;
	public static WebDriverWait wait = null;

	public static String readProperty(String key) {
		prop = new Properties();

		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}// readProperties Ends

	public static String[][] getDataFromAnyExcelSheet(String filePath, String sheetName) throws Exception {

		DataFormatter df = new DataFormatter();
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = sheet.getRow(i + 1).getCell(j);
				String value = df.formatCellValue(cell);
				data[i][j] = value;
			}
		}
		return data;
	}

	public static void waiForElement(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void javascriptClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public static void closeBrowser() {
		driver.close();
	}
}
