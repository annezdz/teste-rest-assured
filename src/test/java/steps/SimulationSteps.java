package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Simulation;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

public class SimulationSteps extends Utils {

    protected RequestSpecification res;
    protected ResponseSpecification resspec;
    protected Response response;
    TestDataBuild data;

    @Given("I create a new simulation with {string} {string} {string} with {string} verb")
    public void iCreateANewSimulationWith(int instalments, double value, boolean insurance, String http) throws IOException {
        response = (Response) RestAssured.given()
                .spec(requestSpecification(http))
                .body(data.addSimulation(value,instalments,insurance));
    }

    @And("returned the new simulation")
    public void returnedTheNewSimulation() {
        getBody(response);

    }

    @When("calls {string} with {string} verb")
    public void callsWithVerb(String resource, String http) throws IOException {
        APIResources resourceAPI = APIResources.valueOf(resource);

        System.out.println(resourceAPI.getResource());

        resspec = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();

        if(http.equalsIgnoreCase("POST")) {
            response = res.when()
                    .post(resourceAPI.getResource());
        }
    }
}
