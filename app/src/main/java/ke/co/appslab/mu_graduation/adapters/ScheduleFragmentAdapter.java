package ke.co.appslab.mu_graduation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ke.co.appslab.mu_graduation.R;

/**
 * Created by root on 1/20/16.
 */
public class ScheduleFragmentAdapter extends RecyclerView.Adapter<ScheduleFragmentAdapter.ContactViewHolder> {

    private List<ContactInfo> contactList;
    private Context mContext;


    //pass the contact array into the constructor
    public ScheduleFragmentAdapter(List<ContactInfo> contactList) {
        this.contactList = contactList;
    }



    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.schedule_recyclerview, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vSurname;

        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.scheduletitle);
            vSurname = (TextView) v.findViewById(R.id.scheduledesc);
        }
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vSurname.setText(ci.surname);

    }

    @Override
    public int getItemCount() {

        return contactList.size();
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

}
