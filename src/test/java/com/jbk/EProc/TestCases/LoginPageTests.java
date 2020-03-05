package com.jbk.EProc.TestCases;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jbk.EProc.TestBase.TestBase;

public class LoginPageTests extends TestBase{
	
	
	@Test()
	public void offWebLogoTestCase() {
		
		Boolean logotest=driver.findElement(By.xpath("//img")).isDisplayed();
		
		Assert.assertTrue(logotest);
		
	}
	
	
	
	
	
	
	

}
