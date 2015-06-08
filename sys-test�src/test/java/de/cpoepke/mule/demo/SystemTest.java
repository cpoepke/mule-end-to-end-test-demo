package de.cpoepke.mule.demo;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SystemTest {

  @Test
  public void testMyAction() {
//    String systemUrl = "http://192.168.59.103:8080";
    String systemUrl = System.getProperty("system.url");
    System.out.println("Checking URL: " + systemUrl);

    RestAssured.baseURI = systemUrl;
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