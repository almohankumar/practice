package com.mohan.practice.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohan.practice.objects.DAO.Auth;
import com.mohan.practice.objects.DAO.Booking;
import com.mohan.practice.service.RestAssuredService;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookingService {

    private Response response;

    private void setResponse(Response resp){
        response = resp;
    }

    public Response getResponse(){
        return response;
    }


    public void createBooking(String resource, Booking booking){

        setResponse(RestAssuredService.makePostCall(resource,booking));

    }


    public void getBooking(String resource) {

        setResponse(RestAssuredService.makeGetCall(resource));

    }

    public void updateBooking(String resource, Booking booking, String authToken){

        String token = "token="+authToken;

        Map<String,String> headers = new HashMap<>();
        headers.put("Cookie",token);

        setResponse(RestAssuredService.makePutCallWithHeader(resource,booking,headers));

    }

    public void deleteBooking(String resource, String authToken){


        String token = "token="+authToken;

        Map<String,String> headers = new HashMap<>();
        headers.put("Cookie",token);

        setResponse(RestAssuredService.makeDeleteCallWithHeader(resource,headers));

    }

    public String getAuthToken(String resource, String user){

        ObjectMapper objectMapper = new ObjectMapper();

        Auth auth = new Auth();
        auth.setUsername(user);
        auth.setPassword("password123");


        Response response = RestAssuredService.makePostCall(resource,auth);

        TypeReference<HashMap<String,String>> typeReference = new TypeReference<HashMap<String, String>>() { };

        Map<String,String> resultMap = new HashMap<>();

        try {
            resultMap = objectMapper.readValue(response.getBody().asString(),typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!resultMap.isEmpty()){
            return resultMap.get("token");

        }else{
            return "";
        }


    }



}
