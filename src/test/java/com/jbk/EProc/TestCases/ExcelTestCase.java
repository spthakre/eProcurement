package com.jbk.EProc.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jbk.EProc.Utility.ExcelSheetTest;

public class ExcelTestCase {
	
  WebDriver driver;
	
  @Test(dataProvider = "testData")
  public void data(String username, String password) {
	    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	    driver = new ChromeDriver();
		driver.get("http://www.gcrit.com/build3/admin/");
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.id("tdb1")).click();
		Assert.assertEquals("http://www.gcrit.com/build3/admin/index.php", driver.getCurrentUrl());
		driver.close();
		driver.quit();
  }

  @DataProvider
  public Object[][] testData() throws IOException {
	  
	  return ExcelSheetTest.getData("a1.xlsx", "Sheet1");
	  	  
  }
 }

