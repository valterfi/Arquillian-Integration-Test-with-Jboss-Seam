package org.jboss.seam.example.test.booking;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.annotations.In;
import org.jboss.seam.example.booking.BookingList;
import org.jboss.seam.example.booking.User;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BookingListActionIT {
	
	@In(create = true)
	private User user;
	
	@In(create = true)
	private BookingList bookingList;
	
	@Test
	@OperateOnDeployment("seam-booking.ear")
	public void bookingTest() {
		assertTrue(bookingList != null);
		assertTrue(bookingList.getBooking() == null);
	}

}
