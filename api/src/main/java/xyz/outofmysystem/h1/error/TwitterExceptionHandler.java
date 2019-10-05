package xyz.outofmysystem.h1.error;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

import twitter4j.TwitterException;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Requires(classes = { TwitterException.class, ExceptionHandler.class })
public class TwitterExceptionHandler implements ExceptionHandler<TwitterException, HttpResponse<ErrorBody>> {

  @Override
  public HttpResponse<ErrorBody> handle(HttpRequest request, TwitterException exception) {
    return HttpResponse.notFound(ErrorBody.builder()
        .code(504)
        .message("An error occured handling Twitter API request.")
        .details(exception.getMessage())
        .build());
  }

}