package parent.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import parent.utilities.UtilityClass;

public class CartPage extends UtilityClass
{
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);// serving driver to parent (UtilityClass) every child needs to serve their parent.
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProd;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public List<WebElement> cartProducts()
	{
		return cartProd;
	}
	
	public boolean getCartProd(String name)
	{
		boolean match=cartProd.stream().anyMatch(cartpro->cartpro.getText().equalsIgnoreCase(name));
		return match;
	}
	
	public OrderPage goToCheckOut()
	{
		checkOut.click();
		OrderPage op=new OrderPage(driver);
		return op;
	}

}
