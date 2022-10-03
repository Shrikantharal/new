package parent.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import parent.pageObjects.HomePage;

public class BaseTest  //make sure every test should extend base test
{
	public WebDriver driver;
	public HomePage hp;				//declaring globally as we need to use this as a object in EtoEtest class
	
	
	public WebDriver intitDriver() throws IOException 
	{

		/*Properties prop=new Properties();
		//FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/parent/Resources/GlobalData.properties");
		FileInputStream fis=new FileInputStream("C:/Users/91845/OneDrive/Desktop/eclipse Workspace/seleniumFrameworkDesign/src/main/java/parent/Resources/GlobalData.properties");

		prop.load(fis);*/
		Properties prp=new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"/data.properties");
		prp.load(file);
		String browserName=prp.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		
		
		/*if(browserName.equalsIgnoreCase("chrome"));
		{
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		}
		/*else if(browserName.equalsIgnoreCase("firefox"))
		{
			//code
		}*/
		/*else if(browserName.equalsIgnoreCase("edge"))
		{
			//code
		}*/
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScrrenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

	@BeforeMethod
	public HomePage launchURL() throws IOException 
	{
		driver=intitDriver();
		
		hp=new HomePage(driver);
		hp.goTo();
		return hp;
		
		
	}
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
}
