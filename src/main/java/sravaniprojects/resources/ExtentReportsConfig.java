package sravaniprojects.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsConfig {
	
	
	public static ExtentReports ExtentReportTest() {
		
	String path=System.getProperty("user.dir")+"\\reports\\index.html";
	ExtentSparkReporter reporter=new ExtentSparkReporter(path);
	reporter.config().setReportName("Website Testing");
	reporter.config().setDocumentTitle("Test Results");
	ExtentReports extent=new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Sravani");
	return extent;
	 
	
	}
}
