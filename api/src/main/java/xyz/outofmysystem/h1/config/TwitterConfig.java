package xyz.outofmysystem.h1.config;

import io.micronaut.context.annotation.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(TwitterConfig.PREFIX)
public class TwitterConfig {

  public static final String PREFIX = "twitter4j";

  private Boolean debug = false;
  private OAuth oauth = new OAuth();
  private String loggerFactory;

  @Data
  @ConfigurationProperties(OAuth.PREFIX)
  public static class OAuth {
    public static final String PREFIX = "oauth";

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;
  }
}