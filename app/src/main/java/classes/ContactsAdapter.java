package classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.grevince.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import api.Responses;

public class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView remark;
        public TextView too;
        public TextView from;
        public TextView monthdate;
        public TextView fulldate;
        public MaterialButton statusbutton;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.responseremark);
            remark=itemView.findViewById(R.id.responseremark);
            too=itemView.findViewById(R.id.responsetoo);
            from=itemView.findViewById(R.id.responsefrom);
             monthdate=itemView.findViewById(R.id.datemonthresponse);
            fulldate=itemView.findViewById(R.id.dateyearday);
           statusbutton=itemView.findViewById(R.id.responsestatusidd);
        }
    }
    private List<Responses> mContacts;

    // Pass in the contact array into the constructor
    public ContactsAdapter(List<Responses> contacts) {
        mContacts = contacts;
    }
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.activity_check, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Responses contact = mContacts.get(position);

        // Set item views based on your views and data model
        viewHolder.remark.setText(contact.getRemark());
        viewHolder.from.setText(contact.getDfrom());
        viewHolder.too.setText(contact.getDto());
        String month=contact.getCtime().substring(5,7);
        String datee="No";
        if(month.equals("01")){
            month="JAN";
        }
        if(month.equals("02")){
            month="FEB";
        }
        if(month.equals("03")){
            month="MAR";
        }
        if(month.equals("04")){
            month="APR";
        }
        if(month.equals("05")){
            month="MAY";
        }
        if(month.equals("06")){
            month="JUN";
        }
        if(month.equals("07")){
            month="JUL";
        }
        if(month.equals("08")){
            month="AUG";
        }
        if(month.equals("09")){
            month="SEP";
        }
        if(month.equals("10")){
            month="OCT";
        }
        if(month.equals("11")){
            month="NOV";
        }
        if(month.equals("12")){
            month="DEC";
        }
        viewHolder.monthdate.setText(month);
        String year=contact.getCtime().substring(0,4);
        String datenumber=contact.getCtime().substring(8,10);
        String fulldatenow=year+","+datenumber;

        viewHolder.statusbutton.setText(contact.getStatus());
        viewHolder.fulldate.setText(fulldatenow);


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}