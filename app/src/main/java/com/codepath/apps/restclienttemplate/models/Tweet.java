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

    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = TwitterClient.getRelativeTimeAgo(jsonObject.getString("created_at"));
        tweet.user = User.fromJSON((jsonObject.getJSONObject("user")));
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
}
