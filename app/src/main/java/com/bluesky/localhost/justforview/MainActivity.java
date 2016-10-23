package com.bluesky.localhost.justforview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bluesky.localhost.justforview.interfa.onChildClick;
import com.bluesky.localhost.justforview.newview.SubButton;
import com.bluesky.localhost.justforview.newview.floatingmenu;

public class MainActivity extends AppCompatActivity {
     private floatingmenu floatingmen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         floatingmen=(floatingmenu)findViewById(R.id.floatingmenu);
        floatingmen.setPosition(floatingmenu.RIGHT_TOP);
        floatingmen.setLayout(floatingmenu.Circle_Layout);
        floatingmen.setDistanct(200);

        floatingmen.setOnChildClick(new onChildClick() {
            @Override
            public void OnClildClick(int positon) {
                Toast.makeText(getApplicationContext(),positon+" is view",Toast.LENGTH_SHORT).show();
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
        switch (item.getItemId()){

            case R.id.distanct:
                floatingmen.setDistanct(300);
        }
        return super.onOptionsItemSelected(item);
    }
}
