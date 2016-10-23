package com.bluesky.localhost.justforview.newview;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Switch;

import com.bluesky.localhost.justforview.R;
import com.bluesky.localhost.justforview.interfa.onChildClick;
import com.bluesky.localhost.justforview.util.pxdp;

import java.io.Closeable;

/**
 * Created by localhost on 2016/10/23.
 */

public class floatingmenu extends FrameLayout {
    private static final String TAG="floatingmenu";
    public static  final  int LEFT_TOP=0;
    public static  final  int LEFT_BOTTOM=1;
    public static  final  int RIGHT_TOP=2;
    public static  final  int RIGHT_BOTTOM=3;
    public static final  int Line_Layout=0;
    public static  final  int Circle_Layout=1;
    public int position;
    public   float distanct;//dp

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;

    }

    public float getDistanct() {
        return distanct;
    }

    public void setDistanct(float distanct) {
        this.distanct = distanct;
        invalidate();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;

    }

    public   int layout;
    private onChildClick monChlidClick;

    public Boolean getIsopen() {
        return isopen;
    }

    public void setIsopen(Boolean isopen) {
        this.isopen = isopen;
    }

    private Boolean isopen=false;

    public floatingmenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public floatingmenu(Context context) {
        this(context,null);
    }

    public floatingmenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.floatingmenu,defStyleAttr,0);
        position=a.getInt(R.styleable.floatingmenu_mainposition,LEFT_TOP);
        distanct= a.getDimension(R.styleable.floatingmenu_distanct,pxdp.dip2px(context,100));
        layout=a.getInt(R.styleable.floatingmenu_floatingmenulayout,Circle_Layout);
        a.recycle();
       /* initClick();*/
        //初始化过程中没有等到childview
    }
      public  void setOnChildClick(onChildClick th){
          this.monChlidClick=th;
      }
    private void initClick() {
        int childcount=getChildCount();
        Log.i(TAG, "initClick: "+childcount);
        for(int i=0;i<childcount;i++) {
            final int k=i;
            if (i == childcount-1){
                getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!getIsopen()) {
                        Log.i(TAG, "onClick: ");
                         openAnimation();
                    }
                    else closeAnimation();
                }
            });
        }
            else {
                getChildAt(i).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        monChlidClick.OnClildClick(k);
                    }
                });
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int childcount=getChildCount();
        Log.i(TAG, "onLayout: "+childcount);
        int height=getChildAt(0).getHeight();
        int width=getChildAt(0).getWidth();
        int l=0,t=0,r=0,b=0;
        switch (position) {
            case LEFT_TOP:
                l = 0;
                t = 0;
                r = width;
                b = height;
                break;
            case LEFT_BOTTOM:
                r = width;
                b = getMeasuredHeight();
                l = 0;
                t = getMeasuredHeight() - height;
                break;
            case RIGHT_TOP:
                r = getMeasuredWidth();
                l = getMeasuredWidth() - width;
                t = 0;
                b = height;
                break;
            case RIGHT_BOTTOM:
                r = getMeasuredWidth();
                b = getMeasuredHeight();
                l = getMeasuredWidth() - width;
                t = getMeasuredHeight() - height;
                break;
        }
        for(int i=0;i<childcount;i++){
                View view=getChildAt(i);
                view.layout(l,t,r,b);
        }
        initClick();
    }
         private void openAnimation(){
             int count=getChildCount();
             for(int i=0;i<count-1;i++){
                /* ObjectAnimator openAnimator=ObjectAnimator.ofFloat(getChildAt(i),"translationY",0f,200*(i+1));
                 Log.i(TAG, "openAnimation: "+i);*/
                 if(layout==Line_Layout) {

                         if(position==LEFT_TOP||position==RIGHT_TOP){
                         PropertyValuesHolder sx = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
                         PropertyValuesHolder sy = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
                         PropertyValuesHolder translationUp = PropertyValuesHolder.ofFloat("translationY", 0f, distanct * (i + 1));
                         PropertyValuesHolder tras = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
                         PropertyValuesHolder trasy = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
                         ObjectAnimator openAnimator = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), sx, sy, translationUp, tras, trasy).setDuration(500);
                         openAnimator.setInterpolator(new AccelerateInterpolator());
                         openAnimator.setStartDelay(300 * i);
                         openAnimator.start();
                         }
                     else {
                             PropertyValuesHolder sx = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
                             PropertyValuesHolder sy = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
                             PropertyValuesHolder translationUp = PropertyValuesHolder.ofFloat("translationY", 0f, -distanct*(i+1));
                             PropertyValuesHolder tras = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
                             PropertyValuesHolder trasy = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
                             ObjectAnimator openAnimator = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), sx, sy, translationUp, tras, trasy).setDuration(500);
                             openAnimator.setInterpolator(new AccelerateInterpolator());
                             openAnimator.setStartDelay(300 * i);
                             openAnimator.start();
                         }

                 }
                 else{
                     circleAnimator();
                 }
             }
             setIsopen(true);
         }



    private  void closeAnimation() {
             int count = getChildCount();
             if (layout == Line_Layout) {
                 if(position==LEFT_TOP||position==RIGHT_TOP) {
                     for (int i = 0; i < count - 1; i++) {
                         PropertyValuesHolder sx = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
                         PropertyValuesHolder sy = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
                         PropertyValuesHolder translationUp = PropertyValuesHolder.ofFloat("translationY", distanct * (i + 1), 0f);
                         PropertyValuesHolder tras = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
                         PropertyValuesHolder trasy = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
                         ObjectAnimator openAnimator = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), sx, sy, translationUp, tras, trasy).setDuration(500);
                         openAnimator.setInterpolator(new AccelerateInterpolator());
                         openAnimator.setStartDelay(300 * i);
                         openAnimator.start();
                     }
                 }
                     else{
                         for (int i = 0; i < count - 1; i++) {
                             PropertyValuesHolder sx = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
                             PropertyValuesHolder sy = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
                             PropertyValuesHolder translationUp = PropertyValuesHolder.ofFloat("translationY", -distanct*(i+1), 0f);
                             PropertyValuesHolder tras = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
                             PropertyValuesHolder trasy = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
                             ObjectAnimator openAnimator = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), sx, sy, translationUp, tras, trasy).setDuration(500);
                             openAnimator.setInterpolator(new AccelerateInterpolator());
                             openAnimator.setStartDelay(300 * i);
                             openAnimator.start();
                         }
                 }

             }else{
                 ClosecircleAnimator();
             }
             setIsopen(false);
         }
    private void circleAnimator() {
        int count = getChildCount() - 1;
        for(int i=0;i<count;i++) {
            PropertyValuesHolder sx = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
            PropertyValuesHolder sy = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
            PropertyValuesHolder tras = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
            PropertyValuesHolder trasy = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
            float offsetX=0;
            float offsetY=0;
            int cl = (int) (distanct * Math.sin(Math.PI / 2 / (count - 1) * i));
            int ct = (int) (distanct * Math.cos(Math.PI / 2 / (count - 1) * i));
            int Yflag=1;
            int Xflag=1;
            //弧度计算
            switch (position) {
                case LEFT_TOP:
                    break;
                case LEFT_BOTTOM:
                    Yflag=-1;
                    break;
                case RIGHT_TOP:
                    Xflag=-1;
                    break;
                case RIGHT_BOTTOM:
                    Xflag=-1;
                    Yflag=-1;
                    break;
            }
            PropertyValuesHolder trY = PropertyValuesHolder.ofFloat("translationY", 0f,cl*Yflag);

            PropertyValuesHolder trX = PropertyValuesHolder.ofFloat("translationX", 0f, ct*Xflag);
            ObjectAnimator a=ObjectAnimator.ofPropertyValuesHolder(getChildAt(i),tras,trasy,trX,trY,sy,sx).
                    setDuration(500);
            a.setInterpolator(new DecelerateInterpolator());
            a.setStartDelay(300*i);
            a.start();
        }
    }
    private void ClosecircleAnimator() {
        int count = getChildCount() - 1;
        for(int i=0;i<count;i++) {
            PropertyValuesHolder sx = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f);
            PropertyValuesHolder sy = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f);
            PropertyValuesHolder tras = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
            PropertyValuesHolder trasy = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
            float offsetX=0;
            float offsetY=0;
            int cl = (int) (distanct * Math.sin(Math.PI / 2 / (count - 1) * i));
            int ct = (int) (distanct * Math.cos(Math.PI / 2 / (count - 1) * i));
            int Yflag=1;
            int Xflag=1;
            //弧度计算
            switch (position) {
                case LEFT_TOP:
                    break;
                case LEFT_BOTTOM:
                    Yflag=-1;
                    break;
                case RIGHT_TOP:
                    Xflag=-1;
                    break;
                case RIGHT_BOTTOM:
                    Yflag=-1;
                    Yflag=-1;
                    break;
            }
            PropertyValuesHolder trY= PropertyValuesHolder.ofFloat("translationY", cl*Yflag,0f);

            PropertyValuesHolder trX = PropertyValuesHolder.ofFloat("translationX", ct*Xflag, 0f);
            ObjectAnimator a=ObjectAnimator.ofPropertyValuesHolder(getChildAt(i),tras,trasy,trX,trY,sy,sx).
                    setDuration(500);
            a.setInterpolator(new DecelerateInterpolator());
            a.setStartDelay(300*i);
            a.start();
        }
    }
}
