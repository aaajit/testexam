package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NewTest {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "/home/ajit/Documents/selenium/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("http://www.allmovie.com/");

		// Searching for The Godfather //input[@placeholder='Search']
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("The Godfather" + Keys.ENTER);

		String searchResult = driver.findElement(By.xpath("//h1[contains(text(),'search results')]")).getText();
		System.out.println(searchResult);

		driver.findElement(By.xpath("//div[@data-hasqtip='1']/a[contains(text(),'The Godfather')]")).click();

	}

	@Test(priority = 0)
	public void verifyGenre() {
		SoftAssert softassert = new SoftAssert();
		String expectedGenre = "Genres - Crime";
		String actualGenre = driver.findElement(By.xpath("//span[@class='header-movie-genres']")).getText();
		softassert.assertEquals(actualGenre, expectedGenre);
		softassert.assertAll();
	}

	@Test(priority = 1)
	public void verifyRating() {
		SoftAssert softassert = new SoftAssert();
		String expectedRating = "A";
		String actualRating = driver.findElement(By.xpath("//span[normalize-space()='R']")).getText();
		System.out.println(actualRating);
		softassert.assertEquals(actualRating, expectedRating);
		softassert.assertAll();
	}

	@Test(priority = 2)
	public void verifyDirector() {
		SoftAssert softassert = new SoftAssert();
		driver.findElement(By.xpath("//li[@class='tab cast-crew']//a[1]")).click();
		String expectedDirector = "Francis Ford Coppola";
		String actualDirector = driver
				.findElement(By.xpath("//dt[@class='name artist-name']//a[contains(text(),'Francis Ford Coppola')]"))
				.getText();
		softassert.assertEquals(actualDirector, expectedDirector);
		softassert.assertAll();
	}

	@Test(priority = 3)
	public void verifyCharacter() {
		SoftAssert softassert = new SoftAssert();
		String expectedDirector = "Michael Corleone";
		String actualDirector = driver.findElement(By.xpath("//div[normalize-space()='Michael Corleone']")).getText();
		softassert.assertEquals(actualDirector, expectedDirector);
		softassert.assertAll();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}