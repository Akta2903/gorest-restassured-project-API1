package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }
    //1. Extract the title
    @Test
    public void test001() {
        String title= response.extract().path("title[0]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  extract the title :" + title);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the total number of record
    @Test
    public void test002() {
        Integer size= response.extract().path("size");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of records :" +size);
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String record= response.extract().path("body[14]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record :" +record);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer>record= response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of All user id  :" +record);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String>titleList= response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of All title  :" +titleList);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6. Extract the title of all records whose user_id = 2272670
    @Test
    public void test065() {
        List<HashMap<String,?>> titleUser_id= response.extract().path("findAll{it.id=='39296'}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Extract the title of all the records:" +titleUser_id);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 39295
    @Test
    public void test007() {
        List<HashMap<String,?>> value= response.extract().path("findAll{it.id==39295}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the Values of id = '39295'  :" +value);
        System.out.println("------------------End of Test---------------------------");
    }



}
