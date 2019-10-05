package xyz.outofmysystem.h1.api;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  private String searchTerm;
  private Integer resultLength;
  private List<HashtagResponse> hashtags;
  private List<TweetResponse> tweets;
  private List<MentionResponse> mentions;

  @Data
  @Builder
  public static class TweetResponse {
    private Long id;
    private String text;
    private String user;
    private String userUrl;
    private String imageUrl;
  }

  @Data
  @Builder
  public static class HashtagResponse {
    private String tag;
    private Integer occurrence;
  }

  @Data
  @Builder
  public static class MentionResponse {
    private String username;
    private Integer occurrence;
  }
}