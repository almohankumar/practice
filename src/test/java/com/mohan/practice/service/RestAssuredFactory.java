package com.mohan.practice.service;

import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

public final class RestAssuredFactory {

    private RestAssuredFactory(){

    }

    private static ThreadLocal<RequestSpecification> requestSpecification = new ThreadLocal<>();

    private static List<RequestSpecification> storedSpecifications = new ArrayList<>();

    public static RequestSpecification getSpecification() {
        return requestSpecification.get();
    }

    public static void addSpecification(RequestSpecification requestSpecification) {
        storedSpecifications.add(requestSpecification);
        RestAssuredFactory.requestSpecification.set(requestSpecification);
    }

    public static void removeSpecification() {
        storedSpecifications.remove(requestSpecification.get());
        requestSpecification.remove();
    }

}
