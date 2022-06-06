package week4.day1;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2_Frames {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");

		// Frame-Topic
		WebElement ele1 = driver.findElement(By.id("frame1"));
		driver.switchTo().frame(ele1);
		Thread.sleep(500);
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Inline Frames");
		Thread.sleep(500);

		// Frame-Inner Frame Check box
		WebElement ele2 = driver.findElement(By.id("frame3"));
		driver.switchTo().frame(ele2);
		Thread.sleep(500);
		WebElement ele3 = driver.findElement(By.id("a"));
		ele3.click();
		Thread.sleep(500);
		// Frame-Animals

		WebElement ele4 = driver.findElement(By.id("frame2"));
		driver.switchTo().frame(ele4);
		WebElement ele5 = driver.findElement(By.xpath("//select[@id='animals']"));
		Select animals = new Select(ele5);
		animals.selectByValue("Baby Cat");
		System.out.println("Baby Cat is selected");
		
		Thread.sleep(5000);
		driver.close();

	}

}
