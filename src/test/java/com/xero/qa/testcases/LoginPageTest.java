package com.xero.qa.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xero.qa.base.TestBase;
import com.xero.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void SetUp() {
		
		intialization();
		loginPage = new LoginPage();
	}
	
	
	@Test
	public void SignUpXero() {
		
		
		
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();

		loginPage.login("Ashwini", "Ashwini");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	


}
