package de.cpoepke.mule.demo;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SystemIT {

  @Test
  public void testMyAction() {

    RestAssured.baseURI = System.getProperty("system.url");
    RestAssured.defaultParser = Parser.JSON;

    // Verify an system end-to-end call
    given()
            .param("mimeType", "application/json")
            .get("/api/myResource/myAction")
            .then().assertThat()
            .header("content-type", containsString("application/json"))
            .body("message", equalTo("You got mocked data"));
  }
}