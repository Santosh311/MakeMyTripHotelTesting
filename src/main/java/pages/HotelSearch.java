package pages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import base.Base;
import pOM.SearchPageObjects;;

public class HotelSearch extends Base
{	
	
	//Opening & Validating URL
	public void openurl() throws IOException, InterruptedException
	{
		driver.get(prop.getProperty("url"));
		waitForPageLoad();
		if(driver.getCurrentUrl().contains(prop.getProperty("url")))
			updateReport("pass", "Homepage", k++);
		else
			updateReport("fail", "Homepage", k++);
	}
	
	//Selecting & Validating the city input field
	public void citySelection(String city) throws InterruptedException, IOException
	{
		try 
		{
			driver.findElement(SearchPageObjects.loginPopup).click();
		}
		catch(Exception e)
		{
			driver.findElement(SearchPageObjects.hotelButton).click();
		}
		finally
		{
			driver.findElement(SearchPageObjects.hotelButton).click();				//Select hotel icon
			waitForPageLoad();
			if(driver.getCurrentUrl().contains("hotels/"))
				updateReport("pass", "Hotel Search Page", k++);
			else
				updateReport("fail", "Hotel Search Page", k++);
			
			driver.findElement(SearchPageObjects.cityBox).click();
			waitload(2);
			driver.findElement(SearchPageObjects.cityValue).sendKeys(city);	// Giving city as input to the city field
			waitload(3);

			driver.findElement(SearchPageObjects.cityOption).click();
			waitload(2);
			driver.findElement(SearchPageObjects.checkInBox).sendKeys(Keys.ESCAPE);
			
			if(driver.findElement(SearchPageObjects.cityinput).getAttribute("value").toLowerCase().contains(city))
				updateReport("pass", "City", k++);
			else
				updateReport("fail", "City", k++);
			
		}
	}
	
	//Selecting & Validating Checkin & Checkout date fields
	public void dateSelection() throws InterruptedException, IOException
	{
		Calendar cal=Calendar.getInstance();
		String today=new SimpleDateFormat("MMM dd yyyy").format(cal.getTime());
		cal.add(Calendar.DATE,31);
		String out=new SimpleDateFormat("MMM dd yyyy").format(cal.getTime());
		
		String checkin=prop.getProperty("checkin");
		String checkout=prop.getProperty("checkout");
		
		By trialcheckInDate = By.xpath("//div[contains(@aria-label,'"+today+"')]");
		By trialCheckOutDate = By.xpath("//div[contains(@aria-label,'"+out+"')]");
		By actualCheckInDate = By.xpath("//div[contains(@aria-label,'"+checkin+"')]");
		By actualCheckOutDate = By.xpath("//div[contains(@aria-label,'"+checkout+"')]");
		
		driver.findElement(SearchPageObjects.checkInBox).click();
		driver.findElement(trialcheckInDate).click();	//Selecting check in date (change required)
		waitload(2);
		driver.findElement(trialCheckOutDate).click();
		String range=driver.findElement(SearchPageObjects.dateErrorMsg).getAttribute("style");
		if(range.equalsIgnoreCase("display: block;"))
			updateReport("pass", "Date Error", k++);
		else
			updateReport("fail", "Date Error", k++);
				
		waitload(2);			
		
		if(range.equalsIgnoreCase("display: block;"))
		{
			driver.findElement(SearchPageObjects.checkInBox).click();
			waitload(2);
			driver.findElement(actualCheckInDate).click();	//Selecting check in date
			
			if(driver.findElement(SearchPageObjects.calCheckIn).getAttribute("aria-label").contains(checkin))
				updateReport("pass", "Check-In", k++);
			else
				updateReport("fail", "Check-In", k++);
			
			waitload(2);
			driver.findElement(actualCheckOutDate).click();		//Selecting checkout date
			waitload(1);
			driver.findElement(SearchPageObjects.checkOutBox).click();
			
			if(driver.findElement(SearchPageObjects.calCheckOut).getAttribute("aria-label").contains(checkout))
				updateReport("pass", "Check-Out", k++);
			else
				updateReport("fail", "Check-Out", k++);
			
			driver.findElement(SearchPageObjects.checkOutBox).sendKeys(Keys.ESCAPE);
		}
		takeScreenShot((k++)+".CheckIn & Checkout.png");
	}
	
	//Selecting & Validating rooms & guest fields
	public void roomSelection() throws InterruptedException, IOException ,NumberFormatException
	{
		int noofAdults=Integer.parseInt(prop.getProperty("noofAdults"));
		int noofChild=Integer.parseInt(prop.getProperty("noofChild"));
		int noofRooms=Integer.parseInt(prop.getProperty("noofRooms"));
		
		By noOfAdults = By.xpath("//li[@data-cy='adults-"+noofAdults+"']");
		By noOfChildren = By.xpath("//li[@data-cy='children-"+noofChild+"']");
		By roomRemoveButton = By.xpath("//a[@data-cy='removeButton-2']");
		By reselectNoOfAdults = By.xpath("//li[@data-cy='adults-"+(noofAdults+2)+"']");
		By lastroom = By.xpath("//p[@data-cy='roomNum"+noofRooms+"']");
		
		try
		{
			driver.findElement(SearchPageObjects.roomBox).click();
			driver.findElement(SearchPageObjects.adults12).click();
			driver.findElement(SearchPageObjects.children1).click();
			driver.findElement(SearchPageObjects.childAgeSelectBox).click();								//Room 1 details
			driver.findElement(SearchPageObjects.childAge8).click();
		}
		catch(Exception e)
		{
			if(driver.findElement(SearchPageObjects.roomErrorMsg).getText().equals("Upto 12 guests allowed in one room."))
				updateReport("pass", "Rooms&Guests Error", k++);
			else
				updateReport("fail", "Rooms&Guests Error", k++);
		}
		
		finally
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			int childAge=1;
			for(int i=0;i<noofRooms-1;i++)
			{
				driver.findElement(SearchPageObjects.addAnotherRoomButton).click();
				waitload(1);
				
				if(i==0)updateReport("pass", "Add Rooms&Guests", k++);

				
				driver.findElement(noOfAdults).click();
				driver.findElement(noOfChildren).click();							//Room 2 details
				for(int j=0;j<noofChild;j++)
				{
					By childAgeSelect = By.xpath("(//label[@class='lblAge'])["+(j+1)+"]");
					By childAgeSelectBoxes = By.xpath("//select[@data-cy='childAge-"+j+"'and@id='"+j+"']");
					By childAgeSelectValue = By.xpath("(//option[@data-cy='childAgeValue-"+childAge+"'])["+(j+1)+"]");
					
					driver.findElement(childAgeSelect).click();
					waitload(2);
					driver.findElement(childAgeSelectBoxes);
					driver.findElement(childAgeSelectValue).click();
					childAge++;
				}

				jse.executeScript("window.scrollBy(0,80)");
				
				driver.findElement(SearchPageObjects.roomBottomPane).click();
				waitload(2);
				if(childAge>=12)
					childAge=2;
				
				if(i<(noofRooms-2))takeScreenShot((k++)+".Room "+(i+2)+".png");
			}
			
			String Rooms=Integer.toString(noofRooms);
			if(driver.findElement(lastroom).getText().contains(Rooms))
				updateReport("pass", "Last Rooms&Guests", k++);
			else
				updateReport("fail", "Last Rooms&Guests", k++);
			
			jse.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
			waitload(2);
			if(noofRooms>2)
			for(int n=2;n<noofRooms;n++)
			{
				driver.findElement(roomRemoveButton).click();
			}
			
			driver.findElement(SearchPageObjects.roomNo2).click();			//Switch to Room 1
			driver.findElement(SearchPageObjects.roomEditButton).click();			//Edit button
			driver.findElement(reselectNoOfAdults).click();		//Changing 1st room adults
			takeScreenShot((k++)+".Room Remove and Edit.png");
			waitload(3);
			driver.findElement(SearchPageObjects.roomBottomPane).click();
			driver.findElement(SearchPageObjects.roomApplyButton).click();		//Apply button
			takeScreenShot((k++)+".Room Apply.png");
			jse.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
		}
	}
	
	//Selecting & Validating Travelling input field
	public void travelType() throws InterruptedException, IOException
	{
		By travelPurposeValue = By.xpath("//li[text()='"+prop.getProperty("travelType")+"']");
		driver.findElement(SearchPageObjects.travelPurposeBox).click();		//Travelling for field
		driver.findElement(travelPurposeValue).click();
		if(driver.findElement(SearchPageObjects.travelType).getAttribute("value").equals(prop.getProperty("travelType")))
			updateReport("pass", "Travelling For", k++);
		else
			updateReport("fail", "Travelling For", k++);
		
		waitload(1);
	}
	
	//Clicking & Validating Search Button
	public void search() throws InterruptedException, IOException
	{
		driver.findElement(SearchPageObjects.searchButton).click();		//Search Button
		waitForPageLoad();
		if(driver.getCurrentUrl().contains("hotel-listing"))
			updateReport("pass", "Hotel List Page", k++);
		else
			updateReport("fail", "Hotel List Page", k++);
	}
	
}
