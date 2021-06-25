package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;
import pOM.DetailsPageObjects;

public class HotelDetails extends Base
{
	//Selecting & Navigating through various hotel tabs 
	public void selectHotelTabs(String city) throws InterruptedException, IOException
	{
		waitForPageLoad();
		List<WebElement> hotelTabs = driver.findElements(DetailsPageObjects.tabs);
		for(int m=0;m<hotelTabs.size();m++)
		{
			waitForPageLoad();
			hotelTabs.get(m).click();
			switch(m)
			{
			case 0:
			{
				//About the Hotel
				waitForPageLoad();
				if(driver.findElement(DetailsPageObjects.about).getText().equals("About "+driver.findElement(DetailsPageObjects.hotelNameNewWindow).getText()))
					updateReport("pass","About The Hotel", k++);
				else
					updateReport("fail","About The Hotel", k++);
				break;
			}
			case 1:
			{
				//Book Hotel
				waitForPageLoad();
				List<WebElement> features = driver.findElements(DetailsPageObjects.featureButtons);
				for(int n=0;n<features.size();n++)
				{
					features.get(n).click();
					waitForPageLoad();
					if(n==0)
					{
						if(driver.findElement(DetailsPageObjects.feature).getText().equals(features.get(n).getText()))
							updateReport("pass","Room Features Tab - "+(n+1), k++);
						else
							updateReport("fail","Room Features Tab - "+(n+1), k++);
					}
					if(n>0)
					{
						if(driver.findElement(DetailsPageObjects.feature).getText().equalsIgnoreCase(features.get(n).getText()))
							takeScreenShot((k++)+".Room Features Tab - "+(n+1)+".png");
					}
					features.get(n).click();
					waitForPageLoad();
				}
				waitForPageLoad();
				break;
			}
			case 2:
			{
				//Hotel Facilities
				waitForPageLoad();
				
				if(driver.findElement(DetailsPageObjects.facilities).getText().toLowerCase().contains(driver.findElement(DetailsPageObjects.selectedTab).getText().toLowerCase()))
					updateReport("pass","Facilities Tab", k++);
				else
					updateReport("fail","Facilities Tab", k++);
				break;
			}
			case 3:
			{
				//Location
				waitForPageLoad();
				List<WebElement> landmarks = driver.findElements(DetailsPageObjects.landmarkButtons);
				landmarks.get(2).click();
				waitload(2);
				String landmarkSearch = driver.findElement(DetailsPageObjects.removeLandmarkSearch).getText();
				if(landmarkSearch.contains("..."))
					landmarkSearch=landmarkSearch.substring(0, (landmarkSearch.length()-3));
				
				if(landmarks.get(2).getText().contains(landmarkSearch))
					updateReport("pass","Location Tab - Location filter", k++);
				else
					updateReport("fail","Location Tab - Location filter", k++);
				
				landmarks.get(2).click();
				waitload(2);
				
				driver.findElement(DetailsPageObjects.landmarkSearch).sendKeys(city);
				waitload(3);
				driver.findElement(DetailsPageObjects.landmarkSearch).sendKeys(Keys.ARROW_DOWN);
				waitForPageLoad();
				driver.findElement(DetailsPageObjects.landmarkSearch).sendKeys(Keys.ENTER);
				waitForPageLoad();
				
				if(driver.findElement(DetailsPageObjects.removeLandmarkSearch).getText().toLowerCase().contains(city))
					updateReport("pass","Location Tab - Location Search", k++);
				else
					updateReport("fail","Location Tab - Location Search", k++);
				driver.findElement(DetailsPageObjects.removeLandmark).click();
				waitForPageLoad();
				break;
			}
			case 4:
			{
				//Ratings & Reviews
				waitForPageLoad();
				Select sortReviews = new Select(driver.findElement(DetailsPageObjects.reviewSort));
				sortReviews.selectByIndex(2);
				waitForPageLoad();
				if(sortReviews.getFirstSelectedOption().getText().contains("Positive"))
					updateReport("pass","Review Sort", k++);
				else
					updateReport("fail","Review Sort", k++);
				
				List<WebElement> reviewCategories = driver.findElements(DetailsPageObjects.reviewCategoriesList);
				for(int n=0;n<reviewCategories.size();n++)
				{
					By reviewCategory = By.xpath("//ul[@class='latoBold makeFlex usrRevwHdr__tabs']/li["+(n+1)+"]");
					
					
					reviewCategories.get(n).click();
					waitload(1);
					if(n==0)
					{
					if(driver.findElement(reviewCategory).getText().equalsIgnoreCase(reviewCategories.get(n).getText()))
						updateReport("pass","Reviews Tab - Category - "+driver.findElement(reviewCategory).getText(), k++);
					else
						updateReport("fail","Reviews Tab - Category - "+driver.findElement(reviewCategory).getText(), k++);
					}
					if(n>0)
						takeScreenShot((k++)+".Reviews Tab - Category - "+driver.findElement(reviewCategory).getText()+".png");
					reviewCategories.get(n).click();
					waitload(1);
				}
				
				waitForPageLoad();
				List<WebElement> reviewFilters = driver.findElements(By.xpath("//ul[@class='rtngCard__tags']/li"));
				for(int n=0;n<reviewFilters.size();n++)
				{
					By reviewFilter = By.xpath("//ul[@class='rtngCard__tags']/li["+(n+1)+"]");
					
					reviewFilters.get(n).click();
					waitload(1);
					if(n==0)
					{
					if(driver.findElement(reviewFilter).getText().equalsIgnoreCase(reviewFilters.get(n).getText()))
						updateReport("pass","Reviews Tab - Filter - "+(n+1), k++);
					else
						updateReport("fail","Reviews Tab - Filter - "+(n+1), k++);
					}
					if(n>0)
						takeScreenShot((k++)+".Reviews Tab - Filter - "+(n+1)+".png");
					reviewFilters.get(n).click();
					waitload(1);
				}
				
				waitForPageLoad();
				break;
				
			}
			
			}
		}
		
		try
		{
			//Close survey, if popup appears
			driver.switchTo().frame("survey-frame-~162i8b6");
			driver.findElement(DetailsPageObjects.closeSurveyBtn).click();
			driver.switchTo().parentFrame();
		}
		catch(Exception e)
		{}
	}
		
	//Selecting & Validating room selection option
	public void selectRoom() throws InterruptedException, IOException
	{
		Actions action = new Actions(driver);
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 15);
			WebElement bookCombo = driver.findElement(DetailsPageObjects.bookComboButton);
			action.moveToElement(bookCombo);
			waitForPageLoad();
			action.click().build().perform();
			waitForPageLoad();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(DetailsPageObjects.review)));
			if(driver.getCurrentUrl().contains("hotel-review"))
				updateReport("pass","Book Combo Button", k++);
			else
				updateReport("fail","Book Combo Button", k++);
			waitForPageLoad();
		}
		catch(NoSuchElementException e)
		{
			WebDriverWait wait = new WebDriverWait(driver, 15);
			WebElement bookRoom = driver.findElement(DetailsPageObjects.bookRoomButton);
			action.moveToElement(bookRoom);
			waitForPageLoad();
			action.click().build().perform();
			waitForPageLoad();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(DetailsPageObjects.review)));
			if(driver.getCurrentUrl().contains("hotel-review"))
				updateReport("pass","Book Room Button", k++);
			else
				updateReport("fail","Book Room Button", k++);
			waitForPageLoad();
		}
		
		waitForPageLoad();
	}
}