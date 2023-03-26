package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;

import static baseUrl.OmbdBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class ApiSteps {

    Response response1;
    Response response2;
    String imdbId;
    @Given("user searchs {string} by search parameter")
    public void user_searchs_by_search_parameter(String text) {
      spec.queryParams("s",text,"apikey", "40debc6c");
      response1 =given().spec(spec).when().get();

    }
    @When("user gets {string} film id from the results")
    public void user_gets_film_id_from_the_results(String filmname) {
        JsonPath jsonPath= response1.jsonPath();
        List<String> films=jsonPath.getList("Search.Title");
        int index=films.indexOf(filmname);
        imdbId=jsonPath.getString("Search["+index+"].imdbID");

    }
    @When("user searchs film with this id by id parameter")
    public void user_searchs_film_with_this_id_by_id_parameter() {

        String endpoint="https://www.omdbapi.com/?i="+imdbId+"&apikey=40debc6c";
        response2 =given().when().get(endpoint);
    }
    @And("user validates {string}> {string}> {string}> and status code {int} the response data")
    public void userValidatesAndStatusCodeTheResponseData(String title, String year, String released, int statuscode) {
        assertEquals(statuscode, response2.statusCode());

        JsonPath jsonPath= response2.jsonPath();
        assertEquals(title, jsonPath.getString("Title"));
        assertEquals(year, jsonPath.getString("Year"));
        assertEquals(released, jsonPath.getString("Released"));


    }
}
