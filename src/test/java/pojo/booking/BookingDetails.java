package pojo.booking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pojo.booking.BookingDates;

@Data // Generates getter, setter, toString()
@NoArgsConstructor // Generate a no-arguments constructor
@AllArgsConstructor // Generate an all-arguments constructor

public class BookingDetails {
    private int id ;
    private String firstname ;
    private String lastname ;
    private int totalprice ;
    private boolean depositpaid ;
    private String additionalneeds ;
    // for the inner json it is better to create different pojo class
    private BookingDates bookingDates;




}
