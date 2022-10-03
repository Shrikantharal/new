package parent.testClass;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import parent.Testcomponents.BaseTest;
public class EndToEndTest2ErrorCheck extends BaseTest
{
	
	@Test(groups= {"ErrorHandling"})
	public void EndToEndtest() throws IOException, InterruptedException
	   {
		
		//HomePage hp=launchURL(
		
		hp.loginApplication("sbs@gmail.com", "Shri#12345"); //Wrong username
		
		Assert.assertEquals(hp.getErrorMsg(),"ncorrect email or password.");
	
		
		}

	@Test
	public void EndToEndtestt() throws IOException, InterruptedException
	   {
		
		//HomePage hp=launchURL(
		
		hp.loginApplication("sb@gmail.com", "Shri#1245"); //Wrong username
		
		Assert.assertEquals(hp.getErrorMsg(),"Incorrect email or password.");
	
		
		}

}