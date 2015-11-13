package ke.co.appslab.mu_graduation;

import android.widget.AbsListView;

/**
 * Created by root on 11/13/15.
 */
public interface ScrollTabHolder {
    public abstract void adjustScroll(int i);

    public abstract void onScroll(AbsListView abslistview, int a, int b, int c, int d);
}
