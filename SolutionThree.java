package com.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NewTest {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ajit/Documents/selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.pepperfry.com/");

	}

	@Test
	public void f() throws InterruptedException, IOException {

		File file = new File("/home/ajit/Documents/selenium/AjitQ3.xls");
		FileInputStream fin = new FileInputStream(file);

		HSSFWorkbook wbook = new HSSFWorkbook(fin);
		HSSFSheet sheet = wbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		for (int i = 0; i <= rowCount; i++) {
			String words = sheet.getRow(i).getCell(0).getStringCellValue();
			WebElement searchTxtBox = driver.findElement(By.id("search"));
			searchTxtBox.clear();
			searchTxtBox.sendKeys(words + Keys.ENTER);

			try {
				driver.findElement(By.xpath("//li[contains(text(),'Relevance')]")).click();
				driver.findElement(By.xpath("//a[contains(text(),'Price Low to High')]")).click();
			} catch (NoSuchElementException e) {
				System.out.println("No product");
			}

		}

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}