package com.dakinfotecz.pwcm;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
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

public class Sprint110 {
	
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	pwcmlibrary config=new pwcmlibrary();
	String reportpath=".\\Reporting\\Advreportpath.html";
	String injectpath=".\\src\\main\\resources\\Injectfile.xls";
	String Attachpath="C:\\Users\\348027\\git\\PrototypeForPWCM\\com.dakinfotecz.pwcm";
	
	/*    TESTTHREE    */
	@Test(priority=2,dataProvider="Regiss")		
	public void test4(String name1,String name2,String pnone,String username,String address1,
	        String address2,String city,String state,String pincode,String country,
	        String email,String passwd1,String passwd2)throws InterruptedException
	{
		extent=new ExtentReports(reportpath,false);
		test=extent.startTest("Registration For Second User");
		test.assignAuthor("Author: Arunkumar");
		test.assignCategory("Sprint110");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Browser Ready");
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
		test.log(LogStatus.INFO, "Registeration Second User Form Completed");
		driver.findElement(By.name("register")).click();
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Registeration Second User Done");
		driver.findElement(By.linkText("SIGN-OFF")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		test.log(LogStatus.PASS, "Registration Second User Testcase Passed");
		extent.endTest(test);
		extent.flush();
	}
	//Data provider for Registration.
		@DataProvider(name="Regiss")
		  public Object[][] passData(){
		  config.Excelpath(injectpath,2);
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
			public void tearDown(ITestResult result){
			if(ITestResult.FAILURE==result.getStatus())
			{
			config.captureScreenshot(driver,result.getName());
			String image=test.addScreenCapture(Attachpath+"\\Screenshots\\"+result.getName()+".jpg");
			test.log(LogStatus.FAIL, result.getName(), image);
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
