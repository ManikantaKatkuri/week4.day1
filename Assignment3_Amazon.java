package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment3_Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {

		// Load the URL https://www.amazon.in/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.amazon.in/");

		// search as oneplus 9 pro 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro"+Keys.ENTER);
		Thread.sleep(3000);

		// Get the price of the first product
		WebElement element1 = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]"));
		String firstProduct=element1.getText();
		System.out.println("Price of the first resulting product: "+firstProduct);
		Thread.sleep(5000);

		// Print the number of customer ratings for the first displayed product
		WebElement element2 = driver.findElement(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style'])[2]/span"));
		System.out.println("Ratings of the first resulting product: "+element2.getText());
		Thread.sleep(3000);

		// Click the first text link of the first image
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal']/parent::a)[1]")).click();
		Thread.sleep(5000);
		Set<String> set = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(set);
		driver.switchTo().window(list.get(1));
		Thread.sleep(5000);

		// Take a screen shot of the product displayed
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Oneplus.png");
		FileUtils.copyFile(source, destination);
		Thread.sleep(5000);

		// Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(5000);

		// Get the cart subtotal and verify if it is correct	
		Set<String> set2 = driver.getWindowHandles();//get no of open windows
		List<String> list2=new ArrayList<String>(set2);
		driver.switchTo().window(list2.get(1));
		Thread.sleep(5000);
		WebElement element3 = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']"));
		String subTotal=element3.getText();
		Thread.sleep(5000);
		System.out.println("Cart SubTotal= "+subTotal);
		if(subTotal.contains(firstProduct)) {
			System.out.println("Pass");
		}
		else {
			System.out.println("Fail");
		}

		// close the browser
		Thread.sleep(5000);
		driver.quit();

	}

}
