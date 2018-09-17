package com.xero.qa.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.xero.qa.base.TestBase;


public class LoginPage extends TestBase {
	
	
	//Page Factory -OR:
		
	@FindBy(xpath="//input[@id='email']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='password'] ")
	WebElement password;
	
	@FindBy(xpath="//button[@id='submitButton']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[@class='forgot-password-advert']")
	WebElement forgotpasswordLink;
	

	
	//Initializing the Page Objects:
	
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public void login(String un, String pwd) {
		
		
		username.clear();
		username.sendKeys(un);
		reportLog("UserName is Entered");
		
		password.clear();
		password.sendKeys(pwd);
		reportLog("Password Is entered");
		
		loginBtn.click();
		reportLog("Login button is clicked");
		
		
	}
	
	
	
	
	
	
	
	

}
