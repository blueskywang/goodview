package com.bluesky.localhost.justforview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bluesky.localhost.justforview.base.BaseActivity;
import com.bluesky.localhost.justforview.newview.rotationImageview;
import com.bluesky.localhost.justforview.util.BlurUtil;
import com.bluesky.localhost.justforview.util.Imagedesign;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by localhost on 2016/10/29.
 */

public class cloudmusicActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.singer)
    ImageView singer;
    @BindView(R.id.line)
    ImageView line;
    @BindView(R.id.line1)
    RelativeLayout line1;
    @BindView(R.id.Rotationimage)
    rotationImageview Rotationimage;
    @BindView(R.id.forc)
    ImageView forc;
    public static String imageUrl = "http://img1.imgtn.bdimg.com/it/u=4187692638,491142665&fm=21&gp=0.jpg";
    private boolean isplay;
    private float syx = 0.2f;
    private float syy = 0.12f;
    private int ofx;
    private int imagew;
    private int imageh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloudmusic);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        imagew = dm.widthPixels;
        imageh = dm.heightPixels;
        imagew += 100;
        imageh += 100;
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setAlpha(0f);
       /* setRotateAnimation(imageView2);*/
      /* singer.post(new Runnable() {
            @Override
            public void run() {
                imageh=singer.getMeasuredHeight();
                imagew=singer.getMeasuredWidth();
                Glide
                        .with(cloudmusicActivity.this) // could be an issue!
                        .load(imageUrl)
                        .asBitmap()   //强制转换Bitmap
                        .into(target);

            }
        });*/

       /* ViewGroup.LayoutParams lp=line.getLayoutParams();
        lp.height=(int)(ofx*(1.2)*100)/100;
        lp.height=200;
        line.setLayoutParams(lp);*/
       /* Imagedesign.LoadImage(imageView2, this, "http://img1.imgtn.bdimg.com/it/u=4187692638,491142665&fm=21&gp=0.jpg");*/
        /*Glide.with(this)
                .load("http://img1.imgtn.bdimg.com/it/u=4187692638,491142665&fm=21&gp=0.jpg")
                .centerCrop()
                .fitCenter()
                .into(singer);*/
        Glide
                .with(cloudmusicActivity.this) // could be an issue!
                .load(imageUrl)
                .asBitmap()   //强制转换Bitmap
                .into(target);
    }

    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
            Rotationimage.setSingerBitmap(resource);
            Matrix matrix = new Matrix();
            float sx = (imagew / (float) resource.getWidth());
            float sy = (imageh / (float) resource.getHeight());
            //int 除以float 如果int<float 那么会返回0
            matrix.reset();
            // 长和宽放大缩小的比例
            int w = resource.getWidth();
            int h = resource.getHeight();
            matrix.postScale(sx, sy);
            Bitmap newBitmap = Bitmap.createBitmap(resource, 0, 0, w, h, matrix, true);
            Imagedesign.LoadImageForBitmap(singer, getApplicationContext(), BlurUtil.doBlur(newBitmap, 8, 20));
        }
    };

    public void startAnimation() {
        RotateAnimation play = new RotateAnimation(0, -25, Animation.RELATIVE_TO_SELF,
                syx, Animation.RELATIVE_TO_SELF,
                syy);
        play.setDuration(500);
        play.setFillAfter(true);
        forc.startAnimation(play);
    }
    private void stopAnimation() {
        RotateAnimation play = new RotateAnimation(-25, 0, Animation.RELATIVE_TO_SELF,
                syx, Animation.RELATIVE_TO_SELF,
                syy);

        play.setDuration(500);
        play.setFillAfter(true);
        forc.startAnimation(play);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void setRotateAnimation(View v) {
      /*  RotateAnimation b=new RotateAnimation(0,359f,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        b.setDuration(2000);
        //0 和360 是一样的
        b.setInterpolator(new LinearInterpolator());
        b.setFillAfter(true);
        b.setRepeatCount(-1);
        v.startAnimation(b);*/
        ObjectAnimator animatorx = ObjectAnimator.ofFloat(v, "rotation", 0, 360);
       animatorx.setInterpolator(new LinearInterpolator());
        animatorx.setRepeatCount(ValueAnimator.INFINITE);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(5000);
        set.playTogether(animatorx);
        set.start();

    }

    @OnClick(R.id.Rotationimage)
    public void onClick() {
        if (isplay) {
            stopAnimation();
            Rotationimage.stopRotateAnimator();
            isplay = !isplay;
        } else {
            startAnimation();
           Rotationimage.setRotateAnimator();
            isplay = !isplay;
        }
    }
}
