package parent.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import parent.Resources.ExtentReportTestNG;

//import org.testng.ITestListener;

public class Listeners extends BaseTest implements ITestListener {  //extending BaseTest for screenshot code
	
	ExtentReports extent=ExtentReportTestNG.getReportObject();
	ExtentTest test;
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test=extent.createTest(result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		
		test.log(Status.PASS, "Test is passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		
		test.fail(result.getThrowable());
		//Scrrenshot
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String path = null;
		try {
			path = getScrrenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		
		
	}
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
		
	}
	
	
	

}
