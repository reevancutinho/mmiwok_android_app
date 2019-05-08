package com.example.android.mmiwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by rex on 24-Jul-18.
 */

class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{
    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new FamilyFragment();
        } else if(position ==2) {
            return new ColorsFragment();
        } else{
            return new PhrasesFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0) {
            return mContext.getString(R.string.number_name);
        }else if(position==1) {
            return mContext.getString(R.string.family_name);
        }else if(position==2) {
            return mContext.getString(R.string.colors_name);
        }else{
            return mContext.getString(R.string.phrases_name);
        }
    }

    @Override
    public int getCount(){
        return 4;
    }
}
