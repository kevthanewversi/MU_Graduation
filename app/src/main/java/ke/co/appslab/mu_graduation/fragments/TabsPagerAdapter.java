package ke.co.appslab.mu_graduation.fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import ke.co.appslab.mu_graduation.R;
import ke.co.appslab.mu_graduation.ScrollTabHolder;
import ke.co.appslab.mu_graduation.ScrollTabHolderFragment;

/**
 * Created by root on 11/13/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    private final String TITLES[];
    private ScrollTabHolder mListener;
    private SparseArrayCompat mscrollTabHolders;
    Context ctxt;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        TITLES = (new String[]{ctxt.getResources().getString(R.string.schedule),ctxt.getResources().getString(R.string.speakers),
                ctxt.getResources().getString(R.string.schools),ctxt.getResources().getString(R.string.awards)});
        mscrollTabHolders = new SparseArrayCompat();

    }

    @Override
    public Fragment getItem(int index) {
        ScrollTabHolderFragment scrollTabHolderfragment = null;
        switch(index){
            case 0:
                scrollTabHolderfragment = ScheduleFragment.newInstance(index);
                break;
            case 1:
                scrollTabHolderfragment = (ScrollTabHolderFragment)SpeakersFragment.newInstance(index);
                break;
            case 2:
                scrollTabHolderfragment = (ScrollTabHolderFragment)SchoolsFragment.newInstance(index);
                break;
            case 3:
                scrollTabHolderfragment = (ScrollTabHolderFragment)AwardsFragment.newInstance(index);
                break;  }

        mscrollTabHolders.put(index,scrollTabHolderfragment);
        if (mListener != null){
         scrollTabHolderfragment.setScrollTabHolder(mListener);
        }

        return scrollTabHolderfragment;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    public CharSequence getPageTitle(int i)
    {
        return TITLES[i];
    }

    public SparseArrayCompat getScrollTabHolders()
    {
        return mscrollTabHolders;
    }

    public void setTabHolderScrollingContent(ScrollTabHolder scrolltabholder)
    {
        mListener = scrolltabholder;
    }

}
