package sravaniprojects.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import sravaniprojects.pageobjects.LandingPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
//import tools.jackson.core.type.TypeReference;
//import tools.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver initializeDriver() throws IOException

	{
		// properties class
		Properties prop = new Properties();
		// FileInputStream fis=new
		// FileInputStream(System.getProperty("user.dir")+"//src//main//java//sravaniprojects//resources//GlobalData.properties");
		FileInputStream fis = new FileInputStream(
				"C://Users//Administrator//eclipse-workspace//SeleniumFrameWorkDesign//src//main//java//sravaniprojects//resources//GlobalData.properties");
		prop.load(fis);
		// java terminal operator to run the test in browser depends on option given in
		// terminal
		// if System.getProperty("browser")( terminal given in terminal) is null then
		// browser value pick from global parameters
		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// String browsername=prop.getProperty("browser");

		if (browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browsername.contains("headless")) {
				// options.addArguments("headless");
				options.addArguments("--headless=new"); // new headless mode
				options.addArguments("--disable-gpu");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));// full size

		} else if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browsername.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;

	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap- Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

		// {map, map}

	}

	public String getscreenshot(String testCasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCasename + ".png";
	}

}
