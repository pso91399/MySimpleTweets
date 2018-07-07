package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TwitterClient;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Tweet {

    String body;
    long uid;
    User user;
    String createdAt;
    String id;
    Boolean like;
    Boolean retweet;

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = TwitterClient.getRelativeTimeAgo(jsonObject.getString("created_at"));
        tweet.user = User.fromJSON((jsonObject.getJSONObject("user")));
        tweet.id = jsonObject.getString("id_str");

        tweet.like = jsonObject.getBoolean("favorited");
        tweet.retweet = jsonObject.getBoolean("retweeted");

        return tweet;
    }

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public User getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public boolean isLike() {
        return like;
    }

    public boolean isRetweet() {
        return retweet;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public void setRetweet(Boolean retweet) {
        this.retweet = retweet;
    }
}
