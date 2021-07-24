package com.mohan.practice.service;

import com.mohan.practice.errors.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Component
@Scope("cucumber-glue")
public class RestAssuredService {


    private static String baseUri;



    public void configureBaseUri(String serviceUrl) {

        RequestSpecification requestSpecification;
        requestSpecification = RestAssured.with();
        requestSpecification.given().contentType(ContentType.JSON).baseUri(serviceUrl);
        baseUri = serviceUrl;
        RestAssuredFactory.addSpecification(requestSpecification);

    }

    public static Response makeGetCall(String resource) {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().get(resource);
        validateResponse(response,resource);
        return response;
    }

    public static Response makeGetCallWithParameters(String resource, List<String> Params)  {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().params(Params.get(0), Params.get(1)).get(resource);
        validateResponse(response,resource);
        return response;

    }

    public static Response makeGetCallWithHeadersAndParams(String resource, Map<String, String> headers, List<String> Params) {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().headers(headers).params(Params.get(0), Params.get(1)).get(resource);
        validateResponse(response,resource);
        return response;

    }

    public static Response makeGetCallWithHeaders(String resource, Map<String, String> headers)  {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().headers(headers).get(resource);
        validateResponse(response,resource);
        return response;
    }

    public static Response makePutCall(String resource, Object obj) {

        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().body(obj).put(resource);
        validateResponse(response,resource);
        return response;
    }

    public static Response makePutCallWithHeader(String resource, Object obj, Map<String, String> headers)  {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().body(obj).headers(headers).put(resource);
        validateResponse(response,resource);
        return response;

    }

    public static Response makePostCall(String resource, Object obj) {
        log.info("Endpoint is: "+baseUri+resource);

        Response response = RestAssuredFactory.getSpecification().body(obj).post(resource);
        validateResponse(response,resource);
        return response;
    }

    public static Response makePostCallWithHeader(String resource, Object obj, Map<String, String> headers)  {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().body(obj).headers(headers).post(resource);

        System.out.println("Response body="+response.asString());

        validateResponse(response,resource);
        return response;
    }

    public static Response makeDeleteCallWithHeader(String resource, Map<String, String> headers)  {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().headers(headers).delete(resource);

        System.out.println("Response body="+response.asString());

        validateResponse(response,resource);
        return response;
    }

    public static Response PostCallWithHeader(String resource,String stringJSON){
        RequestSpecification requestSpecification = RestAssured.given().body(stringJSON);
        requestSpecification.contentType(ContentType.JSON);
//        requestSpecification.headers("Authorization", authToken);
        Response response = requestSpecification.post(resource);
        validateResponse(response,resource);
        return response;
    }

    public static Response makePostCallWithHeaderAndStringBody(String resource, String jsonString, Map<String, String> headers) {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().body(jsonString).headers(headers).post(resource);
        validateResponse(response,resource);
        return response;
    }

    public static Response makePostCallWithPathparam(String resource, Object obj, List<String> pathParams) {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().pathParam(pathParams.get(0), pathParams.get(1)).body(obj).post(resource);
        validateResponse(response,resource);
        return response;
    }

    public static Response makePostCallWithForm(String parameterName, String filePath, String resource) {
        log.info("Endpoint is: "+baseUri+resource);
        Response response = RestAssuredFactory.getSpecification().contentType("multipart/form-data").multiPart(parameterName,  new File(filePath)).post(resource);
        validateResponse(response,resource);
        return response;
    }



    private static void validateResponse(Response response, String resource){

        String uri = baseUri+resource;

        int responseCode = response.getStatusCode();

        switch (responseCode){

            case 500:

                throw new InternalServerError("Endpoint at "+uri+ " returned - Internal Server Error!");


            case 400:

                throw new InvalidRequestError("Endpoint at "+uri+ " returned - Invalid Request!");

            case 401:

                throw new UnauthorizedAccessError("Endpoint at "+uri+ " returned - Unauthorized!");

            case 404:

                throw new NotFoundError("Endpoint at "+uri+ " returned - Not Found!");

            default:

                Optional<String> responseString;
                ResponseBody responseBody = response.getBody();
                responseString = Optional.ofNullable(responseBody.asString());

                if(responseString.isPresent()){

                    if(responseString.get().isEmpty()){

                        throw new EmptyResponseError("Endpoint at "+uri+ " returned - Empty Response!");
                    }

                }else{

                    throw new NullResponseError("Endpoint at "+uri+ " returned - Null Response!");
                }

                break;

        }
    }
}
