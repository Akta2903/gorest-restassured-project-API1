package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract  All the Ids

    @Test
    public void test001() {
        List<Integer> idList = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of id is  : " + idList);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> nameList = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of Name is  : " + nameList);
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("name[4]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name at the 5th object is  : " + name);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List< String> inactiveName = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Extract the names of all object whose status = inactive" + inactiveName);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<HashMap< String,?>> activeName = response.extract().path("findAll{it.status=='active'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Extract the names of all object whose status = active" + activeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<HashMap<String, ?>> femaleName = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Print the names of the object whose gender = female" + femaleName);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<HashMap<String ,?>> gmailInactive= response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get all the emails of the object where status = inactive" + gmailInactive);
        System.out.println("------------------End of Test---------------------------");
    }
    // 8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<HashMap<String ,?>> maleGenderId= response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Get the ids of the object where gender = male" + maleGenderId);
        System.out.println("------------------End of Test---------------------------");
    }
    // 9. Get all the status
    @Test
    public void test009() {
        List<String> allStatus= response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  Get all the status" + allStatus);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get email of the object where name = Ananta Chopra
    @Test
    public void test010() {
        List<String> email= response.extract().path("findAll{it.name=='Ananta Chopra'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  Get email of the object where name = Ananta Chopra : " + email);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get gender Id of =2272641
    @Test
    public void test011() {
        List<String> gender= response.extract().path("findAll{it.id=='2272641'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  Get gender Id of =2272641 :" + gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
