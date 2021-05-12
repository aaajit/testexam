package com.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewTest {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "/home/ajit/Documents/selenium/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.google.com/maps/");

	}

	@Test
	public void f() throws InterruptedException, IOException {
		SoftAssert softassert = new SoftAssert();
		WebElement searchBox = driver.findElement(By.id("searchboxinput"));
		searchBox.sendKeys("Wankhede Stadium" + Keys.ENTER);
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File s = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(s, new File("pic1.png"));

		/*
		 * WebElement btnText=driver.findElement(By.xpath(
		 * "//*[@id=\"pane\"]/div/div[1]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/span[1]/span[1]/button"
		 * )); String words=btnText.getAttribute("value"); System.out.println("Btn"+
		 * words); Assert.assertEquals("stadium", words);
		 */

		WebElement btnText2 = driver.findElement(By.xpath("//span[contains(text(),'Wankhede Stadium Mumbai')]"));
		String words2 = btnText2.getText();
		System.out.println(words2);
		softassert.assertEquals(words2,"Wankhede Stadium Mumbai");
		softassert.assertAll();

		System.out.println("Rating" + driver
				.findElement(By.xpath("//span[@class='mapsConsumerUiSubviewSectionSharedStar__section-star-display']"))
				.getText());

		String title = driver.getTitle();
		Assert.assertEquals(title, "Wankhede Stadium - Google Maps");
		
		WebElement linkName = driver.findElement(By.xpath("//div[contains(text(),'mum')]"));
		String links = linkName.getText();
		System.out.println(links);
		Assert.assertEquals("mumbaicricket.com", links);

		WebElement add = driver.findElement(By.xpath("//div[contains(text(),'Vinoo')]"));
		String address = add.getText();
		System.out.println(address);

		try {
			WebElement phone = driver.findElement(By.xpath("//div[contains(text(),'022 2279 5500')]"));
			if (phone.isDisplayed()) {
				System.out.println("phone Number is displayed");
			} else
				System.out.println("phone Number is not displayed");
		} catch (Exception e) {
			System.out.println("Phone number element not present");
		}

		File s1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(s1, new File("pic2.png"));

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
