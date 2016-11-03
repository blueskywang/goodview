package com.bluesky.localhost.justforview.newview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bluesky.localhost.justforview.R;
import com.bluesky.localhost.justforview.cloudmusicActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by localhost on 2016/10/31.
 */

public class rotationImageview extends View
{
    private  static  final  String TAG="rotationImageview";
    private Paint blackPaint;
    private float degree=30;
    private Paint ImagePaint;
    private Paint mpaint;
    private Bitmap BlackBitmap;
    private  Context mcontext;
    private Bitmap SingerBitmap=null;
    private  long CurrentTime;
    private String imageUrl=cloudmusicActivity.imageUrl;
    ObjectAnimator animatorx ;
    public rotationImageview(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public rotationImageview(Context context) {
        this(context,null);
    }

    public rotationImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext=context;
        setPaint();
        /*if(imageUrl!=null){
            Glide
                    .with(mcontext) // could be an issue!
                    .load(imageUrl)
                    .asBitmap()   //强制转换Bitmap
                    .into(target);}*/
    }

    private void setPaint() {
        ImagePaint=new Paint();
        mpaint=new Paint();
        BlackBitmap= BitmapFactory.decodeResource(mcontext.getResources(), R.mipmap.a9x);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthmode=MeasureSpec.getMode(widthMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int heightmode=MeasureSpec.getMode(heightMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int w;
        int h;
        if(widthmode==MeasureSpec.EXACTLY){
            w=width;
        }else{
            w=BlackBitmap.getWidth();
        }
        if(heightmode==MeasureSpec.EXACTLY){
            h=height;
        }
        else h=BlackBitmap.getHeight();

        setMeasuredDimension(w,h);
    }
   /* private SimpleTarget target=new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
             SingerBitmap=resource;
             postInvalidate();
        }
    };*/
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        double flag=0.17;
        Rect mDestRect =new Rect((int)(BlackBitmap.getWidth()*flag),
                (int)(BlackBitmap.getHeight()*flag) ,
                getWidth()-(int)(BlackBitmap.getWidth()*flag),
                getHeight()-(int)(BlackBitmap.getHeight()*flag));
        if(SingerBitmap!=null){
            Log.i(TAG, "onDraw: "+SingerBitmap.getHeight());
            SingerBitmap=getCircleBitmap(SingerBitmap);
            Rect mSrcRect = new Rect(0, 0, SingerBitmap.getWidth(), SingerBitmap.getHeight());
            canvas.drawBitmap(SingerBitmap,mSrcRect,mDestRect,ImagePaint);
        }
        Rect drect= new Rect(0,0,BlackBitmap.getWidth(),BlackBitmap.getHeight());
        ImagePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        canvas.drawBitmap(BlackBitmap,drect,
                          drect,ImagePaint);
    }
    public void setSingerBitmap(Bitmap n){
        SingerBitmap=n;
        invalidate();
    }
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        float bitW=(float)( BlackBitmap.getWidth()*0.7);
        float bitH=(float)( BlackBitmap.getHeight()*0.7);
        Matrix a=new Matrix();
        float sx=(float) bitW/(float)bitmap.getWidth();
        float sy=(float) bitH/(float)bitmap.getHeight();
        a.postScale(sx,sy);
        Bitmap d=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),a,true);
        Bitmap output = Bitmap.createBitmap(d.getWidth(),
                d.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0, d.getWidth(), d.getHeight());
        mpaint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        mpaint.setColor(color);
        int x = d.getWidth();
        canvas.drawCircle(x / 2, x / 2, x / 2, mpaint);
        mpaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(d, rect, rect, mpaint);
        return output;
    }

  /*  public void setRotateDegree() {
        handler.post(runnable);
    }

    Runnable runnable=new Runnable(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
           degree+=1;
            postInvalidate();
            handler.postDelayed(this,1000000);
        }
    };*/
    //转太快了
  @SuppressLint("NewApi")
   public   void setRotateAnimator(){
             animatorx = ObjectAnimator.ofFloat(this, "rotation", 0, 360);
      //objectanimator默认是先加速后减速的 设置Interpolator 并设置无限循环
            animatorx.setInterpolator(new LinearInterpolator());
            animatorx.setRepeatCount(ValueAnimator.INFINITE);
            animatorx.setDuration(5000);
            animatorx.start();
             animatorx.setCurrentPlayTime(CurrentTime);
    }
    @SuppressLint("NewApi")
    public void stopRotateAnimator(){
          CurrentTime=animatorx.getCurrentPlayTime();
           animatorx.cancel();
    }
}
