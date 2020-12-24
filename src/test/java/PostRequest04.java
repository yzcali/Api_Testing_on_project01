
public class PostRequest04 extends TestBase {

    /* Post Scenario
                    given accept type is Json
                    when I send a Post  request to the url
                    https://restful-booker.herokuapp.com/booking
                    with the request body
                    {
                      "firstname": "Alex",
                      "lastname" : "writer",
                      "totalprice": 147,
                      "depositpaid": true,
                      "bookingdates": {
                           "checkin": "2020-05-04",
                           "checkout": "2021-05-04"
                    },
                    "additionalneeds": "Wifi"
                    } */
    /*
    POJO : Plain Old Java Object
     bir class olusturup variable value olusturuyoruz.
    assertionlari bunun uzerinden yapacagim .
    http://www.jsonschema2pojo.org/ sayfasina giderek json i java ya cevirdik ve mavendan kutuphanleri indirdik

     */
}
