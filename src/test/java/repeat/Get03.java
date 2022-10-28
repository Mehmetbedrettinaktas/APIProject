package repeat;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Get03 extends JsonplaceholderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
//  buraya kadar request(istek, talep) kismi budan sonraki kisim response(cevap,yanit) kismidir yani assertion yapacagiz.
        Then
          HTTP Status Code should be 200
        And
            Response format should be "application/json"  // response format dediginde conten type anlamaliyiz
        And
            "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
        And
            "completed" is false
        And
            "userId" is 2
   */
    @Test
    public void  get01(){
        // 1. Set The Url
        spec.pathParams("first","todos","second",23);

        // 2. Expected Data

        //3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion (dogrulama yapmak)
        // 1 .Yol (hard Assert)
        response.then().assertThat().statusCode(200).contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).body("userId",equalTo(2));
                //Matchers kiyaslama yapmak icin kullandigimiz bir classdir.
                // equalTo --> bununla eslestir demek
        // 2.Yol (softAssert)
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed",equalTo(false),"userId",equalTo(2));





    }
}
