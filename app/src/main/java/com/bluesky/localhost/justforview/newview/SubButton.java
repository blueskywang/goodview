package com.bluesky.localhost.justforview.newview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bluesky.localhost.justforview.R;
import com.bluesky.localhost.justforview.util.pxdp;

/**
 * Created by localhost on 2016/10/16.
 */

public class SubButton extends View {
    private float size;
    private  static  final String TAG="SubButton";
    private Bitmap mBitmap;
    private  Bitmap newBitmap;
    private Paint mPaint;
    private  Paint sPaint;
    private  float bsize;
    private  int color;
    private BitmapDrawable drawable=null;
  public SubButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
      TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SubButton,defStyleAttr,0);
      size=a.getDimension(R.styleable.SubButton_subbuttonsize, pxdp.dip2px(context,48));
       drawable =(BitmapDrawable) a.getDrawable(R.styleable.SubButton_subbuttonIron);
      color=a.getColor(R.styleable.SubButton_subbuttoncolor, ContextCompat.getColor(context,R.color.colorPrimary));
      a.recycle();
      Bitmap mbitmap;
      if(drawable==null){
          mbitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_add_white_48dp);
      }
      else{
      mbitmap=drawable.getBitmap();}
      Log.i(TAG, "SubButton: "+color);
      bsize=size/2*14/10;
      float s=(bsize/(float) mbitmap.getWidth());
      //int 除以float 如果int<float 那么会返回0
      if(s<0){
          s=-s;
      }
      Matrix matrix = new Matrix();
      matrix.reset();
      // 长和宽放大缩小的比例
      int w=mbitmap.getWidth();
      int h=mbitmap.getHeight();
      matrix.postScale(s,s);
      newBitmap=Bitmap.createBitmap(mbitmap,0,0,w,h,matrix,true);
      mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
      mPaint.setColor(color);
      mPaint.setStyle(Paint.Style.FILL);
      this.setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
      //不支持硬件加速的
      mPaint.setShadowLayer(10f, 0.5f, 0.5f, Color.BLACK);
      sPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public SubButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int)size+30,(int)size+30);
    }

    public SubButton(Context context, float Size, int color, Bitmap mbitmap) {
       super(context);
        this.size=Size;
        this.color=color;
       this.mBitmap=mbitmap;
        Log.i(TAG, "SubButton: "+color);
         bsize=size/2*14/10;
        float s=(bsize/(float) mbitmap.getWidth());
        //int 除以float 如果int<float 那么会返回0
        if(s<0){
            s=-s;
        }
        Matrix matrix = new Matrix();
        matrix.reset();
        // 长和宽放大缩小的比例
        int w=mbitmap.getWidth();
        int h=mbitmap.getHeight();
        matrix.postScale(s,s);
        newBitmap=Bitmap.createBitmap(mbitmap,0,0,w,h,matrix,true);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        this.setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
        //不支持硬件加速的
        mPaint.setShadowLayer(10f, 2f, 2f, Color.BLACK);
        sPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    }

 /*   @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(size+10,size+10);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,size/2,mPaint);
        canvas.drawBitmap(newBitmap,(getWidth()-bsize)/2+getWidth()/250,(getHeight()-bsize)/2,sPaint);
    }
}
