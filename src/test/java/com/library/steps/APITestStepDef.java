package com.library.steps;

import com.library.pages.LoginPage;
import com.library.utility.ConfigurationReader;
import com.library.utility.LibraryAPI_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;


import java.util.List;

import static io.restassured.RestAssured.*;

public class APITestStepDef {


    Response response;
    RequestSpecification givenPart;
    ValidatableResponse thenPart;
    String id;
    /*
    Given I logged Library api as a "librarian"
    And Accept header is "application/json"
    When I send GET request to "/get_all_users" endpoint
    Then status code should be 200
    And Response Content type is "application/json; charset=utf-8"
    And Each "id" field should not be null
    And Each "name" field should not be null
     */

    /*
      Response response1 = given().accept(ContentType.URLENC)
                .and().formParam("email", "librarian10@library")
                .and().formParam("password", "libraryUser")
                .when().post("https://library2.cydeo.com/rest/v1/login")
                .then().assertThat()
                .extract().response();


        token = (response.jsonPath()).getString("token");

        JsonPath jsonPath1 = given().accept(ContentType.URLENC)
                .header("x-library-token",token)
                .when().get("https://library2.cydeo.com/rest/v1/get_all_users")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON).extract().jsonPath();
     */
    @Given("I logged Library api as a {string}")
    public void i_logged_library_api_as_a(String userType) {
        givenPart = given().header("x-library-token", LibraryAPI_Util.getToken(userType));


    }

    @Given("Accept header is {string}")
    public void accept_header_is(String contentType) {

        givenPart.accept(contentType);


    }

    @When("I send GET request to {string} endpoint")
    public void iSendGETRequestToEndpoint(String endPoint) {
        response = givenPart
                .when().get(ConfigurationReader.getProperty("library.baseUri") + endPoint);
        thenPart = response.then();
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int statusCode) {
        thenPart.statusCode(statusCode);
    }

    @Then("Response Content type is {string}")
    public void response_content_type_is(String contentType) {
        thenPart.contentType(contentType);
    }

    @And("Each {string} field should not be null")
    public void eachFieldShouldNotBeNull(String path) {
        thenPart.body(path, Matchers.everyItem(Matchers.notNullValue()));
    }

    @And("Path param is {string}")
    public void pathParamIs(String value) {
        givenPart.pathParam("id", value);
        id = value;


    }

    @And("{string} field should be same with path param")
    public void fieldShouldBeSameWithPathParam(String path) {
        thenPart.body(path, Matchers.is(id));
    }

    @And("following fields should not be null")
    public void followingFieldsShouldNotBeNull(List<String> expectedData) {
        for (String eachData : expectedData) {
            thenPart.body(eachData, Matchers.is(Matchers.notNullValue()));
        }
    }

    @And("Request Content Type header is {string}")
    public void requestContentTypeHeaderIs(String contentType) {
        givenPart.contentType(contentType);
    }

    @And("I create a random {string} as request body")
    public void iCreateARandomAsRequestBody(String arg0) {
    }


//    @Given("Request Content Type header is {string}")
//    public void request_content_type_header_is(String contentType) {
//
//    }
//    @Given("I create a random {string} as request body")
//    public void i_create_a_random_as_request_body(String string) {
//
//    }
//    @When("I send POST request to {string} endpoint")
//    public void i_send_post_request_to_endpoint(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//
//    @Then("the field value for {string} path should be equal to {string}")
//    public void the_field_value_for_path_should_be_equal_to(String string, String string2) {
//
//    }
//    @Then("{string} field should not be null")
//    public void field_should_not_be_null(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @And("created user information should match with Database")
//    public void createdUserInformationShouldMatchWithDatabase() {
//    }
//
//    @And("created user should be able to login Library UI")
//    public void createdUserShouldBeAbleToLoginLibraryUI() {
//
//    }
//
//    @And("created user name should appear in Dashboard Page")
//    public void createdUserNameShouldAppearInDashboardPage() {
//    }


}
