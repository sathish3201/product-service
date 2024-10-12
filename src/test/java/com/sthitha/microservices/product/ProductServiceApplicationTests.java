package com.sthitha.microservices.product;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import java.math.BigDecimal;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		//base uri
		RestAssured.baseURI ="http://localhost";
		RestAssured.port = port;
	}
	static {
		mongoDBContainer.start();
	}
	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				    "name":"iphone 14",
				    "description":"this new second feature brand",
				    "price" : 1000.11
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id",  Matchers.notNullValue())
				.body("name", Matchers.equalTo("iphone 14"))
				.body("description",  Matchers.equalTo("this new second feature brand"))
				.body("price", Matchers.equalTo(1000.11));
	}

}
