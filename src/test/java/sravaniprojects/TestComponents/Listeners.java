package sravaniprojects.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import sravaniprojects.resources.ExtentReportsConfig;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent=ExtentReportsConfig.ExtentReportTest();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); //Thread safe
	
	@Override
	public   void onTestStart(ITestResult result) {
	    // not implemented
		 test=extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);//to create unique thread id for each test if tests running parallelly
	  }
	
	@Override
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		test.log(Status.PASS, "Test Passed");
	  }
	
	public void onTestFailure(ITestResult result) {
	    // not implemented
		test.log(Status.FAIL, "Test case Failed");
		extentTest.get().fail(result.getThrowable()); //to show error in which line
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		//screenshot
		String filepath=null;
		try {
			 filepath=getscreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	  }
	
	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	
	@Override
	public  void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }
	
	
}
