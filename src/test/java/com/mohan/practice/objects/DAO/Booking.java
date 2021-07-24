package com.mohan.practice.objects.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingDates;
    private String additionalneeds;
}
