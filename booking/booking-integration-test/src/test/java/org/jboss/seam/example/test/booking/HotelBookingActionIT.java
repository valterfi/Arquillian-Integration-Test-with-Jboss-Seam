package org.jboss.seam.example.test.booking;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.annotations.In;
import org.jboss.seam.example.booking.Hotel;
import org.jboss.seam.example.booking.HotelBooking;
import org.jboss.seam.example.booking.User;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HotelBookingActionIT {
	
	@In(create = true)
	private User user;
	
	@In(required=true)
	private Hotel hotel;
	
	@EJB(mappedName="java:global/seam-booking/booking-ejb/HotelBookingAction!org.jboss.seam.example.booking.HotelBooking")
	private HotelBooking hotelBooking;
	
	@Test
	@OperateOnDeployment("seam-booking.ear")
	public void teste() {
		assertTrue(hotelBooking != null);
		assertFalse(hotelBooking.isBookingValid());
	}

}
