package parent.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import parent.utilities.UtilityClass;

public class OrderHistory extends UtilityClass
{
	WebDriver driver;
	
	public OrderHistory(WebDriver driver)
	{
		super(driver);// serving driver to parent (UtilityClass) every child needs to serve their parent.
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> cartProd;

	public boolean verifyOrder(String name)
	{
		boolean match=cartProd.stream().anyMatch(cartpro->cartpro.getText().equalsIgnoreCase(name));
		return match;
	}
	
	

}
