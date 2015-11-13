package ke.co.appslab.mu_graduation;

import android.app.ListFragment;
import android.widget.AbsListView;

/**
 * Created by root on 11/13/15.
 */
public abstract class ScrollTabHolderFragment extends ListFragment implements ScrollTabHolder {

    protected ScrollTabHolder mScrollTabHolder;

    public ScrollTabHolderFragment()
    {
    }

    @Override
    public void onScroll(AbsListView abslistview, int a, int b, int c, int d) {

    }
    public void setScrollTabHolder(ScrollTabHolder scrolltabholder)
    {
        mScrollTabHolder = scrolltabholder;
    }
}
