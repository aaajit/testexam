package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SolutionFIve {

	public static void main(String[] args) {
		
		try {
		System.setProperty("webdriver.chrome.driver", "/home/ajit/Documents/selenium/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		Dimension d = new Dimension(850, 850);
		driver.manage().window().setSize(d);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Navigate to https://datatables.net/
		driver.get("https://datatables.net/");
		Select ss = new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
		ss.selectByVisibleText("25");
		driver.findElement(By.xpath("//thead/tr[1]/th[4]")).click();

		WebElement tbl = driver.findElement(By.xpath("//table[@id='example']"));

		List<WebElement> rows = tbl.findElements(By.tagName("tr"));
		List<WebElement> heads = rows.get(0).findElements(By.tagName("th"));
		System.out.println("First 25 data");
		for (WebElement hd : heads) {
			System.out.print(hd.getText() + "\t\t\t");
		}
		System.out.println();

		for (WebElement tr : rows) {
			List<WebElement> tdata = tr.findElements(By.tagName("td"));
			if (tdata.size() != 0) {
				for (WebElement data : tdata) {
					System.out.print(data.getText() + "\t\t\t");
				}
			}
			System.out.println();
		}

		driver.findElement(By.xpath("//input[@type=\"search\"]")).sendKeys("Software Engineer");
		System.out.println("Data of the Software Engineers of below 30 years.");

		WebElement tblTwo = driver.findElement(By.xpath("//table[@id='example']"));

		List<WebElement> newRow = tblTwo.findElements(By.tagName("tr"));
		List<WebElement> newHead = newRow.get(0).findElements(By.tagName("th"));
		for (WebElement hd : newHead) {
			System.out.print(hd.getText() + "\t\t\t");
		}
		System.out.println();

		for (WebElement tr : newRow) {
			List<WebElement> newTableData = tr.findElements(By.tagName("td"));
			if (newTableData.size() != 0) {
				if (Integer.parseInt(newTableData.get(3).getText()) < 30) {
					for (WebElement data : newTableData) {
						System.out.print(data.getText() + "\t\t\t");
					}
				}
			}
			System.out.println();
		}
		driver.quit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
