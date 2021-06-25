package pOM;

import org.openqa.selenium.By;

public class ReviewPageObjects
{
	public static By Titlename = By.id("title");
	public static By Countrycode = By.id("mCode");
	public static By FirstName = By.id("fName");
	public static By LastName = By.id("lName");
	public static By Email = By.id("email");
	public static By MobileNo = By.id("mNo");
	public static By Message = By.xpath("//p[@class='errMsg font11 redText appendTop5']");
	public static By Screentap = By.xpath("//div[@class='guestDtls__add']");
	public static By guestdetails=By.xpath("//a[@class='guestDtls__addBtn appendRight5']");
	public static By addGuestPanel =By.xpath("//div[@class='cm__modal']");
	public static By guestbutton=By.xpath("//a[@class='guest__addBtn']");
	public static By guestfirstname=By.xpath("//input[@name='firstName']");
	public static By guestlastname=By.xpath("//input[@name='lastName']");
	public static By submit=By.xpath("//a[@class='addGuestForm__submit']");
	public static By doneButton=By.xpath("//a[@class='addEditGuest__done ']");
	public static By checkboxes=By.xpath("//span[@class='checkmarkOuter']");
	public static By pay=By.xpath("//a[@class='btnContinuePayment primaryBtn capText  ']");
	public static By timingDropdown = By.xpath("//span[@class='timng__heading down ']");
	public static By timing = By.xpath("//ul[@class='timng__list']//li[6]");
}
