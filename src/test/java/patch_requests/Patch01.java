package patch_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Patch01 extends JsonplaceholderBaseUrl {
    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "title": "Wash the dishes"
           }
    When
I send PATCH Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 10,
                   "title": "Wash the dishes",
                   "completed": true,
                   "id": 198
                   }
 */

    @Test
    public void patch01(){
        // 1. Set The Url
        spec.pathParams("first","todos","second",198);

        // 2. Set The Expected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
       Map<String,Object>expectedData= obj.expectedDataMethod(null,"Wash the dishes",null);
        System.out.println("expectedData = " + expectedData);

        // 3. Send The Request And Get The Response
     Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).patch("/{first}/{second}");
     response.prettyPrint();

     // 4. Do Assertion (dogrulama yapmak)
        Map<String,Object> actualData=  response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("title"),actualData.get("title"));
    }
}
