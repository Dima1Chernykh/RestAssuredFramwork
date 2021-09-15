import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.Matchers.is;

public class AllNewsPageGetTest {

    Routes routes = new Routes();

    @Test
    @Story("Get all news request")
    public void allNewsPageGetTest() {
        RestAssured.
                when().get(routes.getNews).
                then().assertThat().statusCode(200).
                and().body("success", is(true)).
                and().body("statusCode", is(1));
    }
}
