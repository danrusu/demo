package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class RestTest {

    @Test
    void calculateTest() {
        Response res = given()
                .baseUri("http://qatools.ro/api/calculate.php")
                .param("firstNumber", 10)
                .param("secondNumber", 10)
                .param("operation", 1)
                .when()
                .get()
                .then()

                .assertThat().statusCode(200)
                .assertThat().body("numbers", hasSize(2))
                .assertThat().body("numbers[0]", equalTo("10"))
                .assertThat().body("operation", is("1"))
                .assertThat().body("result", is("20"))

                .extract()
                .response();

        System.out.println(res.body().prettyPrint());
    }
}
