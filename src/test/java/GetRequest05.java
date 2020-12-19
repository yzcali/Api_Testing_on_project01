import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;


public class GetRequest05 {
    @Test
            public void get02(){
        Response response = given().
                when().
                get("https://restful-booker.herokuapp.com/booking/5");

        response.prettyPrint();
        // note paylasirken bu kismi gondermiyoruz

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Mary")).
                body("totalprice",Matchers.equalTo(333)).
                body("bookingdates.checkin",Matchers.equalTo("2016-03-02") );


    }

    // tekli datalarda equals.to coklu datalarda ise hasItem la calismalisiniz.




}
