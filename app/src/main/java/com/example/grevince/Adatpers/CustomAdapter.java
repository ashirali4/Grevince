package com.example.grevince.Adatpers;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.grevince.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import api.User;

public class CustomAdapter extends ArrayAdapter<User>
       // implements View.OnClickListener
{

    private ArrayList<User> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        Typeface MyFont;
        TextView month;
        TextView year;

    }

    public CustomAdapter(ArrayList<User> data, Context context) {
        super(context, R.layout.list_item_complain_list, data);
        this.dataSet = data;
        this.mContext=context;

    }


    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        User dataModel=(User) object;

        switch (v.getId())
        {
            case R.id.compid:
                Snackbar.make(v, "Release date " +dataModel.getCompid(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_complain_list, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.compid);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.subject);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.ctime);
            viewHolder.month = (TextView) convertView.findViewById(R.id.datemonth);
            viewHolder.year = (TextView) convertView.findViewById(R.id.dateyear);
        //     viewHolder.MyFont= Typeface.createFromAsset(mContext.getAssets(), "font/poppinsbold.ttf");


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        lastPosition = position;
        String date=dataModel.getCtime();
        String ctime=date.substring(8,10);
        String year=date.substring(0,4);
        String month=date.substring(5,7);
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
        String id="Complain ID - #000"+dataModel.getCompid();
        viewHolder.txtName.setText(id);
        viewHolder.txtType.setText(dataModel.getSubject());
        viewHolder.txtVersion.setText(ctime);
        viewHolder.month.setText(month);
        viewHolder.year.setText(year);
      //  viewHolder.txtVersion.setTypeface(viewHolder.MyFont);
        viewHolder.txtVersion.setTypeface(ResourcesCompat.getFont(mContext, R.font.poppins_bold));
        viewHolder.txtType.setTypeface(ResourcesCompat.getFont(mContext, R.font.poppins_semibold));
        viewHolder.txtName.setTypeface(ResourcesCompat.getFont(mContext, R.font.poppins_bold));
        viewHolder.month.setTypeface(ResourcesCompat.getFont(mContext, R.font.poppins_bold));
        viewHolder.year.setTypeface(ResourcesCompat.getFont(mContext, R.font.poppins_bold));

        //viewHolder.info.setOnClickListener(this);
      // viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}