package pOM;

import org.openqa.selenium.By;

public class DetailsPageObjects 
{
	public static By tabs = By.xpath("//ul[@id='navSticky']/li");
	public static By hotelNameNewWindow = By.xpath("//h1[@id='detpg_hotel_name']");
	public static By about = By.xpath("//p[@class='latoBlack font22 blackText appendBottom5']");
	public static By featureButtons = By.xpath("//ul[@class='roomIncludesTag']/li");
	public static By feature = By.xpath("//li[@class='selected staycation__deal']");
	public static By facilities = By.xpath("(//h2[@class='txtHeading'])[1]");
	public static By selectedTab = By.xpath("//a[@class='navLink active']");
	public static By landmarkButtons = By.xpath("//ul[@class='arroundHtl__list']/li");
	public static By landmarkSearch = By.xpath("//input[contains(@placeholder,'landmarks')]");
	public static By removeLandmarkSearch = By.xpath("//ul[@class='poiTags']/li");
	public static By removeLandmark = By.xpath("//ul[@class='poiTags']/li/span");
	public static By reviewSort = By.id("sortBy");
	public static By reviewCategoriesList = By.xpath("//ul[@class='latoBold makeFlex usrRevwHdr__tabs']/li");
	public static By closeSurveyBtn = By.xpath("//i[@class='wewidgeticon we_close icon-large']");
	public static By bookComboButton = By.xpath("(//span[@class='primaryBtn overlapBtn'])[1]");
	public static By bookRoomButton = By.xpath("(//a[text()='SELECT ROOM'])[1]");
	public static By review = By.xpath("//p[@class='rvHeader__heading']");
}
