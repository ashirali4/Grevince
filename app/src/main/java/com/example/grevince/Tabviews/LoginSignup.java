package com.example.grevince.Tabviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.grevince.Adatpers.OrderPageAdapter;
import com.example.grevince.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        ViewPager2 viewPager2=findViewById(R.id.viewpager);
        viewPager2.setAdapter(new OrderPageAdapter(this));
        TabLayout tableLayout=findViewById(R.id.tablelayoutforsearch);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(
                tableLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {

            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Sign In");
                        tab.setIcon(R.drawable.ic_password);
                        break;
                    case 1:
                        tab.setText("Sign Up");
                        tab.setIcon(R.drawable.ic_add);
                      //  BadgeDrawable bd=tab.getOrCreateBadge();
                       // bd.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                       // bd.setVisible(true);
                      //  bd.setNumber(100);
                        break;

                }
            }
        }
        );
        tabLayoutMediator.attach();
    }
}
