package pOM;

import org.openqa.selenium.By;

public class SearchPageObjects
{
	public static By loginPopup = By.xpath("//*[@class='loginModal displayBlock modalLogin dynHeight personal ']");
	public static By hotelButton = By.xpath("//span[text()='Hotels']");
	public static By cityBox = By.xpath("//label[@for='city']");
	public static By cityValue = By.xpath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']");
	public static By cityOption = By.xpath("(//li[@role='option']//span[text()='City'])[1]");
	public static By cityinput = By.xpath("//input[@id='city']");
	public static By dateErrorMsg = By.xpath("//p[@id='range_error']");
	public static By checkInBox = By.xpath("//label[@for='checkin']");
	public static By checkOutBox = By.xpath("//label[@for='checkout']");
	public static By calCheckIn = By.xpath("//div[contains(@class,'Day--start')]");
	public static By calCheckOut = By.xpath("//div[contains(@class,'Day--end')]");
	public static By roomBox = By.xpath("//label[@for='guest']");
	public static By roomRow = By.xpath("//div[@class='addRoomRow']");
	public static By adults12 = By.xpath("//li[@data-cy='adults-12']");
	public static By children1 = By.xpath("//li[@data-cy='children-1']");
	public static By childAgeSelectBox = By.xpath("//label[@class='lblAge']");
	public static By childAge8 = By.xpath("//option[@data-cy='childAgeValue-8']");
	public static By roomErrorMsg = By.xpath("//p[@data-cy='numOfGuestError']");
	public static By addAnotherRoomButton = By.xpath("//button[@data-cy='addAnotherRoom']");
	public static By roomBottomPane = By.xpath("//div[@class='roomsGuestsBottom']");
	public static By roomNo2 = By.xpath("//p[@data-cy='roomNum2']");
	public static By roomEditButton = By.xpath("//a[@data-cy='editButton-0']");
	public static By editedAdults = By.xpath("//li[contains(@data-cy,'adults') and @class='selected'");
	public static By roomApplyButton = By.xpath("//button[@data-cy='submitGuest']");
	public static By travelPurposeBox = By.xpath("//div[@class='hsw_inputBox travelFor  ']");
	public static By travelType = By.xpath("//input[@id='travelFor']");
	public static By searchButton = By.xpath("//button[@data-cy='submit']");
}
