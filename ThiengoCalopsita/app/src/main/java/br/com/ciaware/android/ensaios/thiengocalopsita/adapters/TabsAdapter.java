package br.com.ciaware.android.ensaios.thiengocalopsita.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import br.com.ciaware.android.ensaios.thiengocalopsita.R;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.CarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.LuxuryCarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.OldCarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.PopularCarFragment;
import br.com.ciaware.android.ensaios.thiengocalopsita.fragments.SportCarFragment;

/**
 * Created by fbarb_000 on 19/11/2015.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] titles = new String[]{"TODOS", "LUXO", "ESPORTIVOS", "COLOCIONADOR", "POPULAR"};
    private int[] icons = new int[]{R.drawable.car_1, R.drawable.car_1, R.drawable.car_2, R.drawable.car_3, R.drawable.car_4};
    private int heightIcon;


    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);

        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon = (int)( 24 * scale + 0.5f );
    }

    @Override
    public Fragment getItem(int position) {
        Log.v("events Tabs getItem", "position: " + position);

        Fragment frag = null;


        if(position == 0){ // ALL CARS
            frag = new CarFragment();
        }
        else if(position == 1){ // LUXURY CAR
            frag = new LuxuryCarFragment();
        }
        else if(position == 2){ // SPORT CAR
            frag = new SportCarFragment();
        }
        else if(position == 3){ // OLD CAR
            frag = new OldCarFragment();
        }
        else if(position == 4){ // POPULAR CAR
            frag = new PopularCarFragment();
        }

        Bundle b = new Bundle();
        b.putInt("position", position);
        frag.setArguments(b);

        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        Log.v("events Tabs PageTitle", "position: " + position);
        return titles[position];
    }
}
