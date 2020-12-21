
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;


public class GetRequest10 extends TestBase {

    private int size;

    /*
            when I send GET Request to url
            http://dumpy...
            Then
            Status Code is 200
            1) print all ids greater than 10
             verify that there are 14 ids greater than 10
            2) print all ages less than 30 on the console
            verify that max age is 23
            3) print all employee names whose salaries are greater than 350000
            assert that Charde Marshall is one of the employees whose salary is greater than 350 000
        */
    @Test
    public void get01(){
        Response response = given().
                spec(spec02).
                when().
                get();

        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath json= response.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        //1) print all ids greater than 10
      //  assert that there are 14 ids greater than 10
        List<String>idList = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
        System.out.println(idList); //[11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
         softAssert.assertEquals(idList.size(),14,"is not as expected");
        //softAssert.assertAll();

        //2) print all ages less than 30 on the console
        //verify that max age is 23

        List<String>ageList= json.getList("data.findAll{Integer.valueOf(it.employee_age)< 30}.employee_age");
        System.out.println(ageList); //[22, 23, 22, 19, 21, 23]
        Collections.sort(ageList);
        softAssert.assertTrue(ageList.get(ageList.size()-1).equals("23"),"age is not as expected");
       // softAssert.assertAll();


       // 3) print all employee names whose salaries are greater than 350000
        //assert that Charde Marshall is one of the employees whose salary is greater

        List<String>nameList= json.getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
        System.out.println(nameList);
      softAssert.assertTrue(nameList.contains("Charde Marshall"),"the name is not as expected");
       softAssert.assertAll();

    }


}
