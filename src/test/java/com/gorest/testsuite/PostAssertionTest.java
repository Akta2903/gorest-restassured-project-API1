package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostAssertionTest  {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIT()
    {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);

    }

   // 1. Verify the if the total record is 25
    @Test
    public void test001()
    {
        response.body("size", equalTo(25));
    }

    //2. Verify the if the title of id =39305 is equal to ”Cruentus absorbeo id auctus suus cunabula sint umerus.”
    @Test
    public void  test002 ()
    {
        response.body("findAll{it.id==39305}.title", hasItem("Cruentus absorbeo id auctus suus cunabula sint umerus."));
    }

    //3. Check the single user_id in the Array list (5522)

    @Test
    public void test003()
    {
        response.body("user_id",hasItem(2272646));
    }
    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004()
    {
        response.body("id",hasItems(39305,39296,39294));
    }
    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”

    @Test
    public void test005()
    {
        response.body("findAll{it.id==39271}.body",hasItem("Deficio artificiose tabesco. Alias nemo ubi. Nesciunt claro ipsum. Sub calcar templum. Aliqua vos accusantium. Vestigium aeneus curatio. Antea labore adipisci. Desparatus tersus communis. Caelum solvo natus. Culpa vado angulus."));
    }

}
