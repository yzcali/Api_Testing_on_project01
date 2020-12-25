import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;


public class PutRequest02 extends TestBase{
    /*
    1_ spec03 kullanarak herhangi bir datayi update ediniz
    2_ update edildigini status code ile verify ediniz
     */

    @Test
    public void put02(){
        Response response= given().
                spec(spec03).
                when().
                get("/25");

        response.prettyPrint();

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("title","happy_holiday!");
        jsonObject.put("completed",false);

        Response responseAfterPut=given().
                contentType(ContentType.JSON).
                spec(spec03).
                body(jsonObject.toString()).
                when().
                put("/25");

        responseAfterPut.prettyPrint();

        responseAfterPut.then().assertThat().statusCode(200);

         JsonPath json= responseAfterPut.jsonPath();
         SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(json.getString("title"),jsonObject.get("title"));
        softAssert.assertEquals(json.getBoolean("completed"),jsonObject.get("completed"));
        softAssert.assertAll();






    }
}
