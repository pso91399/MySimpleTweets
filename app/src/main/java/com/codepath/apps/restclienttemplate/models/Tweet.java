package com.codepath.apps.restclienttemplate.models;

import com.codepath.apps.restclienttemplate.TwitterClient;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {

    public String body;
    public long uid;
    public User user;
    public String createdAt;

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = TwitterClient.getRelativeTimeAgo(jsonObject.getString("created_at"));
        tweet.user = User.fromJSON((jsonObject.getJSONObject("user")));
        return tweet;
    }
}
