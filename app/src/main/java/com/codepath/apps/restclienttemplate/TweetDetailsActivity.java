package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class TweetDetailsActivity extends AppCompatActivity {


    TwitterClient client;
    TextView tvDetailBody;
    TextView tvDetailScreenName;
    TextView tvDetailUser;
    Tweet tweet;
    ImageView ivDetailProfileImage;
    String id;
    Boolean liked;
    Boolean retweeted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

        id = tweet.getId();
        liked = tweet.isLike();
        retweeted = tweet.isRetweet();

        tvDetailBody = (TextView) findViewById(R.id.tvDetailBody);
        tvDetailScreenName = (TextView) findViewById(R.id.tvDetailScreenName);
        tvDetailUser = (TextView) findViewById(R.id.tvDetailUser);
        ivDetailProfileImage = (ImageView)findViewById(R.id.ivDetailProfileImage);
        Glide.with(this).load(tweet.getUser().profileImageUrl).into(ivDetailProfileImage);
        client = new TwitterClient(this);

        Log.d("TweetDetailsActivity", "Showing tweet details.");

        tvDetailBody.setText(tweet.getBody());
        tvDetailScreenName.setText(tweet.getUser().screenName);
        tvDetailUser.setText(tweet.getUser().name);

    }

    public void onLike(View v) {
        if (!tweet.isLike()) {
            tweet.setLike(true);
            Log.d("TweetDetailsActivity", "Tweet liked");
            client.sendLike(id, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        } else {
            tweet.setLike(false);
            client.sendUnlike(id, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        }
    }

    public void onRetweet(View v) {
        if (!tweet.isRetweet()) {
            tweet.setRetweet(true);
            Log.d("TweetDetailsActivity", "Tweet retweeted");
            client.sendRetweet(id, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });
        } else {
            tweet.setRetweet(false);
            client.sendUnretweet(id, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });
        }
    }
}