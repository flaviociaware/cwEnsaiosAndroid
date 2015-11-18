package br.com.ciaware.android.ensaios.thiengocalopsita.model;

import android.util.Log;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

/**
 * Created by fbarb_000 on 17/11/2015.
 */

public class ProfileDrawerItemCw extends ProfileDrawerItem {
    public static boolean profileListOpen = false;


    public static void setAccountHeader(AccountHeader accountHeader) {
        for (IProfile profile : accountHeader.getProfiles()) {
            if (profile instanceof  ProfileDrawerItemCw) {
                ((ProfileDrawerItemCw) profile).accountHeader=accountHeader;
            }
        }
    }

    private AccountHeader accountHeader;

    public ProfileDrawerItemCw() {
        this.accountHeader = null;
    }

    public ProfileDrawerItemCw(AccountHeader accountHeader) {
        this.accountHeader = accountHeader;
    }
    @Override
    public StringHolder getEmail() {
        Log.v("events getEmail()", Boolean.toString(ProfileDrawerItemCw.profileListOpen));

        StringHolder result = email;

//        if ((this.accountHeader!=null) && (this.accountHeader.isSelectionListShown())) {
//            result = name;
//        }

        if (ProfileDrawerItemCw.profileListOpen) {
            result = name;
        }

        return result;
    }

}

