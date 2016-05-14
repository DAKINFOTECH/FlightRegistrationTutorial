package com.dakinfotech.Prototype;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Sprint108 {
	ExtentReports extent;
	ExtentTest test;
	String path="M:\\Study\\Selenium\\SeleniumScripts\\workspace1\\com.dakinfotech.Prototype\\Reporting\\Advreportpath.html";
@Test(priority=0)		
public void test1(){
	extent=new ExtentReports(path,true);
	test=extent.startTest("Test1");
	test.log(LogStatus.INFO, "Going to print Test1Passed");
	System.out.println("Test1Passed");
	test.log(LogStatus.PASS, "Print Success");
	extent.endTest(test);
	extent.flush();
}
@Test(priority=1)		
public void test2(){
	extent=new ExtentReports(path,false);
	test=extent.startTest("Test2");
	test.log(LogStatus.INFO, "Going to print Test2Passed");
	System.out.println("Test2Passed");
	test.log(LogStatus.PASS, "Print Success");
	extent.endTest(test);
	extent.flush();
}
@Test(priority=2)		
public void test3(){
	extent=new ExtentReports(path,false);
	test=extent.startTest("Test3");
	test.log(LogStatus.INFO, "Going to print Test3Passed");
	System.out.println("Test3Passed");
	test.log(LogStatus.PASS, "Print Success");
	extent.endTest(test);
	extent.flush();
}
		
}
