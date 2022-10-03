package parent.testClass;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest 
{
	public static void main(String[] args)
	{
		String email="sb@gmail.com";
		String countryName="denmark";
		String prodcutNmae="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("sb@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shri#12345");
		driver.findElement(By.id("login")).click();
		
		List<WebElement>products=driver.findElements(By.cssSelector(".mb-3"));
		
		
		WebElement prod=products.stream().filter(s-> 
		s.findElement(By.cssSelector("b")).getText().equals(prodcutNmae)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".w-10")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
		List<WebElement>cartProd=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProd.stream().anyMatch(cartpro->cartpro.getText().equalsIgnoreCase(prodcutNmae));
		Assert.assertTrue(match);
		
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".user__name  label")).getText(), email);
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("denmark");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='button'] span")));
		
		
		List<WebElement> sugg=driver.findElements(By.cssSelector("button[type='button'] span"));
		System.out.println(driver.findElement(By.cssSelector("button[type='button'] span")).getText());
		WebElement item=sugg.stream().filter(country->country.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);		
		item.click();
		
		driver.findElement(By.cssSelector(".action__submit ")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
	}

}