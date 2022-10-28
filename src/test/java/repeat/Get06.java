package repeat;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Get06 extends RestfulBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/111
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
    public void  get01(){
        // 1. Set The Url
        spec.pathParams("first","booking","second",111);

        // 2. Set The Expected Data

        // 3. Send The Request And Get The Response ( Taleplerimiz gonder ve cevap al)
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion (dogrulama yapmak)
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Carlos"),
                "lastname", equalTo("Vera"),
                "totalprice",equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("Breakfast"));

    }
}
