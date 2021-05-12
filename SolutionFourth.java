package com.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

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
		driver.get("http://maps.google.com");

	}

	@Test
	public void f() throws InterruptedException, IOException {

		WebElement searchBox = driver.findElement(By.id("searchboxinput"));
		searchBox.sendKeys("Antilia, SK Barodawala Marg, Tardeo, Mumbai, Maharashtra 400026" + Keys.ENTER);
		WebElement addressOffice = driver.findElement(By.xpath("//span[contains(text(),'SK')]"));
		System.out.println(addressOffice.getText());
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File s = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(s, new File("Q4.png"));

		driver.findElement(By.xpath("//div[contains(text(),'Directions')]")).click();
		WebElement addressHome = driver
				.findElement(By.xpath("//input[text()='Choose starting point, or click on the map...']"));
		addressHome.sendKeys("Powai" + Keys.ENTER);

		WebElement time = driver
				.findElement(By.xpath("(//div[@class='section-directions-trip-numbers']/child::div[1])[1]"));
		System.out.println("Duration-Time" + time.getText());

		WebElement distance = driver
				.findElement(By.xpath("(//div[@class='section-directions-trip-numbers']/child::div[2])[1]"));
		System.out.println("Distance: " + distance.getText());

	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
