package com.dakinfotech.PWCMPack;

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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Sprint108 {
	WebDriver driver;
	pwcmlibrary config=new pwcmlibrary();
	String injectpath="M:\\Study\\Selenium\\SeleniumScripts\\workspace1\\com.dakinfotech.PrototypeForPWCM\\src\\test\\resources\\Injectfile.xls";
	String Advreportpath="M:\\Study\\Selenium\\SeleniumScripts\\workspace1\\com.dakinfotech.PrototypeForPWCM\\Reporting\\Advreportpath.html";
	String Snapshotpath="M:\\Study\\Selenium\\SeleniumScripts\\workspace1\\com.dakinfotech.PrototypeForPWCM\\Screenshots\\";
	ExtentReports extent = new ExtentReports(Advreportpath, true);
	ExtentTest test;
	
	@Test(priority=0)
	public void Homepage(){
		test = extent.startTest("Homepage Verification-Sprint108");
		test.assignAuthor("Test Owner: Arun");
		test.assignCategory("ID:usrd12345","Regression Test");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://newtours.demoaut.com/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Page opened without any Error");
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
		test.log(LogStatus.PASS, "Homepage Testcase is Passed");
		extent.endTest(test);
		extent.flush();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    }
	
	//Registration Test.	  
	@Test(priority=1,dataProvider="Regiss")
	 public void Register(String name1,String name2,String pnone,String username,String address1,
			              String address2,String city,String state,String pincode,String country,
			              String email,String passwd1,String passwd2) throws InterruptedException 
			{
		    test = extent.startTest("Registration-Sprint108");
		    test.assignAuthor("Test Owner: Arun");
		    test.assignCategory("ID:usrd12346","Regression Test");
	        driver.get("http://newtours.demoaut.com/");
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	        test.log(LogStatus.INFO, "URL Ready");
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
			test.log(LogStatus.INFO, "Register Form filled");
			driver.findElement(By.name("register")).click();
			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Register complete");
			driver.findElement(By.linkText("SIGN-OFF")).click();
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			test.log(LogStatus.INFO, "Register test passed");
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
	
@AfterClass
    public void openreport()
    {
	driver.get(Advreportpath);
	}

//It will take failure screenshots
	@AfterMethod
	  public void tearDown(ITestResult result){
		  if(ITestResult.FAILURE==result.getStatus())
		  {
			  config.captureScreenshot(driver,result.getName());
			  String image=test.addScreenCapture(Snapshotpath+result.getName()+".jpg");
			  test.log(LogStatus.FAIL, result.getName(), image);
		  }
		      extent.endTest(test);
			  extent.flush();
		
	  }
	
	

}
