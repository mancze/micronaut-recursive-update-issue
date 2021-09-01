package com.example.recursiveupdate;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(environments = "foobar")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecursiveUpdate {

	@Inject
	@Client("/")
	RxHttpClient client;

	@Test
	@Order(1)
	void testA() {
		System.err.println("testA");
		var response = client.toBlocking().exchange(buildRequest(""), String.class);
		assertEquals("@", response.body());
	}

	@Test
	@Order(2)
	void testB() {
		System.err.println("testB");
		var response = client.toBlocking().exchange(buildRequest("test"), String.class);
		assertEquals("@test", response.body());
	}

	private MutableHttpRequest<Object> buildRequest(String host) {
		return HttpRequest.GET("/").header("Host", host);
	}

	@Requires(env = "foobar")
	@Controller
	static class RecursiveUpdateController {

		private final HostHeaderExtractor d;

		@Inject
		RecursiveUpdateController(HostHeaderExtractor d) {
			this.d = d;
		}

		@Get()
		@Produces(MediaType.TEXT_PLAIN)
		String index() {
			return "@" + d.extract();
		}
	}
}
