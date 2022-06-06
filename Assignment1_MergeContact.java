package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1_MergeContact {

	public static void main(String[] args) throws InterruptedException {

		//1. Launch URL "http://leaftaps.com/opentaps/control/login"
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://leaftaps.com/opentaps/control/login");

		//2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");

		//3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();

		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();

		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();

		// 6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();

		// 7. Click on Widget of From Contact
		driver.findElement(By.xpath("//table[@name='ComboBox_partyIdFrom']/following::img")).click();
		Thread.sleep(1000);

		// 8. Click on First Resulting Contact
		String parentwindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		driver.findElement(By.xpath("(//div/a[@class='linktext'])[1]")).click();
		driver.switchTo().window(parentwindow);

		// 9. Click on Widget of To Contact
		driver.findElement(By.xpath("//input[@name='partyIdTo']/following::img")).click();
		Set<String> handle2 = driver.getWindowHandles();
		List<String> list2=new ArrayList<String>(handle2);
		driver.switchTo().window(list2.get(1));
		Thread.sleep(1000);

		// 10. Click on Second Resulting Contact
		driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']/div/a)[2]")).click();
		driver.switchTo().window(parentwindow);
		
		// 11. Click on Merge button using Xpath Locator
		driver.findElement(By.linkText("Merge")).click();
		
		// 12. Accept the Alert	
		Alert alert1 = driver.switchTo().alert();
		alert1.accept();
		
		// 13. Verify the title of the page
		System.out.println("Title of the Page: "+driver.getTitle());
		
		Thread.sleep(5000);
		driver.close();

	}

}
