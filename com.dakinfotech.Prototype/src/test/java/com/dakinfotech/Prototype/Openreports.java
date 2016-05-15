package com.dakinfotech.Prototype;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Openreports {
	
	String path="M:\\Study\\Selenium\\SeleniumScripts\\workspace1\\com.dakinfotech.Prototype\\Reporting\\Advreportpath.html";
	@Test
	public void Advreport(){
		WebDriver driver=new FirefoxDriver();
		driver.get(path);
	}

}
