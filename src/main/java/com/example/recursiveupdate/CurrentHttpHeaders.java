package com.example.recursiveupdate;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.runtime.http.scope.RequestScope;

import javax.inject.Inject;
import java.util.Optional;

@RequestScope
public class CurrentHttpHeaders {
	private final CurrentRequestProvider currentRequestProvider;

	@Inject
	public CurrentHttpHeaders(CurrentRequestProvider currentRequestProvider) {
		this.currentRequestProvider = currentRequestProvider;
	}

	public String getOrNull(String header) {
		HttpHeaders httpHeaders = getRequestHeadersOrNull();

		if (httpHeaders != null) {
			return httpHeaders.get(header);
		}

		return null;
	}

	private HttpHeaders getRequestHeadersOrNull() {
		Optional<HttpRequest<Object>> objectHttpRequest = currentRequestProvider.get();
		System.err.println(objectHttpRequest.get().getHeaders());

		HttpRequest<Object> request = objectHttpRequest.orElse(null);

		return request == null ? null : request.getHeaders();
	}
}
