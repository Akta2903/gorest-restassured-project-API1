package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest  {


    static String name = "John" + TestUtils.getRandomValue();
    static String email =  TestUtils.getRandomValue() + "John@gmail.com";
    static String gender = "Female" ;

    static  String status ="active";




    @Test
    public void createUser() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        Response response=given()
                .header("Content-Type","application.json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post("https://gorest.co.in/public/v2/users");
        response.then().log().all().statusCode(201);

    }
    @Test
    public void getUser() {
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .get("https://gorest.co.in/public/v2/users/2284664");
        response.then().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void verifyUserUpdateSuccessfully(){

        UserPojo userPojo = new UserPojo();
        userPojo.setName("Peter");
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);
//        int id = userPojo.getId();
//        userPojo.setId(id);

        Response response=given()
                .header("Content-Type","application.json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put("https://gorest.co.in/public/v2/users/2284664");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void verifyUserDeleteSuccessfully() {
        String token ="61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("https://gorest.co.in/public/v2/users/2284664");
        response.then().log().all().statusCode(204);


    }
}