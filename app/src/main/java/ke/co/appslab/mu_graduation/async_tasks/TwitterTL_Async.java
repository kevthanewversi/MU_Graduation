package ke.co.appslab.mu_graduation.async_tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import ke.co.appslab.mu_graduation.R;
import ke.co.appslab.mu_graduation.activities.MainActivity;
import ke.co.appslab.mu_graduation.twitter.Tweet;
import ke.co.appslab.mu_graduation.twitter.Twitter;

/**
 * Created by root on 7/22/16.
 */
public class TwitterTL_Async extends AsyncTask<String,Void,String> {
    final static String CONSUMER_KEY = "xGRqhGEhkWnSAc3jftAUvfEcs";
    final static String CONSUMER_SECRET = "R6mBaAEFOgKawumZ5OUlKBDEPVqiWop7WWsMLcTWK2RoVVfiDo";
    final static String TwitterTokenURL = "https://api.twitter.com/oauth2/token";
    final static String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";
    View rootView;
    Context context;
    //progress bar
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;


    public class Authenticated {
        String token_type;
        String access_token;

    }

    public TwitterTL_Async ( View rootView,Context context) {
        this.rootView = rootView;
        this.context = context;
        //progressBar  = (ProgressBar)rootView.findViewById(R.id.pbHeaderProgress);
        swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    protected String doInBackground(String... screennames) {
        String result = null;

        if (screennames.length >0 )
        {
            result = fetchTwitterStream(screennames[0]);
        }
        return result;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //show progress bar as data is being fetched
        //progressBar.setVisibility(View.VISIBLE);
    }

    private String fetchTwitterStream(String screenname) {
        String results = null;

        // Step 1: Encode consumer key and secret
        try {
            // URL encode the consumer key and secret
            String urlApiKey = URLEncoder.encode(CONSUMER_KEY, "UTF-8");
            String urlApiSecret = URLEncoder.encode(CONSUMER_SECRET, "UTF-8");

            // Concatenate the encoded consumer key, a colon character, and the
            // encoded consumer secret
            String combined = urlApiKey + ":" + urlApiSecret;

            // Base64 encode the string
            String base64Encoded = Base64.encodeToString(combined.getBytes(), Base64.NO_WRAP);

            // Step 2: Obtain a bearer token
            HttpPost httpPost = new HttpPost(TwitterTokenURL);
            httpPost.setHeader("Authorization", "Basic " + base64Encoded);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
            String rawAuthorization = getResponseBody(httpPost);
            Authenticated auth = jsonToAuthenticated(rawAuthorization);

            // Applications should verify that the value associated with the
            // token_type key of the returned object is bearer
            if (auth != null && auth.token_type.equals("bearer")) {

                // Step 3: Authenticate API requests with bearer token
                HttpGet httpGet = new HttpGet(TwitterStreamURL + screenname);

                // construct a normal HTTPS request and include an Authorization
                // header with the value of Bearer <>
                httpGet.setHeader("Authorization", "Bearer " + auth.access_token);
                httpGet.setHeader("Content-Type", "application/json");
                // update the results with the body of the response
                results = getResponseBody(httpGet);
            }
        } catch (UnsupportedEncodingException ex) {
        } catch (IllegalStateException ex1) {
        }
        return results;
    }

    private Authenticated jsonToAuthenticated(String rawAuthorization) {
        Authenticated auth = null;
        if (rawAuthorization != null && rawAuthorization.length() > 0) {
            try {
                Gson gson = new Gson();
                auth = gson.fromJson(rawAuthorization, Authenticated.class);
            } catch (IllegalStateException ex) {
                // just eat the exception
            }
        }
        return auth;
    }

    private String getResponseBody(HttpRequestBase request) {
        StringBuilder sb = new StringBuilder();
        try {

            DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            String reason = response.getStatusLine().getReasonPhrase();

            if (statusCode == 200) {

                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();

                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sb.append(line);
                }
            } else {
                sb.append(reason);
            }
        } catch (UnsupportedEncodingException UEex) {
        } catch (ClientProtocolException CPex) {
        } catch (IOException IOex) {
        }
        return sb.toString();
    }


    //get the result and convert the JSON object into an ArrayList of tweets
    @Override
    protected void onPostExecute(String result) {
        ArrayList<Tweet> tweets = jsonToTwitter(result);

        for(Tweet tweet:tweets)
        {
            Log.e("tweet",tweet.getText());
        }

        //initialize and set the adapter
        AbsListView mListView = (AbsListView)rootView.findViewById(android.R.id.list);
        try{
            //change this view
        ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(context,android.R.layout.simple_list_item_1,tweets);

        mListView.setAdapter(adapter);}
        catch(Exception e){
            e.printStackTrace();
        }
        //hide the progress bar after tweets are fetched
        //progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    private ArrayList jsonToTwitter(String result) {
        ArrayList<Tweet> tweets = null;
        if (result != null & result.length() >0 ){
            try{
           Gson gson = new Gson();
            tweets = gson.fromJson(result, Twitter.class);}

            catch (IllegalStateException ex) {
                // just eat the exception
            }
        }
        return tweets;
    }

}
