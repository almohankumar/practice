package com.mohan.practice.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohan.practice.api.BookingService;
import com.mohan.practice.config.CommonConfig;
import com.mohan.practice.lib.YamlReader;
import com.mohan.practice.objects.DAO.Booking;
import com.mohan.practice.objects.DAO.BookingResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Log4j2
public class BookingSteps {

    private ObjectMapper objectMapper;

    private Booking booking;

    private int bookingId;

    private String authToken;

    public BookingSteps(){
        objectMapper = new ObjectMapper();
    }


    @Autowired
    BookingService bookingService;

    @Autowired
    CommonConfig commonConfig;



    @Given("I make a post call to {string} as {string}")
    public void iMakeAPostCallToAs(String resource, String user) {

       authToken = bookingService.getAuthToken(resource,user);

    }

    @Given("I make a get call to the {string}")
    public void iMakeAGetCallToThe(String resource) {

        String createdResource = resource+"/"+String.valueOf(bookingId);
        bookingService.getBooking(createdResource);
    }

    @Given("I make a post call to the {string}")
    public void iMakeAPostCallToThe(String resource) {

        Path path = Paths.get(commonConfig.getBasePath(),commonConfig.getFilePath());

        String filePath = path.toString();
        String fileName = "Booking.yaml";

        booking = new Booking();

        booking = (Booking) YamlReader.readYamlAsObject(filePath+"\\"+fileName,Booking.class,booking);

        bookingService.createBooking(resource,booking);

    }



    @And("I verify that the booking is created")
    public void iVerifyThatTheBookingIsCreated() {

        BookingResponse bookingResponse = new BookingResponse();
        String responseString = bookingService.getResponse().getBody().asString();
        log.info("Booking Creation Response:\n"+responseString);
        try {
            bookingResponse = objectMapper.readValue(responseString, BookingResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        bookingId = bookingResponse.getBookingid();

        validateBooking(bookingResponse);

    }


    private void validateBooking(BookingResponse bookingResponse){

        Assert.assertEquals(booking.getFirstname(),bookingResponse.getBooking().getFirstname());
        Assert.assertEquals(booking.getLastname(),bookingResponse.getBooking().getLastname());
        Assert.assertEquals(booking.getTotalprice(),bookingResponse.getBooking().getTotalprice());
        Assert.assertEquals(booking.isDepositpaid(),bookingResponse.getBooking().isDepositpaid());
        Assert.assertEquals(booking.getAdditionalneeds(),bookingResponse.getBooking().getAdditionalneeds());
        Assert.assertEquals(booking.getBookingDates().getCheckin(),bookingResponse.getBooking().getBookingDates().getCheckin());
        Assert.assertEquals(booking.getBookingDates().getCheckout(),bookingResponse.getBooking().getBookingDates().getCheckout());
    }

    private void validateBooking(Booking actualBooking){

        Assert.assertEquals(booking.getFirstname(),actualBooking.getFirstname());
        Assert.assertEquals(booking.getLastname(),actualBooking.getLastname());
        Assert.assertEquals(booking.getTotalprice(),actualBooking.getTotalprice());
        Assert.assertEquals(booking.isDepositpaid(),actualBooking.isDepositpaid());
        Assert.assertEquals(booking.getAdditionalneeds(),actualBooking.getAdditionalneeds());
        Assert.assertEquals(booking.getBookingDates().getCheckin(),actualBooking.getBookingDates().getCheckin());
        Assert.assertEquals(booking.getBookingDates().getCheckout(),actualBooking.getBookingDates().getCheckout());
    }


    @And("I verify the retrieved booking")
    public void iVerifyTheRetrievedBooking() {
        Booking retrieved = new Booking();
        String responseString = bookingService.getResponse().getBody().asString();
        log.info("Booking Retrieval Response:\n"+responseString);
        try {
            retrieved = objectMapper.readValue(responseString, Booking.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        validateBooking(retrieved);
    }

    @When("I make a put call to the {string}")
    public void iMakeAPutCallToThe(String resource) {

        String resourceToUpdate = resource+"/"+String.valueOf(bookingId);

        booking.setTotalprice(555);

        bookingService.updateBooking(resourceToUpdate,booking,authToken);

    }

    @And("I verify that the booking is updated")
    public void iVerifyThatTheBookingIsUpdated() {

        Booking updatedBooking = new Booking();
        String responseString = bookingService.getResponse().getBody().asString();
        log.info("Booking Update Response:\n"+responseString);
        try {
            updatedBooking = objectMapper.readValue(responseString, Booking.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        validateBooking(updatedBooking);
    }

    @When("I make a delete call to the {string}")
    public void iMakeADeleteCallToThe(String resource) {
        String resourceToUpdate = resource+"/"+String.valueOf(bookingId);
        bookingService.deleteBooking(resourceToUpdate,authToken);

    }

    @Then("I verify that the response code is {int}")
    public void iVerifyThatTheResponseCodeIs(int expected) {

        int actual = bookingService.getResponse().getStatusCode();
        Assert.assertEquals(actual,expected,"Response codes doesn't match");

    }

    @When("I make a put call to the {string} using an invalid token")
    public void iMakeAPutCallToTheUsingAnInvalidToken(String resource) {

        String resourceToUpdate = resource+"/"+String.valueOf(bookingId);

        booking.setTotalprice(555);

        bookingService.updateBooking(resourceToUpdate,booking,"12345678");
    }



}
