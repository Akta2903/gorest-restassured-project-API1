package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest  {



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


    static ValidatableResponse response;
    //1. Verify the if the total record is 20
    @Test
    public void verifyTheTotalRecord()
    {

        response.body("size", equalTo(20));

    }

    //2. Verify the if the name of id = 2178503 is equal to ”Msgr. Harinarayan Kaur”
    @Test
    public void verifyTheNameOfGivenId()
    {

        response.body("findAll{it.id == 2178503}.name",hasItem("Msgr. Harinarayan Kaur"));

    }

    //3.Check the single ‘Name’ in the Array list (Subhashini Talwar)

    @Test
    public void verifySpecificNameInList()
    {
        response.body("name",hasItem("Msgr. Baladitya Dhawan"));
    }

    //4. Check the multiple ‘Names’ in the
    @Test
    public void verifyMultipleNamesInTheList()
    {
        response.body("name",hasItems("Aslesha Malik","Bala Patel","Rameshwar Guha Sr."));
    }

    //Verify the emai of userid = 2191531 is equal “sukanya_dubashi@schiller.test”
    @Test
    public void verifyTheEmailOfGivenId()
    {
        response.body("findAll{it.user_id==2191531}.email",hasItem("sukanya_dubashi@schiller.test"));

    }

    //6. Verify the status is “Active” of name is “Rameshwar Guha Sr.”
    @Test
    public void verifyTheStatusOfGivenUserName()
    {
        response.body("findAll{it.name== 'Rameshwar Guha Sr.'}.status",hasItem("active"));
    }

    //7. Verify the Gender = female of  name is “Mrs. Mandaakin Kaul”
    @Test
    public void verifyTheGenderOfGivenName()
    {
        response.body("findAll{it.name=='Mrs. Mandaakin Kaul'}.gender",hasItem("female"));
    }

}
