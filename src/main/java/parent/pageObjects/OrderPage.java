package parent.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import parent.utilities.UtilityClass;

public class OrderPage extends UtilityClass 
{
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);  // used to send driver to parent class(Utitlity class).
		this.driver=driver;
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="button[type='button'] span")
	List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	WebElement placeOredr;
	
	@FindBy(css=".hero-primary")
	WebElement successMsg;;
	
	
	public void getCountry(String country1)
	{
		country.sendKeys(country1);
	}
	
	public void selectCountry(String country1)
	{
		WebElement item=countries.stream().filter(country->country.getText().equalsIgnoreCase(country1)).findFirst().orElse(null);		
		item.click();
	}
	
	public void placeOrder()
	{
		placeOredr.click();
	}
	
	public String getSuccessMsg()
	{
		return successMsg.getText();
	}
	

}
