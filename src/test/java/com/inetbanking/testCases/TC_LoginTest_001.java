package com.inetbanking.testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;



public class TC_LoginTest_001 extends BaseClass 
{
@Test
public void loginTest() throws IOException 
{ 
	driver.get(baseUrl);
	logger.info("Url is opened");
	
	LoginPage lp= new LoginPage(driver);
	lp.setUserName(username);
	logger.info("username is entered");
	lp.setPassword(password);
	logger.info("password is entered");

	if(driver.getTitle().equals("Guru99 Bank Home Page"))
		{
		Assert.assertTrue(true);
		logger.info("Test is passed");
		}
		else
		{
		captureScreen( driver,"loginTest");
		Assert.assertTrue(false);
		logger.info("Test is failed");
	
	}
	
}
}
