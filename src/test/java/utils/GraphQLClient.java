package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.nullValue;

public class GraphQLClient {
    private final String url;
    private final RequestSpecification requestSpecification;

    public GraphQLClient(String url) {
        this.url = url;
        requestSpecification = given()
                .baseUri(url)
                .header("Authorization", "Bearer " + getAuthToken())
                .header("Content-Type", JSON);
    }

    public Response post(GraphQLBody graphQLBody) {
        return requestSpecification
                .body(graphQLBody)
                .post()
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("errors", nullValue())
                .extract().response();
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    private String getAuthToken() {
        return given()
                .auth()
                .preemptive()
                .basic(
                        System.getProperty("user"),
                        System.getProperty("password"))
                .when()
                .post("https://stage.api.eu.mybuildings.abb.com/user/access-token")
                .then()
                .assertThat().statusCode(201)
                .extract()
                .response().body().asString();
    }

}
