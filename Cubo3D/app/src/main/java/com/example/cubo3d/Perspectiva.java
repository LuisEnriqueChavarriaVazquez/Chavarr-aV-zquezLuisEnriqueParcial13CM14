package com.example.cubo3d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Perspectiva extends View implements View.OnTouchListener {
    Paint p;
    int centerX, centerY, maxX, maxY, minMaxXY;
    Obj obj = new Obj();
    Canvas ca;

    public Perspectiva(Context c){
        super(c);
        setOnTouchListener(this);
        centerX = maxX/2;
        centerY = maxY/2;
    }

    int iX(float x){
        return Math.round(centerX + x);
    }
    int iY(float y){
        return Math.round(centerY - y);
    }
    void line(Paint pa, int i, int j, Canvas c){
        Point2D p = obj.vScr[i], q = obj.vScr[j];
        c.drawLine(iX(p.x), iY(p.y), iX(q.x), iY(q.y),pa);
    }

    public void paint(Canvas c, Paint pa){
        ca=c;
        maxX = c.getWidth()-1;
        maxY = c.getHeight()-1;
        minMaxXY=Math.min(maxX, maxY);
        centerX = maxX/2;
        centerY = maxY/2;

        obj.d = obj.rho*minMaxXY/obj.objSize;
        obj.eyeAndScreen();
        p.setColor(Color.BLUE);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        c.drawPaint(paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        c.drawText("Ejemplo, puede rotar la figura", 10, 55, paint);

        line(p, 0, 1,c); // aristas horizontales inferiores
        line(p, 1, 2,c);
        line(p, 2, 0,c);

        line(p, 1, 5,c); // aristas horizontales superiores
        line(p, 2, 6,c);
        line(p, 0, 7,c);
    }

    protected void onDraw(Canvas c){

        super.onDraw(c);
        ca=c;
        p = new Paint();
        p.setColor(Color.WHITE); // Fondo Amarillo
        c.drawPaint(p);

       paint(c,p);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

            obj.theta = (float) ca.getWidth()/event.getX();
            obj.phi = (float) ca.getHeight()/event.getY();
            obj.rho = (obj.phi/obj.theta)*ca.getHeight();
            centerX =(int) event.getX();
            centerY =(int) event.getY();
            invalidate();
      /*  Toast t=Toast.makeText(v.getContext(),"se mueve",Toast.LENGTH_LONG);
        t.show();*/
        return true;
    }
}
