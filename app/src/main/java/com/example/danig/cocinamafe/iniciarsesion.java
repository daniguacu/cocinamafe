package com.example.danig.cocinamafe;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class iniciarsesion extends AppCompatActivity {
    DatabaseHelper DB;
    EditText e1,e2;
    Button B1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarsesion);
        DB=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.es1);
        e2=(EditText)findViewById(R.id.es2);
        B1=(Button)findViewById(R.id.bs1);
        t1=(TextView)findViewById(R.id.ts1);
    }

    public void metodoinicio(View view) {
        String email=e1.getText().toString();
        String contrasena=e2.getText().toString();
        Cursor res=DB.getData(email);
        String DATOS=null;
        if(res.moveToFirst()){
            DATOS=""+res.getString(6);
            if(DATOS.equals(contrasena)){
                Intent COM=new Intent(this,compras.class);
                startActivity(COM);
            }else{
                t1.setText("ERROR AL INGRESAR LA CONTRASEÑA");
            }
        }else{
            t1.setText("EL USUARIO INGRESADO NO EXISTE");
        }
    }
}
