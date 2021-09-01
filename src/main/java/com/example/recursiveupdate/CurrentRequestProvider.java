package com.example.recursiveupdate;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.context.ServerRequestContext;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CurrentRequestProvider {
	public <T> Optional<HttpRequest<T>> get() {
		return ServerRequestContext.currentRequest();
	}
}
