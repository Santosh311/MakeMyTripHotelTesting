package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import base.Base;
import pOM.ReviewPageObjects;

public class HotelReview  extends Base
{
	//Selecting guest title dropdown
	public void selectTitle () throws InterruptedException, IOException
	{
		Actions action = new Actions(driver);
		String title = prop.getProperty("title");
		WebElement titleDropdown = driver.findElement(ReviewPageObjects.Titlename);
		waitForPageLoad();
		action.moveToElement(titleDropdown).perform();
		Select s=new Select(titleDropdown);
		s.selectByVisibleText(title);                               //give title as  Mr,Mrs
		waitForPageLoad();
		updateReport("pass", "Guest Title", k++);
	}
	
	//Selecting country code
	public void countryCode() throws InterruptedException, IOException
	{
		String code = prop.getProperty("countrycode");
		Select countrycode=new Select(driver.findElement(ReviewPageObjects.Countrycode));
		countrycode.selectByVisibleText(code);                                    //code="India (+91)"
		waitForPageLoad();
		updateReport("pass", "Guest Country Code", k++);
	}
	
	//Validating with invalid input
	public void invalidDetails() throws InterruptedException, IOException
	{
		driver.findElement(ReviewPageObjects.FirstName).sendKeys("@123");    //give invalidfirstname
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.LastName).sendKeys("_456");    //give invalidlastname
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.Email).sendKeys("123gmail");    //give invalidemailid   
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.MobileNo).sendKeys("0abcd");                      //give mobileno
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.Screentap).click();
		waitForPageLoad();
		List<WebElement> errormsg=driver.findElements(ReviewPageObjects.Message);
		for(int i=0;i<errormsg.size();i++)
		{
			By errorMessage = By.xpath("(//p[@class='errMsg font11 redText appendTop5'])["+(i+1)+"]");
			By placeholder = By.xpath("(//p[@class='errMsg font11 redText appendTop5']//preceding-sibling::input)["+(i+1)+"]");
			if(i==0)
			{
				if(driver.findElement(errorMessage).getText().equals(errormsg.get(i).getText()))
					updateReport("pass", "Invalid Guest Details", k++);
				else
					updateReport("fail", "Invalid Guest Details", k++);
			}
			takeScreenShot((k++)+".Invalid "+driver.findElement(placeholder).getAttribute("placeholder")+".png");
		}
		
	}
	
	//Validating with empty fields
	public void emptyDetails() throws InterruptedException, IOException
	{
		driver.findElement(ReviewPageObjects.FirstName).sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.LastName).sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.Email).sendKeys(Keys.CONTROL+"A"+Keys.DELETE);
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.MobileNo).sendKeys(Keys.CONTROL+"A"+Keys.DELETE);     //give mobileno   
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.Screentap).click();
		Thread.sleep(3000);
		List<WebElement> errormsg=driver.findElements(ReviewPageObjects.Message);
		for(int i=0;i<errormsg.size();i++)
		{
			By errorMessage = By.xpath("(//p[@class='errMsg font11 redText appendTop5'])["+(i+1)+"]");
			By placeholder = By.xpath("(//p[@class='errMsg font11 redText appendTop5']//preceding-sibling::input)["+(i+1)+"]");
			if(i==0)
			{
				if(driver.findElement(errorMessage).getText().equals(errormsg.get(i).getText()))
					updateReport("pass", "Empty Guest Details", k++);
				else
					updateReport("fail", "Empty Guest Details", k++);
			}
			takeScreenShot((k++)+".Empty "+driver.findElement(placeholder).getAttribute("placeholder")+".png");
		}
		
	}
	
	//Validating with valid details
	public void validDetails() throws InterruptedException, IOException
	{
		String validfname = prop.getProperty("firstname");
		String validlname = prop.getProperty("lastname");
		String validemailId = prop.getProperty("emailId");
		String validmobileno = prop.getProperty("phoneno");
		
		driver.findElement(ReviewPageObjects.FirstName).sendKeys(validfname);    //give validfirstname
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.LastName).sendKeys(validlname);    //give validlastname
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.Email).sendKeys(validemailId);    //give validemailid    
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.MobileNo).sendKeys(validmobileno);   //give mobileno
		Thread.sleep(500);
		driver.findElement(ReviewPageObjects.Screentap).click();
		Thread.sleep(3000);
		updateReport("pass", "Valid Guest Details", k++);
	}
	
	//Selecting & Validating add guest button
	public void addguestdetails()  throws InterruptedException, IOException
	{
		int noofGuest = Integer.parseInt(prop.getProperty("noofGuest"));
		
		for(int n=0;n<noofGuest;n++)
		{
			By otherguest = By.xpath("(//li[@class='othrGuest__list--item']//p)[1]");
			driver.findElement(ReviewPageObjects.guestdetails).click();
			waitForPageLoad();
			if(n==0)
			if(driver.findElement(ReviewPageObjects.addGuestPanel).isDisplayed())
				updateReport("pass", "Add Guest Panel", k++);
			else
				updateReport("pass", "Add Guest Panel", k++);
			
			if(n>=1)
				driver.findElement(ReviewPageObjects.guestbutton).click();
			waitForPageLoad();
			driver.findElement(ReviewPageObjects.guestfirstname).sendKeys(prop.getProperty("guest"+(n+1)+"fname"));         //add guestfirstname
			driver.findElement(ReviewPageObjects.guestlastname).sendKeys(prop.getProperty("guest"+(n+1)+"lname"));          //add guest lastname
			String Guestname=prop.getProperty("guest"+(n+1)+"fname")+" "+prop.getProperty("guest"+(n+1)+"lname");
			waitForPageLoad();
			driver.findElement(ReviewPageObjects.submit).click();
			waitForPageLoad();
			driver.findElement(ReviewPageObjects.doneButton).click();
			waitForPageLoad();
			if(driver.findElement(otherguest).getText().equals(Guestname))
				takeScreenShot((k++)+".Guest -"+(n+1)+".png");
		}
	}
	
	//Selecting & Validating features checkboxes
	public void checkboxes() throws InterruptedException, IOException
	{
		List<WebElement> checkbox=driver.findElements(ReviewPageObjects.checkboxes);
	
		for(int i=0;i<checkbox.size();i++)
		{
			checkbox.get(i).click();
			waitload(1);
			if(i==0)
				updateReport("pass","CheckBox -"+(i+1),k++);
			takeScreenShot((k++)+".CheckBox"+(i+1)+".png");
			if(i==checkbox.size()-2)
			{
				driver.findElement(ReviewPageObjects.timingDropdown).click();
				waitload(1);
				driver.findElement(ReviewPageObjects.timing).click();
				waitload(1);
				takeScreenShot((k++)+".CheckBox"+(i+1)+" Dropdown.png");
			}
			checkbox.get(i).click();
			waitload(1);
		}
	}
	
	//Selecting & Validating pay now button
	public void paynow() throws InterruptedException, IOException
	{
		driver.findElement(ReviewPageObjects.pay).click();
		waitForPageLoad();
		
		if(driver.getCurrentUrl().contains("payment"))
			updateReport("pass", "Payment options", k++);
		else
			updateReport("fail", "Payment options", k++);

	}
}
