package com.example.cubo3d;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Perspectiva l = new Perspectiva(this);
        setContentView(l);
    }

    public class Perspectiva extends View implements View.OnTouchListener {
        double vol = ((1*1*-1)+(-1*-1*-1)+(1*1*-1)-(-1*1*-1)-(-1*1*-1)-(-1*1*1))/6;

        String volString = String.valueOf(Math.abs(vol));

        Paint p;
        int centerX, centerY, maxX, maxY, minMaxXY;
        Obj_nuevo obj = new Obj_nuevo();
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
            p.setColor(Color.BLACK); // Fondo Amarillo
        /*line(p, 0, 1,c); line(p, 1, 2,c); line(p, 2, 3,c); line(p, 3, 0,c); // aristas horizontales inferiores
        line(p, 4, 5,c); line(p, 5, 6,c); line(p, 6, 7,c); line(p, 7, 4,c); // aristas horizontales superiores
        line(p, 0, 4,c); line(p, 1, 5,c); line(p, 2, 6,c); line(p, 3, 7,c); // aristas verticales*/

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
            c.drawText("Vector 1 /// X= " + 1 + " Y= " + -1 + " Z= " + -1 , 10, 105, paint);
            c.drawText("Vector 2 /// X= " + 1 + " Y= " + 1 + " Z= " + -1 , 10, 155, paint);
            c.drawText( "Vector 3 /// X= " + -1 + " Y= " + 1 + " Z= " + -1 , 10, 205, paint);
            c.drawText( "Volumen = 0.666667" + "u\u00B3", 10, 255, paint);

            paint.setColor(getResources().getColor(R.color.rojo));
            paint.setTextSize(25);
            c.drawText( "Eje X", (width/2) + 50, 100, paint);
            paint.setColor(getResources().getColor(R.color.azul));
            c.drawText( "Eje Z", width/2, (height/2)+100, paint);
            paint.setColor(getResources().getColor(R.color.verde));
            c.drawText( "Eje Y", 20, (height/2)+50, paint);

            /*paint.setColor(Color.BLACK);
            c.drawText( "Punto A " + "("+ Vector1_1+","+Vector1_2+")" , Vector1_1, Vector1_2, paint);
            c.drawText( "Punto B" + "("+ Vector2_1+","+Vector2_2+")", Vector2_1, Vector2_2, paint);
            c.drawText( "Punto C" + "("+ Vector3_1+","+Vector3_2+")", Vector3_1, Vector3_2, paint);*/

            line(p, 0, 1,c); // aristas horizontales inferiores
            line(p, 1, 2,c);
            line(p, 2, 0,c);

            line(p, 1, 5,c); // aristas horizontales superiores
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
            p.setColor(getResources().getColor(R.color.cielo)); // Fondo Amarillo
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

        public class Obj_nuevo{	// Posee los datos del objeto 3D
            float rho, theta=0.3F, phi=1.3F, d, objSize, v11, v12, v13, v21, v22, v23, v32, v33, v43; // elementos de la matriz V
            Point3D [] w;	// coordenadas universales
            Point2D [] vScr; // coordenadas de la pantalla

            Obj_nuevo(){	// CAMBIAR LAS COORDENADAS X,Y,Z CON 0,1 PARA CONSTRUIR PRISMA, CILINDRO, PIRAMIDE, CONO Y ESFERA.
                w	= new Point3D[8];
                vScr	= new Point2D[8];


                //Cubo
                w[0] = new Point3D(1, -1, -1); // desde la base
                w[1] = new Point3D(1, 1, -1);
                w[2] = new Point3D(-1, 1, -1);
                w[3] = new Point3D(-1, -1, -1);
                w[4] = new Point3D(0, 0, 1); // Vertices de la parte superior
                w[5] = new Point3D(0, 0, 1);
                w[6] = new Point3D(0, 0, 1);
                w[7] = new Point3D(0, 0, 1);

                objSize = (float) Math.sqrt(12F); // = sqrt(2*2 + 2*2 + 2*2) es la distancia entre dos vertices opuestos
                rho	= 5*objSize;		// para cambiar la perspectiva
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