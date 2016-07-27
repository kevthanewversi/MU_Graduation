package ke.co.appslab.mu_graduation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ke.co.appslab.mu_graduation.R;
import ke.co.appslab.mu_graduation.twitter.Tweet;
import ke.co.appslab.mu_graduation.twitter.TwitterUser;

/**
 * Created by root on 7/27/16.
 */
public class TwitterStreamAdapter extends ArrayAdapter {

    private ArrayList<Tweet> tweets;
    Context context;
    int resource;
    TwitterUser twitterUser;
    public TwitterStreamAdapter(Context context, int resource, ArrayList<Tweet> tweets) {
        super(context, resource, tweets);
        this.tweets = tweets;
        this.context = context;
        this.resource = resource;
    }

    public int getCount(){
        return tweets.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.tweets_view, null);
            viewHolder = new ViewHolder();
            viewHolder.listItemTxtView = (TextView) convertView.findViewById(R.id.tweet);
            viewHolder.listItemImgView = (ImageView) convertView.findViewById(R.id.twitter_avatar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.listItemTxtView.setText(tweets.get(position).toString());
        //place picasso jar into libs folder of your project and use it for download and  set images like this
        Picasso.with(context).load(twitterUser.getProfileImageUrl()).into(viewHolder.listItemImgView);
        //viewHolder.listItemImgView.setImageResource();
        return convertView;
    }

    private class ViewHolder {
        TextView listItemTxtView;
        ImageView listItemImgView;
    }

    public Object getItem(int position) {
        return tweets.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
}

