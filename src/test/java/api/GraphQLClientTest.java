package api;

import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.GraphQLBody;
import utils.GraphQLClient;

import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;

class GraphQLClientTest {
    public static final String STAGE_ABB_GRAPHQL =
            "https://stage.apim.eu.mybuildings.abb.com/adtg-api/v1/graphql";
    private static GraphQLClient graphQLClient;

    @BeforeAll
    public static void setUpGraphQL() {
        graphQLClient = new GraphQLClient(STAGE_ABB_GRAPHQL);
    }

    @SneakyThrows
    @Test
    void graphQLTest() {
        GraphQLBody gql = new GraphQLBody(Paths.get(
                System.getProperty("user.dir"),
                "graphql", "system.gql"));

        //System.out.println(new ObjectMapper().writeValueAsString(gql));

        Response res = graphQLClient.post(gql)
                .then()
                .assertThat().body(
                        "data.SystemFH[0].Assets[0].IsLocated.SublocationOf.label",
                        is("Keller"))
                .extract()
                .response();

        System.out.println(res.body().prettyPrint());


    }
}
