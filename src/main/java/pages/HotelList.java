package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import base.Base;
import pOM.ListPageObjects;;

public class HotelList extends Base
{
	//Verifying whether the results are sorted
	public void sortResults() throws InterruptedException, IOException
	{
		for(int i=4;i>0;i--)
		{
			By sortResult=By.xpath("//ul[@class='customSelectOptions latoBold']/li["+i+"]");
			waitload(2);
			driver.findElement(ListPageObjects.sortOption).click();
			waitForPageLoad();
			String title=driver.findElement(sortResult).getText();
			driver.findElement(sortResult).click();
			waitForPageLoad();
			
			if(driver.findElement(ListPageObjects.sortOption).getText().equalsIgnoreCase(title))
				updateReport("pass", title, k++);
			else
				updateReport("fail", title, k++);
		}
	}
	
	//Checking & Validating Price per night slider
	public void adjustPriceSlider() throws InterruptedException, IOException
	{
		Actions act1 = new Actions(driver);
		waitForPageLoad();
		act1.dragAndDropBy(driver.findElement(ListPageObjects.maxPriceSlider), -50, 0).build().perform();
		String maxValue=driver.findElement(ListPageObjects.maxValue).getText().substring(2);
		waitForPageLoad();
		String priceFilter = driver.findElement(ListPageObjects.applyPriceFilter).getText();
		waitForPageLoad();
		
		if(priceFilter.contains(maxValue))
			updateReport("pass", driver.findElement(ListPageObjects.pricePerNight).getText(), k++);
		else
			updateReport("fail", driver.findElement(ListPageObjects.pricePerNight).getText(), k++);
	}
	
	//Selecting & Validating filter checkboxes
	public void applyFilters() throws InterruptedException, IOException
	{
		waitForPageLoad();

		List<WebElement> filterCategories = driver.findElements(ListPageObjects.fList);
		for(int x=0;x<filterCategories.size();x++)
		{
			By filter = By.xpath("(//ul[@class='filterList'])["+(x+1)+"]/li/span[1]");
			
			List<WebElement> filterList = driver.findElements(filter);
			try
			{
				for(int y=0;y<filterList.size();y++)
				{
					int z=x+1;
					if(z>=2)z++;
					By filterTiltle = By.xpath("(//*[contains(@class,'appendBottom15 makeFlex hrtlCenter')])["+z+"]");
					if(filterList.get(y).getAttribute("class").contains("fadeEff"))
						continue;
					else
					{
						String filterName = filterList.get(y).getText();
						if(filterName.contains("..."))
							filterName=filterName.substring(0, (filterName.length()-3));
						waitForPageLoad();
						filterList.get(y).click();
						waitForPageLoad();

						if(driver.findElement(ListPageObjects.applyedFilterText).getText().contains(filterName))
							updateReport("pass", driver.findElement(filterTiltle).getText(), k++);
						else
							updateReport("fail", driver.findElement(filterTiltle).getText(), k++);
						driver.findElement(ListPageObjects.applyFilterLink).click();
						waitForPageLoad();
						break;
					}
				}
			}
				
			catch(NoSuchElementException e)
			{
				System.out.println((x+1)+e.getMessage());
			}
			catch(ElementNotInteractableException e)
			{
				System.out.println((x+1)+"Element not clickable");
			}
		}
	}
	
	//Verifying the search property input field
	public void searchProperty(String city) throws InterruptedException, IOException
	{
		
		driver.findElement(ListPageObjects.propertySearch).sendKeys(city);
		waitForPageLoad();
		driver.findElement(ListPageObjects.propertySearch).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(ListPageObjects.propertySearch).sendKeys(Keys.ENTER);
		waitForPageLoad();
		if(driver.findElement(ListPageObjects.applyedFilterText).getText().toLowerCase().contains(city))
			updateReport("pass", "Search Locality or Property", k++);
		else
			updateReport("fail", "Search Locality or Property", k++);
	}
		
	//Selecting & Validating clear filters button
	public void clearFilters() throws InterruptedException, IOException
	{
		driver.findElement(ListPageObjects.clearButton).click();		//Clear
		waitForPageLoad();
		try
		{
			driver.findElement(ListPageObjects.clearButton).click();
			updateReport("fail", "Clear Filters", k++);
		}
		catch(Exception e) 
		{
			updateReport("pass", "Clear Filters", k++);	
		}
	}
	
	//Selecting & Validating view map button
	public void openViewMap() throws InterruptedException, IOException
	{
		driver.findElement(ListPageObjects.openViewMapButton).click();  ///opening the map
		waitload(2);
		
		if(driver.findElement(ListPageObjects.map).isDisplayed())
			updateReport("pass", "Open View Map", k++);
		else
			updateReport("fail", "Open View Map", k++);
	}
	
	//Selecting & Validating area guide pane and its filters
	public void areaGuidePane() throws InterruptedException, IOException
	{
		driver.findElement(ListPageObjects.areaGuideTab).click();		//Area guide tab
		waitForPageLoad();
		
		if(driver.findElement(ListPageObjects.areaGuideTab).getAttribute("class").equalsIgnoreCase("selected"))
			updateReport("pass", driver.findElement(ListPageObjects.areaGuideTab).getText(), k++);
		else
			updateReport("fail", driver.findElement(ListPageObjects.areaGuideTab).getText(), k++);

		
		Actions act2 = new Actions(driver);
		act2.dragAndDropBy(driver.findElement(ListPageObjects.viewMapPriceSlider), -50, 0).build().perform();
		
		waitload(2);
		takeScreenShot((k++)+".Open View Map Price Per Night.png");
		
		driver.findElement(ListPageObjects.popularFilterBox).click();		//Popular Filters dropdown
		waitForPageLoad();
		takeScreenShot((k++)+".View Map Popular Filter Box.png");
		
		driver.findElement(ListPageObjects.popularFilterBox).click();
		waitForPageLoad();
			
		List<WebElement> filterlst = driver.findElements(ListPageObjects.mapFilterlist);
		
		for(int x=0;x<filterlst.size();x++)
		{
			By mapFilter = By.xpath("(//ul[@class='ar__tags'])["+(x+1)+"]/li[1]");
			By mapFilterTitle = By.xpath("(//div[@class='arHeading'])["+(x+1)+"]");
			driver.findElement(mapFilter).click();		//Area guide tab
			waitload(2);
			String mapTag = driver.findElement(ListPageObjects.mapAreaTag).getText();
			if(mapTag.contains("..."))
				mapTag=mapTag.substring(0, (mapTag.length()-3));
			
			if(driver.findElement(mapFilter).getText().contains(mapTag))
				updateReport("pass", "View Map "+driver.findElement(mapFilterTitle).getText(), k++);
			else
				updateReport("fail", "View Map "+driver.findElement(mapFilterTitle).getText(), k++);
			
			driver.findElement(mapFilter).click();		//Area guide tab
			waitload(2);
		}
	}
	
	//Selecting & Validating hotel panel
	public void hotelPane() throws InterruptedException, IOException
	{
		driver.findElement(ListPageObjects.hotelsTab).click();			//Hotels tab
		waitForPageLoad();
		
		if(driver.findElement(ListPageObjects.hotelsTab).getAttribute("class").equalsIgnoreCase("selected"))
			updateReport("pass", driver.findElement(ListPageObjects.hotelsTab).getText(), k++);
		else
			updateReport("pass", driver.findElement(ListPageObjects.hotelsTab).getText(), k++);

		driver.findElement(ListPageObjects.chooseHotel1).click();				//Select hotel in view map
		waitForPageLoad();																			
		
		ArrayList<String> lst=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(lst.get(1));
		waitForPageLoad();
		
		if(driver.getCurrentUrl().contains("hotel-details"))
			updateReport("pass", "Hotel Detail through Map", k++);
		else
			updateReport("fail", "Hotel Detail through Map", k++);

		driver.close();																			//Come back to 2nd page
		driver.switchTo().window(lst.get(0));
		waitForPageLoad();
	}
	
	//Selecting & Validating close view map button
	public void closeViewMap() throws InterruptedException, IOException
	{
		driver.findElement(ListPageObjects.closeViewMapButton).click();   ///closing the map
		waitForPageLoad();
		try
		{
			driver.findElement(ListPageObjects.map).click();
			updateReport("fail", "Close View Map", k++);
		}
		catch(Exception e)
		{
			updateReport("pass", "Close View Map", k++);
		}

	}
	
	//Selecting & Validating hotel selection in hotels list page
	public void selectHotel() throws InterruptedException, IOException
	{
		driver.findElement(ListPageObjects.chooseHotel2).click();   //selecting the hotel on 2nd main page
		String hotelName = driver.findElement(ListPageObjects.hotelTitle).getText();
		ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		waitForPageLoad();
		
		if(driver.findElement(ListPageObjects.hotelNameNewWindow).getText().equals(hotelName))
			updateReport("pass", "Hotel Details Through Main Menu", k++);
		else
			updateReport("fail", "Hotel Details Through Main Menu", k++);
	}
		
	
}
