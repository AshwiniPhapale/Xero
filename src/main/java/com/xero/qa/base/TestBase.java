package com.xero.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.xero.qa.util.TestUtil;

// TestBase class
public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentTest test;
	public static ExtentReports extent;
	public static ExtentHtmlReporter reporter;

	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/xero/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void intialization() {
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "/Users/vihaan/Downloads/chromedriver 2");
			driver  = new ChromeDriver();
		} else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "/Users/vihaan/Downloads/geckodriver");
			driver  = new FirefoxDriver();
				
		}
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	
	
	@BeforeSuite
	public void before() {
		
		
		reporter=new ExtentHtmlReporter("./TestReport/log.html");
			
		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		
		
		
	}
	
	@BeforeMethod
	public void setUp(Method method) {
		
		test = extent.createTest(method.getName());
		
		test.assignAuthor("Ashwini");
		
	}
	
	@AfterSuite
	public void after() {
		
		test.info("Ending Suite");
		
		extent.flush();
		
	}
	
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is " + result.getName());
			test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case is Passed " + result.getName() );
		}

	}

	//Method for adding logs passed from test cases
	 public void reportLog(String message) {  
		 test.log(Status.INFO, message);
		
	 }

	
	
	
	
	
	
	
	

}
