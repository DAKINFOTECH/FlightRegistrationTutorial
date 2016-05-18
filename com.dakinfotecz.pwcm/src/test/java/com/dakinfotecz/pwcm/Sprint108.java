package com.dakinfotecz.pwcm;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import com.dakinfotecz.pwcm.pwcmlibrary;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Sprint108 {
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	pwcmlibrary config=new pwcmlibrary();
	String reportpath=".\\Reporting\\Advreportpath.html";
	String injectpath=".\\src\\main\\resources\\Injectfile.xls";
	String Attachpath="C:\\Users\\348027\\git\\PrototypeForPWCM\\com.dakinfotecz.pwcm";
	
	/*    TESTONE    */	
	@Test(priority=0)		
	public void test1(){
		extent=new ExtentReports(reportpath,true);
		test=extent.startTest("Homepage Verification");
		test.assignAuthor("Author: Arunkumar");
		test.assignCategory("Sprint108");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Browser Ready");
		driver.get("http://newtours.demoaut.com/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "URL Opened");
		String Title="Welcome: Mercury Tours";String link1="Home";
		String link2="Flights";String link3="Cruises";
		String link4="SUPPORT";String link5="CONTACT";
		AssertJUnit.assertEquals(Title,driver.getTitle());
		test.log(LogStatus.INFO, "Title Verified");
		AssertJUnit.assertEquals(link1,driver.findElement(By.xpath("//a[text()='Home']")).getText());
		AssertJUnit.assertEquals(link2,driver.findElement(By.xpath("//a[text()='Flights']")).getText());
		AssertJUnit.assertEquals(link3,driver.findElement(By.xpath("//a[text()='Cruises']")).getText());
		AssertJUnit.assertEquals(link4,driver.findElement(By.xpath("//a[text()='SUPPORT']")).getText());
		AssertJUnit.assertEquals(link5,driver.findElement(By.xpath("//a[text()='CONTACT']")).getText());
		test.log(LogStatus.INFO, "All Links Verified");
		test.log(LogStatus.PASS, "Homepage Verification Testcase Passed");
		extent.endTest(test);
		extent.flush();
	}
	/*    TESTTWO    */	
	@Test(priority=1)		
	public void test2(){
		extent=new ExtentReports(reportpath,false);
		test=extent.startTest("Test2");
		test.assignAuthor("Author: Arunkumar");
		test.assignCategory("Sprint108");
		test.log(LogStatus.INFO, "Going to print Test2Passed");
		System.out.println("Test2Passed");
		test.log(LogStatus.PASS, "Print Success");
		extent.endTest(test);
		extent.flush();
	}
	/*    TESTTHREE    */
	@Test(priority=2,dataProvider="Regiss")		
	public void test3(String name1,String name2,String pnone,String username,String address1,
	        String address2,String city,String state,String pincode,String country,
	        String email,String passwd1,String passwd2)throws InterruptedException
	{
		extent=new ExtentReports(reportpath,false);
		test=extent.startTest("Registration");
		test.assignAuthor("Author: Arunkumar");
		test.assignCategory("Sprint108");
		driver.get("http://newtours.demoaut.com/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "URL Opened");
		driver.findElement(By.linkText("REGISTER")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Register link working");
		driver.findElement(By.name("firstName")).sendKeys(name1);
		driver.findElement(By.name("lastName")).sendKeys(name2);
		driver.findElement(By.name("phone")).sendKeys(pnone);
		driver.findElement(By.id("userName")).sendKeys(username);
		driver.findElement(By.name("address1")).sendKeys(address1);
		driver.findElement(By.name("address2")).sendKeys(address2);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("postalCode")).sendKeys(pincode);
		Select countryname=new Select(driver.findElement(By.name("country")));
		countryname.selectByVisibleText(country);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(passwd1);
		driver.findElement(By.name("confirmPassword")).sendKeys(passwd2);
		test.log(LogStatus.INFO, "Registeration Form Completed");
		driver.findElement(By.name("registerr")).click();
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Registeration Done");
		driver.findElement(By.linkText("SIGN-OFF")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		test.log(LogStatus.PASS, "Registration Testcase Passed");
		extent.endTest(test);
		extent.flush();
	}
	//Data provider for Registration.
		@DataProvider(name="Regiss")
		  public Object[][] passData(){
		  config.Excelpath(injectpath,0);
		  int rows=config.Excelcount();
		  int roww=rows-1;
		  Object[][] data=new Object[roww][13];
		  for(int i=1;i<rows;i++)
		  {
			  for(int j=0;j<13;j++){
				int k=i-1;  
		 	    data[k][j]=config.Exceldata(i, j);
		   	  }
		  }
	    return data;
		  }
		
	//It will take failure screenshots
	      
		@AfterMethod
			public void tearDown(ITestResult result) throws InterruptedException{
			if(ITestResult.FAILURE==result.getStatus())
			{
			config.captureScreenshot(driver,result.getName());
			String image=test.addScreenCapture(Attachpath+"\\Screenshots\\"+result.getName()+".jpg");
			test.log(LogStatus.FAIL, result.getName(), image);
			Thread.sleep(5000);
			}
			extent.endTest(test);
			extent.flush();
			}

	@AfterClass
	public void closebrowser()
	{
	driver.close();	
	}

}
