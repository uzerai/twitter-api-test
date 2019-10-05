package xyz.outofmysystem.h1.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import twitter4j.TwitterException;
import xyz.outofmysystem.h1.api.SearchApi;
import xyz.outofmysystem.h1.api.SearchResponse;
import xyz.outofmysystem.h1.service.SearchService;

@Slf4j
@Controller("/search")
@RequiredArgsConstructor
public class SearchController implements SearchApi {

  // autowired via micronaut (with @RequiredArgsConstructor)
  public final SearchService service;

  @Override
  @Get("/")
  public SearchResponse search(@QueryValue String query) throws TwitterException{
    log.info("GET /search/query?={} | Searching for {}", query, query);
    return service.search(query);
  }

}