package com.bluesky.localhost.justforview.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by localhost on 2016/10/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public  void navitatoTo(final  String activityname, final Intent intent){
        Class<?> ca=null;
        try {
            ca=Class.forName(activityname);
            if(ca!=null){
                intent.setClass(this,ca);
                this.startActivity(intent);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
