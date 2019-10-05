package xyz.outofmysystem.h1.service;

import io.micronaut.context.annotation.Requires;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;
import xyz.outofmysystem.h1.api.SearchApi;
import xyz.outofmysystem.h1.api.SearchResponse;
import xyz.outofmysystem.h1.api.SearchResponse.HashtagResponse;
import xyz.outofmysystem.h1.api.SearchResponse.MentionResponse;
import xyz.outofmysystem.h1.api.SearchResponse.TweetResponse;
import xyz.outofmysystem.h1.config.TwitterConfig;

@Singleton
@Requires(beans = TwitterConfig.class)
public class SearchService implements SearchApi {

  // twitter4j client.
  private Twitter twitter;

  public SearchService(TwitterConfig config) {
    this.configureTwitter4j(config);
  }

  @Override
  public SearchResponse search(String query) throws TwitterException {
    Query twitterQuery = new Query(query).count(100);

    QueryResult result = twitter.search(twitterQuery);
    List<Status> tweets = result.getTweets();
    return this.formatTweets(query, tweets);
  }

  /**
   * Configures the Twitter4j twitter client via a given {@link TwitterConfig}.
   *
   * @param config {@link TwitterConfig} configuration variables.
   */
  private void configureTwitter4j(TwitterConfig config) {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true).setOAuthConsumerKey(config.getOauth().getConsumerKey())
        .setOAuthConsumerSecret(config.getOauth().getConsumerSecret())
        .setOAuthAccessToken(config.getOauth().getAccessToken())
        .setOAuthAccessTokenSecret(config.getOauth().getAccessTokenSecret());
    TwitterFactory factory = new TwitterFactory(cb.build());
    this.twitter = factory.getInstance();
  }

  /**
   * Formats a {@link List} of {@link Status}es from the twitter4j client.
   *
   * @param searchTerm {@link String} search term which resulted in given list of
   *                   tweets.
   * @param tweets     {@link List} of {@link Status}
   * @return {@link SearchResponse} a purpose-specific formatted response for
   *         consumption.
   */
  private SearchResponse formatTweets(String searchTerm, List<Status> tweets) {
    List<TweetResponse> formatted = new ArrayList<TweetResponse>();
    Map<String, Integer> hashtags = new HashMap<String, Integer>();
    Map<String, Integer> userMentions = new HashMap<String, Integer>();

    tweets.stream().forEach(tweet -> {
      formatted.add(this.formatTweet(tweet));
      HashtagEntity[] tags = tweet.getHashtagEntities();
      UserMentionEntity[] mentions = tweet.getUserMentionEntities();

      List.of(tags).stream().forEach(tag -> {
        String text = tag.getText();
        if (hashtags.containsKey(text)) {
          hashtags.put(text, Integer.valueOf(hashtags.get(text).intValue() + 1));
        } else {
          hashtags.put(text, Integer.valueOf(1));
        }
      });

      List.of(mentions).stream().forEach(mention -> {
        String username = mention.getScreenName();
        if(userMentions.containsKey(username)) {
          userMentions.put(username, Integer.valueOf(userMentions.get(username).intValue() + 1));
        } else {
          userMentions.put(username, Integer.valueOf(1));
        }
      });
    });

    return SearchResponse.builder().searchTerm(searchTerm).resultLength(Integer.valueOf(tweets.size()))
        .hashtags(hashtags.entrySet().stream().map(this::formatHashtag).collect(Collectors.toList()))
        .mentions(userMentions.entrySet().stream().map(this::formatMention).collect(Collectors.toList()))
        .tweets(formatted).build();
  }

  /**
   * Formats a single given tweet to a {@link TweetResponse}
   * @param tweet {@link Status} from the twitter4j client.
   * @return {@link TweetResponse}
   */
  private TweetResponse formatTweet(Status tweet) {
    return TweetResponse.builder()
        .id(tweet.getId())
        .text(tweet.getText()).user(tweet.getUser().getScreenName())
        .userUrl(tweet.getUser().getURL())
        .imageUrl(tweet.getUser().getProfileImageURL()).build();
  }

  private MentionResponse formatMention(Entry<String, Integer> entry) {
    return MentionResponse.builder().username(entry.getKey()).occurrence(entry.getValue()).build();
  }

  private HashtagResponse formatHashtag(Entry<String, Integer> entry) {
    return HashtagResponse.builder().tag(entry.getKey()).occurrence(entry.getValue()).build();
  }
}
