package com.doodlepro.lniu.doodlepro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by lniu on 11/4/14.
 */
public class DoodleView extends View {
    Paint p;
    Path path;
    List<Pair<Path, Paint>> oldPaths;

    public DoodleView(Context context) {
        super(context);
    }

    // Without a style, called by inflater
    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        oldPaths = new ArrayList<Pair<Path, Paint>>();
        path = new Path();
        p = new Paint();
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.STROKE);
    }

    public void DrawTo(Canvas canvas) {
        for(Pair<Path, Paint> pair : oldPaths) {
            canvas.drawPath(pair.first, pair.second);
        }
        canvas.drawPath(path, p);
    }

    public void ChangePaint(Bundle bundle) {
        int color = bundle.getInt("Color");
        int size = bundle.getInt("Size");
        oldPaths.add(new Pair<Path, Paint>(path, p));
        path = new Path();
        p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(size);
        p.setStyle(Paint.Style.STROKE);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStype) {
        super(context, attrs, defStype);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw according time series.
        for(Pair<Path, Paint> pair : oldPaths) {
            canvas.drawPath(pair.first, pair.second);
        }
        canvas.drawPath(path, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                    path.moveTo(event.getX(), event.getY());
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                    path.lineTo(event.getX(), event.getY());
                postInvalidate();
                return true;
            case MotionEvent.ACTION_UP:
                return false;
        }
        return false;
    }


}
