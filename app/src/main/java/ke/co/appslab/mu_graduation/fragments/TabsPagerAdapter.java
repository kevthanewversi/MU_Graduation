package ke.co.appslab.mu_graduation.fragments;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by root on 11/13/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch(index){
            case 0:
                return new ScheduleFragment();
            case 1:
                return new SpeakersFragment();
            case 2:
                return new SchoolsFragment();
            case 3:
                return new AwardsFragment();        }
        return null;
    }

    @Override
    public int getCount() {
        return TITLES.length();
    }
}
