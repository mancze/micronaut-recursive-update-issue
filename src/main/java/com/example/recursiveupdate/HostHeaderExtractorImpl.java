package com.example.recursiveupdate;

import io.micronaut.runtime.http.scope.RequestScope;

import javax.inject.Inject;

@RequestScope
class HostHeaderExtractorImpl implements HostHeaderExtractor {

	private final String host;

	@Inject
	HostHeaderExtractorImpl(CurrentHttpHeaders headers) {
		host = headers.getOrNull("Host");
	}

	@Override
	public String extract() {
		return host;
	}
}
