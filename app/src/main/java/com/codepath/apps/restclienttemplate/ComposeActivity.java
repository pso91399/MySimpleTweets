package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    TwitterClient client;
    Context context;
    EditText etNewItem;
    TextView tvCharsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = new TwitterClient(this);
        context = this;
        etNewItem = (EditText) findViewById(R.id.etCompose);
        tvCharsLeft = (TextView) findViewById(R.id.tvCharsLeft);
        etNewItem.addTextChangedListener(mTextEditorWatcher);
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvCharsLeft.setText(String.valueOf(140 - s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    public void onTweet(View v) {

        //pb.setVisibility(ProgressBar.VISIBLE);

        //obtain a reference to the EditText created with the layout
        EditText etNewItem = (EditText) findViewById(R.id.etCompose);
        //grab the EditText's content as a string
        String itemText = etNewItem.getText().toString();

        client.sendTweet(itemText, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet = null;
                try {
                    //Intent data = new Intent();
                    //data.putExtra("tweet'", Parcels.wrap(tweet));
                    //setResult(RESULT_OK, data);
                    //pb.setVisibility(ProgressBar.INVISIBLE);
                    //finish();
                    tweet = Tweet.fromJSON(response);
                    Intent data = new Intent(context, TimelineActivity.class);
                    data.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                    context.startActivity(data);
                    Toast.makeText(getApplicationContext(), "Tweet tweeted", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //add the item to the list via the adapter
        //itemsAdapter.add(itemText);
        //store the updated list
        //writeItems();
        //clear the EditText by setting it to an empty string
        //etNewItem.setText("");
    }

}
