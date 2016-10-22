package com.bluesky.localhost.justforview.newview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by localhost on 2016/10/16.
 */

public class SubButton extends View {
    private int size;
    private  static  final String TAG="SubButton";
    private Bitmap mBitmap;
    private  Bitmap newBitmap;
    private Paint mPaint;
    private  Paint sPaint;
    private  float bsize;
    private  int color;
 /*   public SubButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SubButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }*/



    public SubButton(Context context, int Size, int color, Bitmap mbitmap) {
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
