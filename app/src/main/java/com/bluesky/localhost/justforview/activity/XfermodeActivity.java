package com.bluesky.localhost.justforview.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bluesky.localhost.justforview.R;
import com.bluesky.localhost.justforview.base.BaseActivity;
import com.bluesky.localhost.justforview.newview.XfermodeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by localhost on 2016/11/4.
 */

public class XfermodeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.Xfermode)
    XfermodeView Xfermode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.justfortry);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.xrermodemenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PorterDuff.Mode a= PorterDuff.Mode.CLEAR;
        String k="";
        switch (item.getItemId()){
            case R.id.clear:
                a= PorterDuff.Mode.CLEAR;
                k=(String) item.getTitle();
                setToolbarTitle(k);
                Xfermode.setPaintmode(a);
                break;
            case R.id.draken:
                a= PorterDuff.Mode.DARKEN;
                k=(String) item.getTitle();
                setToolbarTitle(k);
                Xfermode.setPaintmode(a);
                break;
            case R.id.dstatop:
                a= PorterDuff.Mode.DST_ATOP;
                k=(String) item.getTitle();
                setToolbarTitle(k);
                Xfermode.setPaintmode(a);
                break;
            case R.id.dst:
                a= PorterDuff.Mode.DST;
                k=(String) item.getTitle();
                setToolbarTitle(k);
                Xfermode.setPaintmode(a);
                break;
            case R.id.dstin:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.DST_IN;
                Xfermode.setPaintmode(a);
                break;
            case R.id.dstout:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.DST_OUT;
                Xfermode.setPaintmode(a);
                break;
            case R.id.dstover:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.DST_OVER;
                Xfermode.setPaintmode(a);
                break;
            case R.id.lighten:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.LIGHTEN;
                Xfermode.setPaintmode(a);
                break;
            case R.id.multiply:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.MULTIPLY;
                Xfermode.setPaintmode(a);
                break;
            case R.id.screen:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.SCREEN;
                Xfermode.setPaintmode(a);
                break;
            case R.id.src:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.SRC;
                Xfermode.setPaintmode(a);
                break;
            case R.id.srcatop:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.SRC_ATOP;
                Xfermode.setPaintmode(a);
                break;
            case R.id.srcln:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.SRC_IN;
                Xfermode.setPaintmode(a);
                break;
            case R.id.srcout:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.SRC_OUT;
                Xfermode.setPaintmode(a);
                break;
            case R.id.srcover:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.SRC_OVER;
                Xfermode.setPaintmode(a);
                break;
            case R.id.xor:
                k=(String) item.getTitle();
                setToolbarTitle(k);
                a= PorterDuff.Mode.XOR;
                Xfermode.setPaintmode(a);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbarTitle(String k) {
        toolbar.setTitle(k);
    }


}
