import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest {
    @Test
    public void getMethod01(){
        given().
                when().
                get("https://restful-booker.herokuapp.com").
                then().
                assertThat().
                statusCode(200);
    }
    // Get ile aldigim datayi console yazdirma
    // consolda respond body i gosteriyor
    @Test
    public void getMethod02(){
        Response response=given().
                when().
                get("https://restful-booker.herokuapp.com/booking/7");

        // consolda Response body i gosteriyor.
        response.prettyPrint();

        // statuscode u console da gormek icin
        System.out.println("Status code :" + response.getStatusCode());  //] 200
        // status line i concole a yazdir mak icin
        System.out.println("Status line :"+response.getStatusLine());

        // Response body deki datanin content type i
        //1.way
        System.out.println("Content type :"+response.getContentType());
       // 2. way
        System.out.println("Content type :"+response.getHeader("Content-Type"));


        System.out.println();
      /*
      Status code :200
      Status line :HTTP/1.1 200 OK
      Content type :application/json; charset=utf-8 (utf-8 , means english words )
       */
        // Headers ta ki tum  bilgileri almak icin
        System.out.println( response.getHeaders());

        /*
        Server=Cowboy
       Connection=keep-alive
       X-Powered-By=Express
       Content-Type=application/json; charset=utf-8
       Content-Length=144
      Etag=W/"90-UW/DPSal8kzWiHN83EezLtBmmLc"
      Date=Wed, 16 Dec 2020 23:42:08 GMT
      Via=1.1 vegur
         */
        System.out.println();


        // Headers ta istenen specifik bir bilgiyi almak
        System.out.println(  response.getHeader("Date"));  //Wed, 16 Dec 2020 23:45:39 GMT

        //we have to must assertion
        //1) Status code 200
        // using assertThat it means first  defect , code execution stops and giving report and it does not continue to work
        // usually we use the assertThat ,  because better than soft for us.
        response.
                then().
                assertThat().
                statusLine("HTTP/1.1 200 OK").
                contentType("application/json; charset=utf-8").
                statusCode(200);
    }
    //
}
