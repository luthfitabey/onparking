package com.anjilibey.onpark;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hp on 06/05/2018.
 */

public class SharedPrefManager {
    public static final String SP_ONPARK = "spOnPark";
    public static final String SP_TOKEN = "spToken";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";


    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_ONPARK, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public String getSpToken (){return sp.getString(SP_TOKEN, "token"); }
    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }


}
