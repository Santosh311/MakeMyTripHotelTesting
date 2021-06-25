package testSuite;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HotelReview;

public class HotelReviewTest
{
	@Test
	@Parameters({"city"})
	public void hotelReview() throws InterruptedException, IOException
	{
		HotelReview hr = new HotelReview();
		hr.selectTitle();
		hr.countryCode();
		hr.invalidDetails();
		hr.emptyDetails();
		hr.validDetails();
		hr.addguestdetails();
		hr.checkboxes();
		hr.paynow();
	}
}
