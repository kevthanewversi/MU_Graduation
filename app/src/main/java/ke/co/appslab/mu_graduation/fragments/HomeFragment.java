package ke.co.appslab.mu_graduation.fragments;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.astuetz.PagerSlidingTabStrip;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.Random;

import android.os.Handler;

import ke.co.appslab.mu_graduation.R;
import ke.co.appslab.mu_graduation.ScrollTabHolder;
import ke.co.appslab.mu_graduation.ScrollTabHolderFragment;

/**
 * Created by root on 11/13/15.
 */
public class HomeFragment extends Fragment implements ScrollTabHolder, android.support.v4.view.ViewPager.OnPageChangeListener {
    public Context ctxt;



    public class TabsPagerAdapter extends FragmentPagerAdapter {

        private  String TITLES[] = (new String[]{"schedule", "speakers", "schools", "awards"/*ctxt.getResources().getString(R.string.schedule),ctxt.getResources().getString(R.string.speakers),
             ctxt.getResources().getString(R.string.schools),ctxt.getResources().getString(R.string.awards)*/});
        private ScrollTabHolder mListener;
        private SparseArrayCompat mscrollTabHolders;



        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
//        TITLES = (new String[]{ctxt.getApplicationContext().getString(R.string.schedule),ctxt.getApplicationContext().getString(R.string.speakers),
//                ctxt.getApplicationContext().getString(R.string.schools),ctxt.getApplicationContext().getString(R.string.awards)});
            mscrollTabHolders = new SparseArrayCompat();

        }

        @Override
        public Fragment getItem(int index) {
            ScrollTabHolderFragment scrolltabholderfragment = null;
            if (index == 0) {
                scrolltabholderfragment = (ScrollTabHolderFragment) ScheduleFragment.newInstance(index);
            }
            if (index == 1) {
                scrolltabholderfragment = (ScrollTabHolderFragment) SpeakersFragment.newInstance(index);
            }
            if (index == 2) {
                scrolltabholderfragment = (ScrollTabHolderFragment) SchoolsFragment.newInstance(index);
            }
            if (index == 3) {
                scrolltabholderfragment = (ScrollTabHolderFragment) AwardsFragment.newInstance(index);

            }

            mscrollTabHolders.put(index, scrolltabholderfragment);
            if (mListener != null) {
                scrolltabholderfragment.setScrollTabHolder(mListener);
            }
            return scrolltabholderfragment;
        }
        @Override
        public int getCount() {
            return TITLES.length;
        }

        public CharSequence getPageTitle(int i) {
            return TITLES[i];
        }

        public SparseArrayCompat getScrollTabHolders() {
            return mscrollTabHolders;
        }

        public void setTabHolderScrollingContent(ScrollTabHolder scrolltabholder) {
            mListener = scrolltabholder;
        }
    //end of TabsPagerAdapter
    }

//continuing of HomeFragment
    KenBurnsView slideimage;
    Activity activity;
    int  photos [];
    Handler handler;



    public HomeFragment() {
        // Required empty public constructor

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        final long interval = 9000;
        activity = getActivity();

        //can activity be removed
        if(activity != null & isAdded()){ //to handle fragment is not attached error

            photos = new int[] {R.drawable.moi_uni,R.drawable.moi_uni2};
        }
        handler = new Handler();
        Runnable changeimage = new Runnable() {

            @Override
            public void run() {
                try{
                    //do your code here
                    int i = (new Random()).nextInt(photos.length);
                    slideimage.setImageResource(photos[i]);
                    //also call the same runnable
                    handler.postDelayed(this, interval);
                }
                catch (Exception e) {
                    e.printStackTrace();
                   // Log.e("Mine","Didn't work");
                }
                finally{
                    //also call the same runnable
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(changeimage, 1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tabbed, container, false);
        View view = inflater.inflate(R.layout.fragment_tabbed, container, false);

        slideimage = (KenBurnsView)view.findViewById(R.id.kbview);
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabsPagerAdapter(getFragmentManager()));

        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip)view.findViewById(R.id.tabs);
        tabsStrip.setViewPager(viewPager);
        return view;
    }
    public void  OnViewCreated(final View handler, Bundle bundle){
     super.onViewCreated(handler, bundle);

        }





    public static Fragment newInstance(int i) {
        HomeFragment tabbedFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("position",i);
        tabbedFragment.setArguments(args);
        return tabbedFragment;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void adjustScroll(int i) {

    }

    @Override
    public void onScroll(AbsListView abslistview, int firstvisibleItem, int visibleItemcount, int totalItemcount, int pagePosition) {

    }
}