import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest02 {
    /*
    Positive Scenario
    when() a Get Request  sending to the Endpoint
          https://restful-booker.herokuapp.com/booking
    And() Accept type is "application/json"
    then() status code 200
    and () content type "application/json"
     */
@Test
    public void get01(){
    given().
            accept("application/json").
            when().
            get("https://restful-booker.herokuapp.com/booking").
            then().
            assertThat().
            statusCode(200).
            contentType("application/json");

}
/*
Negative scenario
when() a Get Request  sending to the Endpoint
          https://restful-booker.herokuapp.com/booking/47
    And() Accept type is "application/json"
    then() status code 404
    and () content type "application/json"
 */


@Test
    public void get02(){

    Response response = given().accept("application/json").
            when().
            get("https://restful-booker.herokuapp.com/booking/47");
           response.prettyPrint(); //Not Found

           response.
                   then().
                   assertThat().
                   statusCode(404);

           // resônse la calismamiz duzen acisindan daha guzel
    // failed  olmustu cunku olmayan datanin type i da olmaz .
    // content type i yazmadik o yuzden .
    // test le isimiz bitince  response.prettyPrint(); kismi silinir bu bize gormeyi saglar

}
    /*
    Negative scenario
when() a Get Request  sending to the Endpoint
          https://restful-booker.herokuapp.com/booking/47
    then() status code 404
    and () response Body there is "Not Found"
    and() response Body there is not  "Sir : alex"
     */

   @Test
    public void get03(){
       Response response= given().
                          when().
                            get("https://restful-booker.herokuapp.com/booking/47");

       response.prettyPrint();
       // Not found u gozumle gormek cin yazdiriyorum

      assertEquals(404,response.getStatusCode());
      assertTrue(response.asString().contains("Not Found"));
      assertFalse(response.asString().contains("Sir : alex"));

     // burada response. then() kullanmadan gorduk .
       // "Sir : alex" olmadigini dogrulamak icin assertFalse kullandik



   }
}
