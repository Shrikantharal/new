package parent.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import parent.utilities.UtilityClass;

public class ProductCatalog extends UtilityClass
{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);// serving driver to parent (UtilityClass) every child needs to serve their parent.
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="button[routerlink*=cart]")
	WebElement cartButton;
	
	By prod=By.cssSelector(".mb-3");
	By addCart=By.cssSelector(".w-10");
	By waitToLoadEle=By.cssSelector("#toast-container");
	By invisible=By.cssSelector(".ng-animating");
	
	public List<WebElement> productList()
	{
		
		waitForElementToAppear(prod);
		return products;
	}
	
	public WebElement getProductByName(String prodcutNmae)
	{
		WebElement prod=products.stream().filter(s-> 
		s.findElement(By.cssSelector("b")).getText().equals(prodcutNmae)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String prodcutNmae)
	{
		WebElement prod=getProductByName(prodcutNmae);
		prod.findElement(addCart).click();
		waitForElementToAppear(waitToLoadEle);
		waitForElementNotToAppear(invisible);
	}
	
	public CartPage getCart() 
	{
		waitForElementNotToAppear(invisible);
		cartButton.click();
		CartPage cp=new CartPage(driver);
		return cp;
		
	}
}
