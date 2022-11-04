package get_requests;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get08 extends JsonplaceholderBaseUrl {

    /*
    De-Serialization : Json datayi Java objesine cevirme
    Serialization : Java objesini Json formatina cevirme.
    De-Serialization : iki turlu yapacagiz
    1. Gson ile yapacagiz --> google tarafindan uretilmistir.
    2. Object Mapper : Daha populer...
     */

         /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
         */

    @Test
    public void  get08(){
        // 1. Set The Url
        spec.pathParams("first","todos","second",2);

        // 2. Set The Expected Data --> Payload
        Map<String,Object>  expectedData= new HashMap<>();
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);

        System.out.println("expectedData = " + expectedData);

        // 3. Send The Request And Get The Response
       Response response= given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       // 4. Do Assertion (dogrulama yapmak)
      Map<String,Object> actualData=  response.as(HashMap.class); // De-Serialization   bir birine donusturme
        // response al ve HashMap'e ceviriyoruz.
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("1.1 vegur"),actualData.get("Via"));
        assertEquals(expectedData.get("cloudflare"),actualData.get("Server"));
        assertEquals(200, response.statusCode());

    }

}
