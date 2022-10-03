package parent.testClass;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import parent.Testcomponents.BaseTest;
import parent.pageObjects.CartPage;
import parent.pageObjects.HomePage;
import parent.pageObjects.OrderHistory;
import parent.pageObjects.OrderPage;
import parent.pageObjects.ProductCatalog;

public class EndToEndTest2_DataProvider extends BaseTest
{
	
	@Test
	public void EndToEndtest(HashMap<String,String> input) throws IOException
	   {
		
		//HomePage hp=launchURL();
		
		ProductCatalog pc=hp.loginApplication(input.get("email"), input.get("password"));
		//List<WebElement> products=pc.productList();
		pc.getProductByName(input.get("productName"));
		pc.addToCart(input.get("productName"));
		
		CartPage cp=pc.getCart();
		//List<WebElement>cartList=cp.cartProducts();
		//cp.cartProducts();
		Boolean match=cp.getCartProd("ZARA COAT 3");
		Assert.assertTrue(match);
		
		OrderPage op=cp.goToCheckOut();
		op.getCountry("de");
		op.selectCountry("denmark");
		op.placeOrder();
		Assert.assertEquals(op.getSuccessMsg(), "THANKYOU FOR THE ORDER.");
		
		}
	
	//Verify order placed history.
	
	@Test(dependsOnMethods={"EndToEndtest"})
	public void orderHistory()
	{
		ProductCatalog pc=hp.loginApplication("sb@gmail.com", "Shri#12345");
		OrderHistory oh=pc.getOrdersPage();
		Assert.assertTrue(oh.verifyOrder("ZARA COAT 3"));

	}
	
	@DataProvider
	public  Object[][] getData()
	{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email","sb@gmail.com");
		map.put("password","shri#12345");
		map.put("productName","ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email","shetty@gmail.com");
		map1.put("password","Iamking@000");
		map1.put("productName","ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	
	
	}


}