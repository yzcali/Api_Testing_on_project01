import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class TestBase {

    protected RequestSpecification spec01;
    protected RequestSpecification spec02;
    protected RequestSpecification spec03;
    Map<String, Object> requestBodyMap = new HashMap<>();
    Map<String, String> bookingDatesMap = new HashMap<>();
    JSONObject jsonBookingDatesBody = new JSONObject();
    JSONObject jsonReqBody = new JSONObject();
    @Before
    public void setUP01() {
        spec01 = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
        // artik endpoint olarak "https://restful-booker.herokuapp.com" degil spec01 i kullanacagim.

    }

    @Before
    public void setUP02() {
        spec02 = new RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
                build();


    }

    @Before
    public void setUP03() {
        spec03 = new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();


    }

    protected Response createRequestBody() {

        // en ic taraftaki json icin obje olusturdum

        jsonBookingDatesBody.put("checkin", "2020-05-04");
        jsonBookingDatesBody.put("checkout", "2021-05-04");

        // buyuk kisim icin  JSON obje

        jsonReqBody.put("firstname", "Alexson");
        jsonReqBody.put("lastname", "Printerson");
        jsonReqBody.put("totalprice", 155);
        jsonReqBody.put("depositpaid", true);
        jsonReqBody.put("bookingdates", jsonBookingDatesBody);
        // bookingdates in value sunu yazmamiz lazim ic tarafta json oldugu icin .
        jsonReqBody.put("additionalneeds", "Wifi");

        Response response = given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(jsonReqBody.toString()).
                when().
                post("/booking");
        return response;
    }

    protected Response createReqBody() {

        bookingDatesMap.put("checkin", "2020-05-04");
        bookingDatesMap.put("checkout", "2021-05-04");


        requestBodyMap.put("firstname", "Alexand");
        requestBodyMap.put("lastname", "Waterson");
        requestBodyMap.put("totalprice", 147);
        requestBodyMap.put("depositpaid", true);
        requestBodyMap.put("bookingdates", bookingDatesMap);
        requestBodyMap.put("additionalneeds", "Wifi");

        Response response = given().
                contentType(ContentType.JSON).// or "application/json"
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(requestBodyMap).
                when().
                post("/booking");
        return response;

    }
}








