import io.restassured.path.json.JsonPath;
import org.junit.Test;
import io.restassured.response.Response;

import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;


public class GetRequest13 extends TestBase{
    @Test
    public void get01(){
        Response response = given().
                spec(spec02).
                when().
                get();
        response.prettyPrint();

  // first 5 names Tiger Nixon, G... verify them.
        //1.way bad

        // once objeleri olusturalim
        JsonPath json= response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(json.getString("data[0].employee_name"),"Tiger Nixon");
        softAssert.assertEquals(json.getString("data[1].employee_name"),"Garrett Winters");
        softAssert.assertEquals(json.getString("data[2].employee_name"),"Ashton Cox");
        softAssert.assertEquals(json.getString("data[3].employee_name"),"Cedric Kelly");
        softAssert.assertEquals(json.getString("data[4].employee_name"),"Airi Satou");
        //softAssert.assertAll();

       //  2.way good

        List<String> nameList= new ArrayList<>();
        nameList.add("Tiger Nixon");
        nameList.add("Garrett Winters");
        nameList.add("Ashton Cox");
        nameList.add("Cedric Kelly");
        nameList.add("Airi Satou");

        for(int i =0 ; i<nameList.size(); i++){
            softAssert.assertEquals(json.getString("data[" + i +"].employee_name"),nameList.get(i));
           }
        //softAssert.assertAll();

        //3. way great

        List<Map> actualList= json.getList("data");
        System.out.println(actualList);

        Map<Integer, String> expectedMap= new HashMap<>();
        expectedMap.put(0,"Tiger Nixon");
        expectedMap.put(1,"Garrett Winters");
        expectedMap.put(2,"Ashton Cox");
        expectedMap.put(3,"Cedric Kelly");
        expectedMap.put(4,"Airi Satou");

        for(int i=0;i<expectedMap.size();i++){
            softAssert.assertEquals(actualList.get(i).get("employee_name"),expectedMap.get(i));
        }
        softAssert.assertAll();

    }
}
