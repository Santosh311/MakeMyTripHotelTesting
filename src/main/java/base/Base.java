package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Base 
{
	public static WebDriver driver;	
	public static Properties prop;
	public static ExtentReports report;
	public static ExtentTest test;
	public static ExtentHtmlReporter html;
	public static int k=1;
	
	@BeforeSuite
	//Browser's driver setup and Extent report initialization
	public void setup() throws FileNotFoundException, IOException		
	{
		prop=new Properties();
		prop.load(new FileInputStream("src/main/java/config/config.properties"));
		
		if(prop.getProperty("browsername").matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(prop.getProperty("browsername").matches("chrome"))
		{
			driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		//driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		html = new ExtentHtmlReporter(prop.getProperty("reportLocation"));
		report = new ExtentReports();
		report.attachReporter(html);
	 	report.setSystemInfo("Host Name", "TestSystem");  
	 	report.setSystemInfo("Environment", "Test Env");
	 	report.setSystemInfo("User Name", "Techno Testers");
	 	   
	 	html.config().setDocumentTitle("MakeMyTrip");
	 	html.config().setReportName("Hotel Search Module");
	 	html.config().setTestViewChartLocation(ChartLocation.TOP);
	 	html.config().setTheme(Theme.STANDARD);
	}
	
	//Logging Extent report
	public void updateReport(String status,String imageName,int k) throws IOException 
	{
		if(status.equalsIgnoreCase("pass"))
		{
			test=report.createTest("Validate "+imageName);
			test.log(Status.PASS,imageName+" is as expected");
			takeScreenShot(k+"."+imageName+".png");
		}
		if(status.equalsIgnoreCase("fail"))
		{
			test=report.createTest("Validate"+imageName);
			test.log(Status.FAIL,imageName+" is not as expected");
			takeScreenShot(k+"."+imageName+".png");
		}
		if(status.equalsIgnoreCase("skip"))
		{
			test=report.createTest("Validate"+imageName);
			test.log(Status.SKIP,imageName+" is skipped");
			takeScreenShot(k+"."+imageName+".png");
		}
	}
	
	//Capturing test case's screenshot
	public void takeScreenShot(String imageName) throws IOException
	{
		File File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(File,new File(prop.getProperty("screenShotLocation")+imageName));
		test.addScreenCaptureFromPath(prop.getProperty("screenShotLocation")+imageName);
	}
	
	//Explicit wait for page loading
	public void waitForPageLoad() throws InterruptedException
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
	int i=0;
	while(i!=180)
	{
	String pagestate=(String) js.executeScript("return document.readyState;");
	if(pagestate.equals("complete"))
	{
	break;
	}
	else
	{
	waitload(1);
	}
	}
	waitload(2);
	}
	
	//Simple wait
	public void waitload(int i) throws InterruptedException
	{
		Thread.sleep(i*1000);
	}
	
	@AfterSuite
	//Closing the browser & saving the report
	public void shutdown()
	{
		driver.quit();
		report.flush();
	}
}
