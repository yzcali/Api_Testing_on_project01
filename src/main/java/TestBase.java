import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class TestBase {

    protected RequestSpecification spec01;
    protected RequestSpecification spec02;
    protected RequestSpecification spec03;

@Before
    public void setUP01() {
    spec01 = new RequestSpecBuilder().
            setBaseUri("https://restful-booker.herokuapp.com").
            build();
    // artik endpoint olarak "https://restful-booker.herokuapp.com" degil spec01 i kullanacagim.

}
@Before
    public void setUP02(){
        spec02= new RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1/employees").
                build();


    }
    @Before
    public void setUP03(){
        spec03= new RequestSpecBuilder().
                setBaseUri("https://jsonplaceholder.typicode.com/todos").
                build();


    }

}
