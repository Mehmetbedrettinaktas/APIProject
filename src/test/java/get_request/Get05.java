package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get05 extends RestfulBaseUrl {
    /*
   Given
    https://restful-booker.herokuapp.com/booking
   When
    User sends get request to the URL
   Then
    Status code is 200
   And
  Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
 */
    @Test
    public void get01() {
        // https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz
        // i) Set the URL,
        spec.pathParam("first", "booking").queryParams("firstname", "Ali", "lastname", "Cengiz");

        //ii) set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)

        //iii) Type code to send request (Talep gondermek icin kod yazimi)
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        /*
        1.spec kismi-->https://restful-booker.herokuapp.com
        2. spec kismina,pathparam ile booking'i ekledi,
        query ile sorgulama parametrelerini ekledik
         */
        // iv) Do Assertion (dogrulama yapmak)
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));

    }
}
