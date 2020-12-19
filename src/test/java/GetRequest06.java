import io.restassured.http.ContentType;
import io.restassured.response.Response;
import  static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest06 extends TestBase {
    // TestBase class olusturup her teste kullanilan datalari
    // TestBase class a koyup extends ederek tekrardan kurtulacagiz
     // base url ==> .com da biten kismidir , bunu koyariz tekrar tekrar yazmayiz
    /*
    When I send a GET request to REST API URL
    https://restful-booker.herokuapp.com/booking/5
    Then
    HTTP Status Code should be 200
    And Response format should be "application/Json"
     {
     "firstname": "Eric",
    "lastname": "Jackson",
    "totalprice": 412,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2018-01-13",
        "checkout": "2019-12-09"
    }
     */

    // given() dan sonra iki sey vardir  spec ve acceptance type

    @Test
    public void get01(){
        Response response = given().
                spec(spec01).
                when().
                get("/booking/5");

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Eric"),
                        "lastname",equalTo("Jackson"),
                        "totalprice",equalTo(412),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2018-01-13"),
                        "bookingdates.checkout",equalTo("2019-12-09"));


    }

 //testin scope u nedir verilen yeri yapmaniz gerekli diyor .
    // yani scope a bakarak is yapacagiz bize verilen neyse o dur.
      //import  static org.hamcrest.Matchers.*; yazarsaniz Matchers yazip  tekrar etmenize gerek yok

}
