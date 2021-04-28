package com.example.cubo3d;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Vector;

public class Generado extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Perspectiva_nuevo l = new Perspectiva_nuevo(this);
        setContentView(l);
    }

    public class Perspectiva_nuevo extends View implements View.OnTouchListener {

        int defaultValor = -1;
        Intent intent = getIntent();
        int Vector1_1 = intent.getIntExtra("Vector1_1", defaultValor);
        int Vector1_2 = intent.getIntExtra("Vector1_2", defaultValor);
        int Vector1_3 = intent.getIntExtra("Vector1_3", defaultValor);
        int Vector2_1= intent.getIntExtra("Vector2_1", defaultValor);
        int Vector2_2= intent.getIntExtra("Vector2_2", defaultValor);
        int Vector2_3= intent.getIntExtra("Vector2_3", defaultValor);
        int Vector3_1= intent.getIntExtra("Vector3_1", defaultValor);
        int Vector3_2= intent.getIntExtra("Vector3_2", defaultValor);
        int Vector3_3= intent.getIntExtra("Vector3_3", defaultValor);


        String ter = String.valueOf(Vector1_1);
        String ter1 = String.valueOf(Vector1_2);
        String ter2 = String.valueOf(Vector1_3);
        String ter3 = String.valueOf(Vector2_1);
        String ter4 = String.valueOf(Vector2_2);
        String ter5 = String.valueOf(Vector2_3);
        String ter6 = String.valueOf(Vector3_1);
        String ter7 = String.valueOf(Vector3_2);
        String ter8 = String.valueOf(Vector3_3);

        double Vector1_1d = Vector1_1;
        double Vector1_2d = Vector1_2;
        double Vector1_3d = Vector1_3;
        double Vector2_1d = Vector2_1;
        double Vector2_2d = Vector2_2;
        double Vector2_3d = Vector2_3;
        double Vector3_1d = Vector3_1;
        double Vector3_2d = Vector3_2;
        double Vector3_3d = Vector3_3;

        double vol = ((Vector1_1d*Vector2_2d*Vector3_3d)+(Vector1_2d*Vector2_3d*Vector3_1d)+(Vector2_1d*Vector3_2d*Vector1_3d)-(Vector1_3d*Vector2_2d*Vector3_1d)-(Vector1_2d*Vector2_1d*Vector3_3d)-(Vector2_3d*Vector3_2d*Vector1_1d))/6;

        String volString = String.valueOf(Math.abs(vol));

        Paint p;
        int centerX, centerY, maxX, maxY, minMaxXY;
        Obj_nuevo obj = new Obj_nuevo(Vector1_1,Vector1_2,Vector1_3,Vector2_1,Vector2_2,Vector2_3,Vector3_1,Vector3_2,Vector3_3);
        Canvas ca;


        public Perspectiva_nuevo(Context c){
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
            p.setColor(Color.BLACK);

            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.cielo));
            paint.setStyle(Paint.Style.FILL);
            c.drawPaint(paint);


            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            paint.setColor(Color.BLACK);
            paint.setTextSize(45);
            c.drawText("Coordenadas", 10, 55, paint);
            paint.setTextSize(30);
            c.drawText("Vector 1 /// X= " + ter + " Y= " + ter1 + " Z= " + ter2 , 10, 105, paint);
            c.drawText("Vector 2 /// X= " + ter3 + " Y= " + ter4 + " Z= " + ter5 , 10, 155, paint);
            c.drawText( "Vector 3 /// X= " + ter6 + " Y= " + ter7 + " Z= " + ter8 , 10, 205, paint);
            c.drawText( "Volumen = " + volString + "u\u00B3", 10, 255, paint);

            paint.setColor(getResources().getColor(R.color.rojo));
            paint.setTextSize(25);
            c.drawText( "Eje X", (width/2) + 50, 100, paint);
            paint.setColor(getResources().getColor(R.color.azul));
            c.drawText( "Eje Z", width/2, (height/2)+100, paint);
            paint.setColor(getResources().getColor(R.color.verde));
            c.drawText( "Eje Y", 20, (height/2)+50, paint);

            line(p, 0, 1,c);
            line(p, 1, 2,c);
            line(p, 2, 0,c);

            line(p, 1, 5,c);
            line(p, 2, 6,c);
            line(p, 0, 7,c);


            paint.setColor(getResources().getColor(R.color.rojo));
            c.drawLine(width/2,0,width/2, 10000, paint);

            paint.setColor(getResources().getColor(R.color.azul));
            c.drawLine(0,0,width/2, height/2, paint);
            c.drawLine(width/2,height/2,width*2, height*2, paint);

            paint.setColor(getResources().getColor(R.color.verde));
            c.drawLine(0,height/2,10000, height/2, paint);

        }

        protected void onDraw(Canvas c){
            super.onDraw(c);
            ca=c;
            p = new Paint();
            p.setColor(getResources().getColor(R.color.cielo));
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
            return true;
        }

        public class Obj_nuevo{
            float rho, theta=0.3F, phi=1.3F, d, objSize, v11, v12, v13, v21, v22, v23, v32, v33, v43;
            Point3D [] w;
            Point2D [] vScr;

            Obj_nuevo(int Vector1_1,int Vector1_2,int Vector1_3,int Vector2_1,int Vector2_2,int Vector2_3,int Vector3_1,int Vector3_2,int Vector3_3){	// CAMBIAR LAS COORDENADAS X,Y,Z CON 0,1 PARA CONSTRUIR PRISMA, CILINDRO, PIRAMIDE, CONO Y ESFERA.
                w	= new Point3D[8];
                vScr	= new Point2D[8];


                w[0] = new Point3D(Vector1_1/2, Vector1_2/2, Vector1_3/2);
                w[1] = new Point3D(Vector2_1/2, Vector2_2/2, Vector2_3/2);
                w[2] = new Point3D(Vector3_1/2, Vector3_2/2, Vector3_3/2);
                w[3] = new Point3D(0, 0, 0);
                w[4] = new Point3D(0, 0, 1);
                w[5] = new Point3D(0, 0, 1);
                w[6] = new Point3D(0, 0, 1);
                w[7] = new Point3D(0, 0, 1);

                objSize = (float) Math.sqrt(12F);
                rho	= 5*objSize;
            }
            void initPersp(){
                float costh = (float)Math.cos(theta), sinth=(float)Math.sin(theta), cosph=(float)Math.cos(phi), sinph=(float)Math.sin(phi);
                v11 = -sinth; v12 = -cosph*costh; v13 = sinph*costh;
                v21 = costh; v22 = -cosph*sinth; v23 = sinph*sinth;
                v32 = sinph; v33 = cosph; v43 = -rho;
            }
            void eyeAndScreen(){
                initPersp();
                for(int i=0; i<8; i++){
                    Point3D p = w[i];
                    float x = v11*p.x + v21*p.y, y = v12*p.x + v22*p.y + v32*p.z, z = v13*p.x + v23*p.y + v33*p.z + v43;
                    vScr[i] = new Point2D(-d*x/z, -d*y/z);
                }
            }
        }


    }
}