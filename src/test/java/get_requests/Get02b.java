package get_requests;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02b extends ReqresBaseUrl {

          /*
      Given
          https://reqres.in/api/users/23
      When
          User send a GET Request to the url
      Then
          HTTP Status code should be 404
      And
          Status Line should be HTTP/1.1 404 Not Found
      And
          Server is "cloudflare"
      And
          Response body should be empty

       */
    @Test
    public void  get02(){
        // 1. Set The Url
        spec.pathParams("first","users","second",23);

        //2. Set The Expected Data

        // 3. Send The Request And Get The Response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion (dogrulama yapmak)
        assertEquals(404, response.getStatusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(2, response.asString().replaceAll("\\s","").length());
        // sayi ve rakamlar disindaki her seyi silersem size sifir verir.

    }

}
