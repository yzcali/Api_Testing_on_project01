import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class TestBase {

    protected RequestSpecification spec01;

@Before
    public void setUP01(){
        spec01= new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
      // artik endpoint olarak "https://restful-booker.herokuapp.com" degil spec01 i kullanacagim.
    }


}
