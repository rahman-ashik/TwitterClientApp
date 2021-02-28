package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeActivity extends AppCompatActivity {

    public static final String TAG= "ComposeActivity";
    private static final int MAX_TWEET_SIZE = 120;

    EditText etCompose; // the composer window, editable
    Button btnTweet;    // the button to call the API
    TextView text_count;// the textView which shows updated character count from the composer
    TwitterClient client;
    ImageView ivProfileImage;
    TextView tvScreenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client = TwitterApp.getRestClient(this);


        //=== View Finders ====//
        etCompose = findViewById(R.id.editTextTextMultiLine);
        btnTweet = findViewById(R.id.postButton);
        //ivProfileImage= findViewById(R.id.profileImage2);
        //tvScreenName = findViewById(R.id.userName2);

        text_count = findViewById(R.id.textcounter);
        text_count.setText("0/120");



        //=========== textListener =======================//
        //this is the implementation of textListener
        final TextWatcher txwatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text_count.setText(String.valueOf(s.length()+ "/120"));

                if (s.length()>MAX_TWEET_SIZE) {
                    btnTweet.setBackgroundColor(Color.RED);
                    Toast.makeText(ComposeActivity.this, "Too long to post", Toast.LENGTH_SHORT).show();
                } else {
                    btnTweet.setBackgroundColor(Color.parseColor("#4086FF"));
                }

            }
            public void afterTextChanged(Editable s) {
            }
        };
        etCompose.addTextChangedListener(txwatcher);
        //============================================//

        //========add a click listener to the button========//
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //api call to push the post to the server
                final String tweetText = etCompose.getText().toString();
                if (tweetText.isEmpty()) {
                    //Toast.makeText(ComposeActivity.this, "Tweet is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tweetText.length()> MAX_TWEET_SIZE){
                    Toast.makeText(ComposeActivity.this, "Too long to post", Toast.LENGTH_SHORT).show();
                }

                client.postTweet(tweetText, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        Log.i(TAG, "Tweet pushed successfully! onSuccess");
                        try {
                            Tweet tweet = Tweet.fromJson(json.jsonObject);
                            Log.i(TAG, "Published successfully: "+tweet.body);
                            Intent i= new Intent();
                            i.putExtra("tweet", Parcels.wrap(tweet));
                            setResult(RESULT_OK,i);
                            startActivity(i);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        Log.e(TAG, "Tweet wasn't pushed! onFailure");
                        Log.e(TAG, response + " "+ statusCode);
                    }
                });

                //Toast.makeText(ComposeActivity.this, tweetText, Toast.LENGTH_SHORT).show();
            }
        });

    }
}