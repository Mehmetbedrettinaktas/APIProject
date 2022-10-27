package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonplaceholderBaseUrl {
        /*
         Given
             https://jsonplaceholder.typicode.com/todos
         When
          I send a GET request to the Url
        And
            Accept type is "application/json"
        Then
            HTTP Status Code should be 200
        And
            Response format should be "application/json"
        And
            There should be 200 todos
        And
            "quis eius est sint explicabo" should be one of the todos title
        And
            2, 7, and 9 should be among the userIds

        */
    @Test
    public void  get04(){

        // i) Set the URL,
        spec.pathParam("first","todos");
        //ii) set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)

        //iii) Type code to send request (Talep gondermek icin kod yazimi)
        Response response= given().spec(spec).when().accept(ContentType.JSON).
                get("/{first}");
        // response.prettyPrint();

        // iv) Do Assertion (dogrulama yapmak)
        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200),"title",hasItem("quis eius est sint explicabo"),
                "userId",hasItems(2,7,9));
        // butun dogrulamalari tek bir body icinde yaparsak soft assert olur.
        // butun adimlar icin farkli bodyler icinde sorgulama yapiyorsak bu hart assorter.
        /*
        Soft assert testi gerçekleştirir ve assert başarısız olursa hata fırlatma gerçekleştirmez.
        Hard assert anında hata fırlatır, sonrasında test işlemine devam eder
        */
    }
}
