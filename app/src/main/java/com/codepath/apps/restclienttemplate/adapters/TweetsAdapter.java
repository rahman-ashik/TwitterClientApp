package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TimeFormatter;
import com.codepath.apps.restclienttemplate.models.LinkifiedTextView;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;


    // pass in data


    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // inflate
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }
    // bind
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);

        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear() {// Clean all elements of the recycler
        tweets.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<Tweet> tweetListlist) { // Add a list of items -- change to type used
        tweets.addAll(tweetListlist);
        notifyDataSetChanged();
    }








    //viewholder definition

    public class ViewHolder extends RecyclerView.ViewHolder {

        TimeFormatter tf;
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView postedTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfileImage= itemView.findViewById(R.id.profileImage);
            tvBody= itemView.findViewById(R.id.tweetBody);
            tvScreenName = itemView.findViewById(R.id.userName);
            postedTime = itemView.findViewById(R.id.postedTime);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText("@"+tweet.user.screenName);
            Glide.with(context).load(tweet.user.profileImageURL).into(ivProfileImage);


            tf = new TimeFormatter();
            String temp= tf.getTimeDifference(tweet.createdAt) ;
            postedTime.setText(temp);
        }
    }


}
