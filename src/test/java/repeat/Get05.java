package repeat;

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
    public void  get01(){
        // 1. Set The Url
       //  https://restful-booker.herokuapp.com/booking/9?firstname=Eric&lastname=Jackson
        spec.pathParam("first","booking").queryParams("firstname","Eric","lastname","Jackson");
        // spec --> base_urldir.
        //2. Set The Expected Data

        // 3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion (dogrulama yapmak)
        assertEquals(200, response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));


    }

}
