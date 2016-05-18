package com.dakinfotecz.pwcm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Openreports {
	String path="C:\\Users\\348027\\workspaceluna\\com.dakinfotecz.pwcm\\Reporting\\Advreportpath.html";
	@Test
	public void Advreport(){
		WebDriver driver=new FirefoxDriver();
		driver.get(path);
	}

}
