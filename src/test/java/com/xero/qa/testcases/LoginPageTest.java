package com.xero.qa.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xero.qa.util.TestUtil;
import com.xero.qa.base.TestBase;
import com.xero.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	TestUtil testUtil;
	
	String sheetName = "login";
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() {
		
		intialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
	}
	
	
	@DataProvider
	public Object[][] getXeroTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(dataProvider="getXeroTestData")
	public void SignUpXero(String username,String password) {
		
		
		
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();

		loginPage.login(username, password);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	


}
