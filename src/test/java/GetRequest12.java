import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetRequest12 extends TestBase {

    /*
     GSON: GSON ,  1) Json formatindaki data'lari Java Objectlerine donusturur.
                  2) Java Object'lerini Json formatindaki datalara donusturur.

        GSON also known as Google GSON, is an open-source Java library that
        can be used to convert Java Objects into JSON (JavaScript Object Notation)
         & vice versa.
     */

    @Test
    public void get01(){
        Response response = given().
                spec(spec03).
                when().
                get();


        response.prettyPrint();
     // List lerin icine sadece nonprimitive ler konulabilir ve objectler de

        List<Map<Object, Object>> listOfMaps=response.as(ArrayList.class);

        System.out.println(listOfMaps.get(1)); // {userId=1.0, id=2.0, title=quis ut nam facilis et officia qui, completed=false}

      // it should has been  200 id , verify it

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listOfMaps.size()==200, "id number is not as expected");
      //  softAssert.assertAll();
    // id=121 should be completed true verify it.
         softAssert.assertEquals(listOfMaps.get(120).get("completed"),true, "result is not as expected ");
        // softAssert.assertAll();

    // sondan bir once elemanin title i nin "numquam repellendus a magnam" oldugunu verify ediniz
        softAssert.assertEquals(listOfMaps.get(listOfMaps.size()-2).get("title"),"numquam repellendus a magnam");
        softAssert.assertAll();






    }
}
