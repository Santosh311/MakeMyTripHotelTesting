package testSuite;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.io.IOException;

import pages.HotelSearch;

public class HotelSearchTest 
{
	@Test
	@Parameters({"city"})
	public void hotelSearch(String city) throws InterruptedException, IOException
	{
		HotelSearch hs=new HotelSearch();
		hs.openurl();
		hs.citySelection(city);
		hs.dateSelection();
		hs.roomSelection();
		hs.travelType();
		hs.search();
	}
}
