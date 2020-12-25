import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;


import static io.restassured.RestAssured.*;


public class PutRequest01 extends TestBase {
    @Test
    public void put01(){
        Response response= given().
                            spec(spec03).
                            when().
                            get("/20");

        response.prettyPrint();

        // change the data of title

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title","merry_christmas");
        jsonObject.put("id",20); // id yi update edemedigimiz icin degistirmedik ok.
        jsonObject.put("userId",222);
        jsonObject.put("completed",false);

        Response responseAfterPut = given().
                                    contentType(ContentType.JSON).
                                    spec(spec03).
                                    body(jsonObject.toString()).
                                    when().
                                    put("/20");

        responseAfterPut.prettyPrint();



    }
}
