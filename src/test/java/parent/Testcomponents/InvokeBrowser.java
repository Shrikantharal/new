package parent.Testcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvokeBrowser {

	// **********************************************************************************************//
	// Misc. thigs

	// **********************************************************************************************//
	@Test
	public void m_thod() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		WebElement window = driver.findElement(By.className("_2QfC02"));
		driver.findElement(By.className("_2IX_2-")).sendKeys("shrikant");
		driver.findElement(By.cssSelector("._3mctLh ")).sendKeys("itsKing@789");
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.cssSelector("._2HKlqd "))).build().perform();
		act.click();

		
		act.moveToElement(driver.findElement(By.linkText("New to Flipkart? Create an account"))).build().perform();
		act.click().build().perform();
		

		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", window);/// JS to click webElement

		je.executeScript("window.scrollBy(0,150)"); /// JS to Scroll page

		
	}

}
