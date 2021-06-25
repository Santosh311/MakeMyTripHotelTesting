package testSuite;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HotelList;

public class HotelListTest 
{
	@Test
	@Parameters({"city"})
	public void hotelList(String city) throws InterruptedException, IOException
	{
		HotelList hl= new HotelList();
		hl.sortResults();
		hl.adjustPriceSlider();
		hl.applyFilters();
		hl.searchProperty(city);
		hl.clearFilters();
		hl.openViewMap();
		hl.areaGuidePane();
		hl.hotelPane();
		hl.closeViewMap();
		hl.selectHotel();
	}
}
