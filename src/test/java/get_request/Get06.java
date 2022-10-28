package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get06 extends RestfulBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/2325
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
         "firstname": "Bradley",
         "lastname": "Pearson",
         "totalprice": 132,
         "depositpaid": false,
         "bookingdates": {
             "checkin": "2022-10-27",
             "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
    }
     */

    @Test
    public void get06() {

        // 1. Set The URL ( URL Olustur)

        spec.pathParams("first", "booking", "second", 2325);

        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response ( Talep gonder ve cevap al)
        Response response = given().spec(spec).when().get("/{first}/{second}");
        // response.prettyPrint();

        // 4. Do Assertion ( Dogrulama Yap)

        /*
         Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
         {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": { // outer json
        "checkin": "2022-10-27", // inner json
        "checkout": "2022-11-07" // inner json
    },
    "additionalneeds": "None"
}
         */
        // 1. Yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Bradley"),
                        "lastname", equalTo("Pearson"), // burada softAssert yaptik
                        "totalprice", equalTo(132),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2022-10-27"),
                        "bookingdates.checkout", equalTo("2022-11-07"),
                        "additionalneeds", equalTo("None"));

        // 2. Yol : Jsonpath class'nın kullanimi
        JsonPath json = response.jsonPath();
        assertEquals("Bradley", json.getString("firstname"));
        assertEquals("Pearson", json.getString("lastname"));
        assertEquals(132, json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27", json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07", json.getString("bookingdates.checkout"));
        assertEquals("None", json.getString("additionalneeds"));


        // 3. Yol : soft Assertion
        //softAssert classini 3 adimda  kullanilabilir

        // 1. Obje olusturma
        SoftAssert softAssert = new SoftAssert();
        // 2. Do assertion( Dogrulama yapma)
        softAssert.assertEquals(json.getString("firstname"), "Bradleyx", "firstname hatali");
        softAssert.assertEquals(json.getString("lastname"), "Pearson", "lastname hatalı");
        softAssert.assertEquals(json.getInt("totalprice"), 132, "Total price Hatali");
        softAssert.assertEquals(json.getBoolean("depositpaid"), false, "dapositpaid hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2022-10-27", "booking tarihi hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2022-11-07", "booking tarihi hatali");
        softAssert.assertEquals(json.getString("additionalneeds"), "None", "additionalneeds hatali");


        // iii) Dogrulama islemleri sonunda softAssert.assertAll() diyerek yaptigimiz tum dogrulama islemlerinin kontrol edilmesini
        // sagliyoruz.
        // Eger sistemin sonunda softAssert.assertAlll() kullanmaz isek taleplerimiz hatali olsa bile testimiz pass olacaktır.
        softAssert.assertAll();


    }


}