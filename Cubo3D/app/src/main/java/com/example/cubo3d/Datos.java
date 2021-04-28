package com.example.cubo3d;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Datos extends AppCompatActivity {

    EditText px,py,pz,sx,sy,sz,tx,ty,tz;
    String pxv,pyv,pzv,sxv,syv,szv,txv,tyv,tzv;
    int pxvi,pyvi,pzvi,sxvi,syvi,szvi,txvi,tyvi,tzvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        px = (EditText) findViewById(R.id.vector1_1);
        py = (EditText) findViewById(R.id.vector1_2);
        pz = (EditText) findViewById(R.id.vector1_3);

        sx = (EditText) findViewById(R.id.vector2_1);
        sy = (EditText) findViewById(R.id.vector2_2);
        sz = (EditText) findViewById(R.id.vector2_3);

        tx = (EditText) findViewById(R.id.vector3_1);
        ty = (EditText) findViewById(R.id.vector3_2);
        tz = (EditText) findViewById(R.id.vector3_3);



        Button buttonActivityDos = findViewById(R.id.button2);
        buttonActivityDos.setOnClickListener( view -> {

            pxv = px.getText().toString();
            pyv = py.getText().toString();
            pzv = pz.getText().toString();
            sxv = sx.getText().toString();
            syv = sy.getText().toString();
            szv = sz.getText().toString();
            txv = tx.getText().toString();
            tyv = ty.getText().toString();
            tzv = tz.getText().toString();

            if(pxv.matches("") || pyv.matches("") || pzv.matches("")
                    || sxv.matches("") || syv.matches("") || szv.matches("")
                    || txv.matches("") || tyv.matches("") || tzv.matches("")){
                Toast.makeText(this, "Ingrese los valores que faltan", Toast.LENGTH_SHORT).show();
            }else {
                pxvi = Integer.valueOf(pxv);
                pyvi = Integer.valueOf(pyv);
                pzvi = Integer.valueOf(pzv);
                sxvi = Integer.valueOf(sxv);
                syvi = Integer.valueOf(syv);
                szvi = Integer.valueOf(szv);
                txvi = Integer.valueOf(txv);
                tyvi = Integer.valueOf(tyv);
                tzvi = Integer.valueOf(tzv);

                Intent intentLogin = new Intent(this, Generado.class);
                intentLogin.putExtra("Vector1_1", pxvi);
                intentLogin.putExtra("Vector1_2", pyvi);
                intentLogin.putExtra("Vector1_3", pzvi);
                intentLogin.putExtra("Vector2_1", sxvi);
                intentLogin.putExtra("Vector2_2", syvi);
                intentLogin.putExtra("Vector2_3", szvi);
                intentLogin.putExtra("Vector3_1", txvi);
                intentLogin.putExtra("Vector3_2", tyvi);
                intentLogin.putExtra("Vector3_3", tzvi);
                startActivity(intentLogin);
                Toast.makeText(this, "Este es el tetraedro generado por usted", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonActivityTres = findViewById(R.id.button3);
        buttonActivityTres.setOnClickListener( view -> {
            Intent intentLogin = new Intent(this, MainActivity.class);
            startActivity(intentLogin);
            Toast.makeText(this, "Este es el tetraedro generado de ejemplo", Toast.LENGTH_SHORT).show();
        });

        Button buttonActivityCuatro = findViewById(R.id.button4);
        buttonActivityCuatro.setOnClickListener( view -> {
            Toast.makeText(this, "Luis Enrique Chavarría Vázquez \n Examen primer parcial \n GRUPO 3CM14", Toast.LENGTH_SHORT).show();
        });

    }
}