package com.CodingRound.Selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TC_PriceComparison {
	public WebDriver driver;
	public String amazonPrice;
	public String flipkartPrice;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@Test(priority =1)
	public void getAmazonPrice() throws InterruptedException {

		driver.get("https://www.amazon.in");
		Thread.sleep(2000);
		driver.findElement(By.name("field-keywords")).sendKeys("iPhone XR (64GB) yellow", Keys.ENTER);
		String x = "//span[@class='a-price-whole']";
		String aPrice=driver.findElement(By.xpath(x)).getText();
		amazonPrice = aPrice.replaceAll("[^a-zA-Z0-9_-]", "");
		System.out.println("amazonPrice :" +amazonPrice);	
	}
	
	@Test(priority =2)
	public void getFilpkartPrice() throws InterruptedException {
		driver.navigate().to("http://flipkart.com");
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		driver.findElement(By.name("q")).sendKeys("iPhone XR (64GB) yellow");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		String x ="//div[@class='_1vC4OE _2rQ-NK']";
		String fPrice = driver.findElement(By.xpath(x)).getText();
		System.out.println("fPrice "+fPrice);
		flipkartPrice=fPrice.replaceAll("[^a-zA-Z0-9_-]", "");
		System.out.println("FlipkartPrice :" +flipkartPrice);

			
		if(Integer.parseInt(flipkartPrice)>Integer.parseInt(amazonPrice)) {
			System.out.println("Flipkart value is greter than amazon : " + flipkartPrice);
		}else {
			System.out.println("Flipkart value is leser than amazon : " + amazonPrice);
		}
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
