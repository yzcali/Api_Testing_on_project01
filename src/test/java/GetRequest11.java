
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class GetRequest11 extends TestBase{
    /*
    GSON: GSON ,  1) Json formatindaki data'lari Java Objectlerine donusturur.
                  2) Java Object'lerini Json formatindaki datalara donusturur.

        GSON also known as Google GSON, is an open-source Java library that
        can be used to convert Java Objects into JSON (JavaScript Object Notation)
         & vice versa.

         example: Map e donusturur neden cunku mapler de key value vardi ona
         cevirerek java icin kodlari kullanisli hale getirir. ,id =24 , gibi sonra
          tekrar Gson devreye girer ve javadan aldi kodu json formatina cevirir.

          De_Serialization = json formatindan gson ile java ya cevirmek
          Serialization = javadan gson ile json formatina cevirmek


  Note:  GSon ile ayni islevi yapan ObjectMapper class' da var.

 */

   @Test
    public void get(){
       Response response =given().
                          spec(spec03).
                          when().
                          get("/2");

       response.prettyPrint();

       HashMap<String, String> map = response.as(HashMap.class); // De-Serialization
       System.out.println(map);
       //{id=2.0, completed=false, title=quis ut nam facilis et officia qui, userId=1.0}

       System.out.println(map.keySet()); //[id, completed, title, userId] tum keyleri verir ama hashmap siraya dikkat etmez
       System.out.println(map.values()); // [2.0, false, quis ut nam facilis et officia qui, 1.0]

       // question : completed key should be false verify it

       SoftAssert softAssert = new SoftAssert();
       softAssert.assertEquals(map.get("completed"), false, "msg is not as expected");
       //softAssert.assertAll();

       // question all of the keys should be verify
        softAssert.assertEquals(map.get("userId"),1.0);
        softAssert.assertEquals(map.get("id"),2.0);
        softAssert.assertEquals(map.get("title"),"quis ut nam facilis et officia qui");
        softAssert.assertAll();
    // note dikkatimizi map e vererek yapiyoruz cunku 1.0 almaliyim double seklinde




   }

}
