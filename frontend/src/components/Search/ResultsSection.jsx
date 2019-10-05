import React, { Component } from 'react';

class ResultsSection extends Component {
  render() {
    const { tweets, hashtags, mentions } = this.props.results;
    return <>
      <div className="container ">
        <h3 className="is-size-3">Search Results:</h3>
        <div className="columns">
          <div className="column">
            <h5>Top Tweets:</h5>
            <hr></hr>
            {
              tweets && tweets.length > 0 ?
              tweets.map(tweet => <TweetSummary tweet={tweet}></TweetSummary>) : null
            }
          </div>
          <div className="column">
            <h5>Top hashtags (#):</h5>
            <hr></hr>
            <table className="table is-fullwidth">
              <thead>
                <th>Hashtag (#)</th>
                <th>Occurrence</th>
              </thead>
              <tbody>
                {
                  hashtags && hashtags.length > 0 ?
                  hashtags.sort(sortByOccurrence).slice(0, 10).map((hashtag) =>
                    <HashTagAggregateRow hashtag={hashtag}></HashTagAggregateRow>) : null
                }
              </tbody>
            </table>
            <h5>Top mentions:</h5>
            <hr></hr>
            <table className="table is-fullwidth">
              <thead>
                <th>Username</th>
                <th>Occurrence</th>
              </thead>
              <tbody>
                {
                  mentions && mentions.length > 0 ?
                  mentions.sort(sortByOccurrence).slice(0, 10).map((mention) =>
                    <UserMentionAggregateRow mention={mention}></UserMentionAggregateRow>) : null
                }
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </>;
  }
}

const sortByOccurrence = (a, b) => {
  return b.occurrence - a.occurrence;
}

const TweetSummary = (props) => {
  const { tweet } = props;
  return <>
    <div className="box">
      <article className="media">
        <div className="media-left">
          <figure className="image is-64x64">
            <img src={tweet.imageUrl} alt="profile_img"></img>
          </figure>
        </div>
        <div class="media-content">
          <div class="content">
            <p>
              <strong>{tweet.user}</strong> <small>@{tweet.user}</small>
              <br></br>
              {tweet.text}
            </p>
          </div>
        </div>
      </article>
    </div>
  </>;
}

const HashTagAggregateRow = (props) => {
  const { tag, occurrence } = props.hashtag;
  const link = "https://twitter.com/hashtag/" + tag;
  return <>
    <tr>
      <td><a href={link}>{tag}</a></td>
      <td>{occurrence}</td>
    </tr>
  </>
}

const UserMentionAggregateRow = (props) => {
  const { url, username, occurrence } = props.mention;
  return <>
    <tr>
      <td><a href={url}>{username}</a></td>
      <td>{occurrence}</td>
    </tr>
  </>
}

export default ResultsSection;
