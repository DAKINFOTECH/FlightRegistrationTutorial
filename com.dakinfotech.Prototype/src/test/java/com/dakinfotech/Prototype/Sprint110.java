package com.dakinfotech.Prototype;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Sprint110 {
	ExtentReports extent;
	ExtentTest test;
	String path="M:\\Study\\Selenium\\SeleniumScripts\\workspace1\\com.dakinfotech.Prototype\\Reporting\\Advreportpath.html";
@Test(priority=0)		
public void test4(){
	extent=new ExtentReports(path,false);
	test=extent.startTest("Test4");
	test.log(LogStatus.INFO, "Going to print Test4Passed");
	System.out.println("Test4Passed");
	test.log(LogStatus.PASS, "Print Success");
	extent.endTest(test);
	extent.flush();
}
@Test(priority=1)		
public void test5(){
	extent=new ExtentReports(path,false);
	test=extent.startTest("Test5");
	test.log(LogStatus.INFO, "Going to print Test5Passed");
	System.out.println("Test5Passed");
	test.log(LogStatus.PASS, "Print Success");
	extent.endTest(test);
	extent.flush();
}
@Test(priority=2)		
public void test6(){
	extent=new ExtentReports(path,false);
	test=extent.startTest("Test6");
	test.log(LogStatus.INFO, "Going to print Test6Passed");
	System.out.println("Test6Passed");
	test.log(LogStatus.PASS, "Print Success");
	extent.endTest(test);
	extent.flush();
}
@AfterClass
public void openreports(){
	WebDriver driver=new FirefoxDriver();
	driver.get(path);
}
		
}
