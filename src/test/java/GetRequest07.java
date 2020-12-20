import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;


public class GetRequest07 extends TestBase{
    //among the data there are smo whose first name is "Eric"
    //Url spec01 from TestBase

//1. way

    @Test
    public void get01(){

        Response response = given().
                            spec(spec01).
                            when().
                            get("/booking?firstname=Eric");

        response.prettyPrint();

   // postmande body de ararken yazinca ekleme , endpoint e , yapiyordu ordan hatirla
// "bookingid": 6, 10,3

       assertTrue(response.getBody().asString().contains("bookingid"));

    }
    //2. way

    @Test
    public void get02(){
       spec01.queryParams("firstname", "Eric","depositpaid",true);
      // spec01.queryParam("depositpaid",true); bunu alta tekrar yazmana gerek yok params la coklu kullanmak lazim
        Response response = given().
                spec(spec01).
                get("/booking");

        response.prettyPrint();


        assertTrue(response.getBody().asString().contains("bookingid"));

    }
}
