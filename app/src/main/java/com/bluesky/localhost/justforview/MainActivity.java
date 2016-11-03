package com.bluesky.localhost.justforview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bluesky.localhost.justforview.base.BaseActivity;
import com.bluesky.localhost.justforview.base.activityname;
import com.bluesky.localhost.justforview.interfa.onChildClick;
import com.bluesky.localhost.justforview.newview.floatingmenu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private floatingmenu floatingmen;
    private final String a = "cloudmusicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        floatingmen = (floatingmenu) findViewById(R.id.floatingmenu);
        floatingmen.setLayout(floatingmenu.Circle_Layout);
        floatingmen.setDistanct(200);

        floatingmen.setOnChildClick(new onChildClick() {
            @Override
            public void OnClildClick(int positon) {
                if (positon == 0) {
                  /*  Intent intent=new Intent();
                    intent.setClass(getApplicationContext(),cloudmusicActivity.class);
                    startActivity(intent);*/
                    Intent intent = new Intent();
                    navitatoTo(activityname.getAcitvityName(a), intent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.distanct:
                floatingmen.setDistanct(300);
        }
        return super.onOptionsItemSelected(item);
    }
}
