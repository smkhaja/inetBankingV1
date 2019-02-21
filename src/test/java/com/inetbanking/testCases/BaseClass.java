package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readconfig = new ReadConfig();
	public String baseUrl = readconfig.getApplicationURL();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassWord();
	public static WebDriver driver;
	public static Logger logger;
	@Parameters("browser")
	@BeforeClass
	public void Setup(String br) {
		logger = Logger.getLogger("Ebanking");
		PropertyConfigurator.configure("log4j.properties");
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		}
	
	@AfterClass
	public void tearDown() 
	{
		driver.quit();
	}
	public void captureScreen(WebDriver driver,String tname) throws IOException {
	TakesScreenshot ts = (TakesScreenshot)driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File target = new File(System.getProperty("user.dir")+ "/Screenshots/"+ tname+".png");
	FileUtils.copyFile(source,target);
	System.out.println("Screnshot is taken");
	}
	public String randomstring() {
	     String generatedstring	= RandomStringUtils.randomAlphabetic(8);
	return( generatedstring);
	}
	public static String randomnum() {
	    String generatedstring2	= RandomStringUtils.randomNumeric(4);
	return (generatedstring2);
	}
	}
