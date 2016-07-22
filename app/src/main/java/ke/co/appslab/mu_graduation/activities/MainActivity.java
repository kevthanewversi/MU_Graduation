package ke.co.appslab.mu_graduation.activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import ke.co.appslab.mu_graduation.fragments.AboutFragment;
import ke.co.appslab.mu_graduation.fragments.AwardsFragment;
import ke.co.appslab.mu_graduation.fragments.HelpFragment;
import ke.co.appslab.mu_graduation.fragments.HomeFragment;
import ke.co.appslab.mu_graduation.fragments.MapsFragment;
import ke.co.appslab.mu_graduation.fragments.NavigationDrawerFragment;

import ke.co.appslab.mu_graduation.R;
import ke.co.appslab.mu_graduation.fragments.ScheduleFragment;
import ke.co.appslab.mu_graduation.fragments.SchoolsFragment;
import ke.co.appslab.mu_graduation.fragments.SpeakersFragment;
import ke.co.appslab.mu_graduation.fragments.TwitterStreamFragment;

import static ke.co.appslab.mu_graduation.fragments.NavigationDrawerFragment.NavigationDrawerCallbacks;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks,ScheduleFragment.OnFragmentInteractionListener,SpeakersFragment.OnFragmentInteractionListener,SchoolsFragment.OnFragmentInteractionListener,AwardsFragment.OnFragmentInteractionListener,MapsFragment.OnFragmentInteractionListener,HelpFragment.OnFragmentInteractionListener,AboutFragment.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        //main fragment will be the home fragment

        switch (position)
        {case 0:
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance(position + 1))
                    .commit();
            break;
            case 1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MapsFragment.newInstance(position + 1))
                        .commit();
                break;
         
            case 4:
                rateApp();
                break;

            case 5:
                shareApp();
                break;

            case 6:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, HelpFragment.newInstance(position + 1))
                        .commit();
                break;

            case 7:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, AboutFragment.newInstance(position + 1))
                        .commit();
                break;
        }
    }


    public void onSectionAttached(int number) {
        String sections_array[] = getResources().getStringArray(R.array.section_titles);
        if(number >= 1){
            mTitle = sections_array[number-1];
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    private void rateApp() {



        //////THIS RATE CODE DOES WORK AND I DON'T KNOW WHY, I ALSO DON'T CARE FOR NOW....EVER!
//        try {
//            PackageManager pm = getPackageManager();
//            String playstoreURL = "market://details?id=";
//            String appID = pm.getPackageInfo(getPackageName(), 0).packageName;
//            String rateURL = (new StringBuilder()).append(playstoreURL).append(appID).toString();
//            Intent rate = new Intent("android.intent.action.VIEW");
//            rate.setType("text/plain");
//            rate.putExtra("android.intent.name.TEXT", rateURL);
//            //we add the following flags to go back to our application after user rates app and presses back button
//            rate.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
//                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
//                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//            //now we fire the intent
//            try {
//                startActivity(rate);
//            } catch (ActivityNotFoundException anfe) {
//                Log.e("ANFE", "Playstore not installed!");
//                viewinBrowser(context, rateURL);
//            }
//        }
//
//        catch(PackageManager.NameNotFoundException nameNotFoundException){
//            nameNotFoundException.printStackTrace();
//        }


        PackageManager pm = getPackageManager();
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        }
        catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }





    }


    private  void shareApp(){


        try {
            PackageManager pm = getPackageManager();
            String playstoreURL = "https://play.google.com/store/apps/details?id=";
            String appID = pm.getPackageInfo(getPackageName(),0).packageName;
            String shareURL = (new StringBuilder()).append(playstoreURL).append(appID).toString();
            Intent share = new Intent("android.intent.action.SEND");
            share.setType("text/plain");
            share.putExtra("android.intent.action.TEXT",  (new StringBuilder()).append("Get Moi University's graduation day app for Android here:").append(shareURL).toString());
            share.putExtra("android.intent.action.SUBJECT","MU Graduation app");
            startActivity(Intent.createChooser(share,"Share app via"));
        }

        catch(PackageManager.NameNotFoundException nameNotFoundException){
            nameNotFoundException.printStackTrace();
        }
    }

    public void viewinBrowser(Context cxt, String rateURL){


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}