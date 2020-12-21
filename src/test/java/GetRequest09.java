
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.*;


import static io.restassured.RestAssured.*;

public class GetRequest09 extends TestBase {
    @Test
    public void get01(){
        Response response= given().
                           spec(spec02).
                           when().
                           get();

        response.prettyPrint();
        //JsonPath objesi olusturalim

        JsonPath json = response.jsonPath();
        // tum employee isimlerini colsole a yazdiriniz

        //System.out.println(json.getString("data.employee_name"));

        System.out.println(json.getList("data.employee_name"));


        /*
        [Tiger Nixon, Garrett Winters, Ashton Cox, Cedric Kelly,
        Airi Satou, Brielle Williamson, Herrod Chandler, Rhona Davidson,
        Colleen Hurst, Sonya Frost, Jena Gaines, Quinn Flynn,
        Charde Marshall, Haley Kennedy, Tatyana Fitzpatrick,
        Michael Silva, Paul Byrd, Gloria Little, Bradley Greer,
        Dai Rios, Jenette Caldwell, Yuri Berry, Caesar Vance, Doris Wilder]
         */

        // second employee name should be  Garrett Winters , verify (soft assertion) it .
         // there are 3 steps for soft assertion
        /*
        1 create an object from soft assertion class
        2 using object make assertion
        3 softAssert.assertAll()
       */
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(json.getString("data[1].employee_name"), "Garrett Winters","name is not as expected");
        softAssert.assertAll();


        // hard assertion bu sekilde
        // assertEquals("name is not as expected","Garrett Winters",json.getString("data[1].employee_name"));

        // Herrod Chandler should be within the names of employees verify
        SoftAssert softAssert1 = new SoftAssert();
        softAssert1.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"));
        softAssert1.assertAll();

    }



}
