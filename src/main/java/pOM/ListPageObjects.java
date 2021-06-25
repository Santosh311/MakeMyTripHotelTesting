package pOM;

import org.openqa.selenium.By;

public class ListPageObjects 
{
	public static By sortOption = By.xpath("//span[@class='customSelectTitle blueText latoBold']");
	public static By minPriceSlider = By.xpath("(//div[@class='input-range__slider'])[1]");
	public static By maxPriceSlider = By.xpath("(//div[@class='input-range__slider'])[2]");
	public static By firstFilter = By.xpath("(//ul[@class='filterList'])[1]//li[1]");
	public static By applyPriceFilter = By.xpath("//ul[@class='appliedFilters']/li[1]/span");
	public static By minValue = By.xpath("//span[@class='minValue']");
	public static By maxValue = By.xpath("//span[@class='maxValue']");
	public static By pricePerNight = By.xpath("(//*[contains(@class,'appendBottom15 makeFlex hrtlCenter')])[2]");
	public static By applyFilterLink = By.xpath("//ul[@class='appliedFilters']/li[2]/a");
	public static By applyedFilterText = By.xpath("//ul[@class='appliedFilters']/li[2]/span");
	public static By fList = By.xpath("//ul[@class='filterList']");
	public static By propertySearch = By.xpath("//input[contains(@placeholder,'Search location')]");
	public static By clearButton = By.xpath("//a[text()='Clear']");
	public static By openViewMapButton = By.xpath("//a[@class='mapCont listingPgMapImg']");
	public static By map = By.xpath("//div[@class='listingMapContainer']");
	public static By areaGuideTab = By.xpath("//div[@class='listingAreaGuide']/a[text()='AREA GUIDE']");
	public static By viewMapPriceSlider = By.xpath("(//div[@role='slider'])[4]");
	public static By popularFilterBox = By.xpath("(//div[@id='POPULAR'])[2]//span");
	public static By mapFilterlist = By.xpath("//ul[@class='ar__tags']");
	public static By mapAreaTag = By.xpath("//div[@class='poi_htlTag--text']");
	public static By hotelsTab = By.xpath("//div[@class='listingAreaGuide']/a[text()='HOTELS']");
	public static By chooseHotel1 = By.xpath("//div[@class='hotelListingItem ']");
	public static By closeViewMapButton = By.xpath("//span[@class='listingMapClose']");
	public static By chooseHotel2 = By.xpath("(//div[@class='makeFlex flexOne padding20 relative lftCol'])[2]");
	public static By hotelTitle = By.xpath("(//div[@class='makeFlex flexOne padding20 relative lftCol'])[2]//p/span");
	public static By hotelNameNewWindow = By.xpath("//h1[@id='detpg_hotel_name']");
}
