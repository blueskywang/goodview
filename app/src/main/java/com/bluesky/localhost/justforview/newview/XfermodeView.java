package com.bluesky.localhost.justforview.newview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bluesky.localhost.justforview.R;

/**
 * Created by localhost on 2016/11/4.
 */

public class XfermodeView extends View {
    private  int mode=0;
    private Paint mpaint;
    private Paint cPaint;
    private PorterDuff.Mode fmode=PorterDuff.Mode.CLEAR;
    private   static  final  String TAG="XfermodeView";
    public XfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XfermodeView(Context context) {
        this(context,null);
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.XfermodeView,defStyleAttr,0);
        mode=a.getInt(R.styleable.XfermodeView_Xfermode_mode,0);
        a.recycle();
        setPaint();
    }

    public void setPaint() {
        mpaint = new Paint();
        cPaint=new Paint();
        cPaint.setAntiAlias(true);
        mpaint.setColor(Color.BLUE);
        mpaint.setAntiAlias(true);
        mpaint.setStyle(Paint.Style.FILL);

    }

    private PorterDuff.Mode getmode(){
        PorterDuff.Mode a=PorterDuff.Mode.CLEAR;
        switch (mode){
            case Type.Clear:
                a= PorterDuff.Mode.CLEAR;
                break;
            case Type.draken:
                a= PorterDuff.Mode.DARKEN;
                break;
            case Type.dstatop:
                a= PorterDuff.Mode.DST_ATOP;
                break;
            case Type.dst:
                a= PorterDuff.Mode.DST;
                break;
            case Type.dstin:
                a= PorterDuff.Mode.DST_IN;
                break;
            case Type.dstout:
                a= PorterDuff.Mode.DST_OUT;
                break;
            case Type.dstover:
                a= PorterDuff.Mode.DST_OVER;
                break;
            case Type.lighten:
                a= PorterDuff.Mode.LIGHTEN;
                break;
            case Type.multiply:
                a= PorterDuff.Mode.MULTIPLY;
                break;
            case Type.screen:
                a= PorterDuff.Mode.SCREEN;
                break;
            case Type.src:
                a= PorterDuff.Mode.SRC;
                break;
            case Type.srcatop:
                a= PorterDuff.Mode.SRC_ATOP;
                break;
            case Type.srcln:
                a= PorterDuff.Mode.SRC_IN;
                break;
            case Type.srcout:
                a= PorterDuff.Mode.SRC_OUT;
                break;
            case Type.srcover:
                a= PorterDuff.Mode.SRC_OVER;
                break;
            case Type.xor:
                a= PorterDuff.Mode.XOR;
                break;
            default:break;
        }
        return a;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(500,500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_HARDWARE, mpaint);
        int sc=canvas.saveLayer(0,0,500,500,null,Canvas.ALL_SAVE_FLAG);
        Rect mrect=new Rect(0,0,200,200);
        canvas.drawRect(mrect,mpaint);
        mpaint.setXfermode(new PorterDuffXfermode(fmode));
        canvas.drawBitmap(getBitmap(),0,0,mpaint);
        canvas.restoreToCount(sc);
    }

    public void setPaintmode(PorterDuff.Mode d) {
        mpaint.reset();
        fmode =d;
        mpaint.setColor(Color.BLUE);
        mpaint.setAntiAlias(true);
        mpaint.setStyle(Paint.Style.FILL);
        invalidate();
    }
    private Bitmap getBitmap(){
        int w=getWidth();
        int h=getHeight();
        Bitmap output=Bitmap.createBitmap(w, h,Bitmap.Config.ARGB_8888);
        Canvas mcanvas=new Canvas(output);
        Rect mrect=new Rect(0,0,200,200);
        cPaint.setColor(Color.YELLOW);
        cPaint.setAntiAlias(true);
        cPaint.setStyle(Paint.Style.FILL);
        mcanvas.drawCircle(200,200,100,cPaint);
     return output;
    }

}
