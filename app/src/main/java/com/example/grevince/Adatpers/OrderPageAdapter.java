package com.example.grevince.Adatpers;




import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.grevince.Fragments.Login;
import com.example.grevince.Fragments.Signup;


public class OrderPageAdapter extends FragmentStateAdapter {


    public OrderPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Login();

            case 1:
               return new Signup();

            default:
               return new Login();

        }



    }

    @Override
    public int getItemCount() {
        return 2;
    }
}