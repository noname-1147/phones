package demo.infrastructure;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
public class IllegalArgumentExceptionHandler implements ExceptionHandler<IllegalArgumentException, HttpResponse<JsonError>> {
    @Override
    public HttpResponse<JsonError> handle(final HttpRequest request, final IllegalArgumentException exception) {
        final JsonError error = new JsonError(exception.getMessage());
        error.link(Link.SELF, Link.of(request.getUri()));
        return HttpResponse.badRequest(error);
    }
}
