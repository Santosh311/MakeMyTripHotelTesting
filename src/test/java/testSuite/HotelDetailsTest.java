package testSuite;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HotelDetails;

public class HotelDetailsTest 
{
	@Test
	@Parameters({"city"})
	public void hotelDetails(String city) throws InterruptedException, IOException
	{
		HotelDetails hd=new HotelDetails();
		hd.selectHotelTabs(city);
		hd.selectRoom();
	}
}
