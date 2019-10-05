package xyz.outofmysystem.h1.api;

import twitter4j.TwitterException;

public interface SearchApi {

  public SearchResponse search(String query) throws TwitterException;

}