package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

    static int id;
    static String name = "John" + TestUtils.getRandomValue();
    static String email =  TestUtils.getRandomValue() + "John@gmail.com";
    static String gender = "Female" ;

    static  String status ="active";



    //create user
    @Test
    public void test001() {
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
        id = response.jsonPath().get("id");
        System.out.println("Id : " + id);

    }
    //get user by id
    @Test
    public void test002() {
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .get("/" +id);
        response.then().statusCode(200);
        response.prettyPrint();
    }


    //update user
    @Test
    public void test003(){

        UserPojo userPojo = new UserPojo();
        userPojo.setName("Peter" + name + "pascal");
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);


        Response response=given()
                .header("Content-Type","application.json")
                .header("Authorization","Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put("/" +id);
        response.then().log().all().statusCode(200);
    }
   //delete user
    @Test
    public void test004() {
        String token ="61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .when()
                .delete("/" +id);
        response.then().log().all().statusCode(204);


    }
}