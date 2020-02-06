package com.CodingRound.Selenium;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_TripAdvisor {
	public WebDriver driver;
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}
	@Test
	public void tripAdvisorReview() throws Exception {
		driver.get("https://www.tripadvisor.in/");
		driver.findElement(By.xpath("//div[@class='brand-global-nav-action-search-Search__searchButton--b9-IK']")).click();
		driver.findElement(By.id("mainSearch")).sendKeys("Club Mahindra",Keys.ENTER);

		
		driver.findElement(By.xpath("//*[@id='BODY_BLOCK_JQUERY_REFLOW']/div[2]/div/div[2]/div/div/div/div/div[1]/div/div[1]/div/div[3]/div/div[1]/div/div[2]/div/div/div/div/div/div/div[2]/div[1]/div[2]/div/a")).click();
		Set<String>Set=driver.getWindowHandles();
		Iterator<String>it=Set.iterator();
		String parentWinID=it.next();
		String childWinID=it.next();
		
		driver.switchTo().window(childWinID);
		driver.findElement(By.xpath("//a[text()='Write a review']")).click();
		
		Set<String>Set1=driver.getWindowHandles();
		Iterator<String>it1=Set.iterator();
		String parentWinID1=it.next();
		String childWinID1=it.next();
		
		driver.switchTo().window(childWinID1);
		driver.findElement(By.xpath("//span[@class='ui_bubble_rating fl bubble_10']")).click();
		
//		Actions action = new Actions(driver);
//		WebElement target = driver.findElement(By.id("bubble_rating"));
//		action.moveToElement(target).perform();
//		action.moveToElement(driver.findElement(By.xpath("//span[@id='bubble_rating'][@class='ui_bubble_rating fl bubble_00 bubble_50']"))).click().build().perform();
//		
		
		driver.findElement(By.xpath(".//input[@id='ReviewTitle']")).sendKeys("Hi");
		driver.findElement(By.xpath(".//*[@id='ReviewText']")).sendKeys("Nice Hotel");
		Thread.sleep(2000);
		driver.findElement(By.id("SUBMIT")).click();
		
		driver.switchTo().window(parentWinID);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}


}
