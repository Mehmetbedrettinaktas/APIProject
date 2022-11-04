package get_requests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1) Postman, manuel API testleri icin kullandik.
    2) otomasyon testleri icin de Rest Assured Library kullanacagiz
    3) Otomasyon testleri yaparken asagidaki adimlari izliyoruz;
        a) gereksinimleri iyi anlamak
        b) Test caselerimizi yaziyoyoruz
            i) Test Case yazinminda "Gherkin" dilini kullanacagiz. Bizler yazilim diline hakim olsakda, karsimizdaki
            kisiler hakim olmayabilir ama Gherkin ile yazilan testleri anlamak ta zorluk cekmemeyeceklerdir.
            Ghrkin dilinde kullanacagimiz keywordler;

            - Given : On kusullar
            - When : Yapilacak aksiyonlar icin (get(), put(), post(), patch() ve delete() )
            - Then : Itsek yaptiktan (request gondertikten sonra) dogrulama
            - And : Coklu islemlerde kullanacagiz

         c) Test kodlarimizi yazmaya baslayacagiz.

            i) Set the URL,
            ii) set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)
            iii) Type code to send request (Talep gondermek icin kod yazimi)
            iv) Do Assertion (dogrulama yapmak)
     */


    @Test
    public void  get01(){
        /*
    Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
    */

        // i) Set the URL,
        String url="https://restful-booker.herokuapp.com/booking/101";

        //ii) set the expected Data (beklenen datanin olusturulmasi, Post, Put, Patch)
        // Bizden post, put, patch istemedigi icin bu case de kullanamayacagiz

        //iii) Type code to send request (Talep gondermek icin kod yazimi)

       Response response= given().when().get(url); // response cevap yanit demek
       response.prettyPrint();

        // iv) Do Assertion (dogrulama yapmak)
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
        // Status Code konsola yazdiralim
        System.out.println("Status Code : "+response.getStatusCode());
        // Cotent Type konsla yazdiralim
        System.out.println("Content Type : "+response.getContentType());
        // Status Line konsla yazdiralim
        System.out.println("Status Line : "+response.getStatusLine());

        // Header konsla yazdiralim
        System.out.println(" Header : "+response.getHeader("Server"));
        // Headers konsla yazdiralim
        System.out.println(" Headers : "+response.getHeaders());
        // Time konsla yazdiralim
        System.out.println(" Time : "+response.getTime());


    }
}
