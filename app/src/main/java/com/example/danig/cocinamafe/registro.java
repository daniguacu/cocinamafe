package com.example.danig.cocinamafe;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class registro extends AppCompatActivity {
    int op=0;
    LinearLayout CONTE;
    Button B1;
    int cambio=0;

    Fragment FRAGMENT;
    EditText e1,e2,e4,e5,e6,e7,p,pc;
    TextView t1;
    DatabaseHelper DB;
    CheckBox check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        CONTE=(LinearLayout)findViewById(R.id.contenedor);
        CONTE.setVisibility(View.INVISIBLE);
        B1=(Button)findViewById(R.id.b1);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e4=(EditText)findViewById(R.id.e4);
        e5=(EditText)findViewById(R.id.e5);
        e6=(EditText)findViewById(R.id.e6);
        e7=(EditText)findViewById(R.id.e7);
        t1=(TextView)findViewById(R.id.t1);
        check=(CheckBox)findViewById(R.id.check);
        DB=new DatabaseHelper(this);
    }
    public void metodoPolitica(View view){
        switch (op){
            case 0:
                CONTE.setVisibility(View.VISIBLE);
                FRAGMENT=new Fragmento1();
                op=1;
                cargarfragmento(FRAGMENT);
                break;
            case 1:
                FRAGMENT=new Fragmento2();
                op=2;
                cargarfragmento(FRAGMENT);
                break;
            case 2:
                CONTE.setVisibility(View.INVISIBLE);
                op=0;
                //break;


        }

    }
    private void cargarfragmento(Fragment FRAGMENT){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.fragment2,FRAGMENT);
        transaction.commit();

    }


    public void crearusuario(View view) {
        String nombre,apellido,telefono,cedula,email,contrasena;
        int v1,v2,v3,v6;
        nombre=e1.getText().toString();
        v1=validarCaracteres(1,nombre);
        apellido=e2.getText().toString();
        v2=validarCaracteres(1,apellido);
        telefono=e4.getText().toString();
        v3=validarCaracteres(2,telefono);
        cedula=e5.getText().toString();
        email=e6.getText().toString();
        contrasena=e7.getText().toString();
        v6=validarCaracteres(3,contrasena);
        if(v1!=1){
            t1.setText("ERROR, EL NOMBRE SOLO DEBE CONTENER CARACTERES");
        } else{
            if (v2!=1){
                t1.setText("ERROR, EL APELLIDO SOLO DEBE CONTENER CARACTERES");

            }else {
                if(v3!=1){
                    t1.setText("ERROR, EL TELEFONO SOLO DEBE CONTENER NUMEROS");
                }else{
                    if(v6!=1){
                        if(v6==2){
                            t1.setText("ERROR,LA CONTRASEÑA DEBE CONTENER NUMEROS");
                        }else{
                            if(v6==3){
                                t1.setText("LA CONTRASEÑA DEBE CONTENER MINUSCULAS");
                            }else{
                                if(v6==4){
                                    t1.setText("LA CONTRASEÑA DEBE CONTENER MAYUSCULAS");
                                }
                                else{
                                    if(v6==5){
                                        t1.setText("LA CONTRASEÑA DEBE CONTENER AL MENOS UN CARACTER ESPECIAL");
                                    }else{
                                        if(v6==0){
                                            t1.setText("LA CONTRASEÑA DEBE CONTENER AL MENOS 8 CARACTERES");
                                        }else{
                                            if(check.isChecked()==true){
                                                Cursor res=DB.getData(email);
                                                if(res.moveToFirst()){
                                                    String Datos="ESTE USUARIO YA EXISTE";
                                                    t1.setText(Datos);
                                                }else{
                                                    if (DB.insertData(nombre,apellido,telefono,cedula,email,contrasena)){
                                                        t1.setText("registro Exitoso");
                                                    }else{
                                                        t1.setText("error");
                                                    }
                                                }
                                            }else{
                                                t1.setText("DEBE ACEPTA LA POLÍTICA DE TRATAMIENTO DE DATOS");

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                }

            //no se creará el usuario, se puede mejorar colocando un condicionaldentro de otro ondiiconal e i cambiando los mensajes de que tipo de error existe

            }


    }

    private int validarCaracteres(int i, String cadena) {
        int validacion=0,pos,c;
        String resultado;
        switch(i){
            case 1:
                //verificarcaracteresalfabéticos
                for(pos=0;pos<=cadena.length();pos=pos+1)
                {
                    c=cadena.charAt(pos);
                    if((c>=67 && c<=90)||(c>=97&&c<=122)){
                        validacion=1;

                    }else{
                        validacion=0;
                        pos=cadena.length();
                    }

                }
                break;
            case 2:
                for(pos=0;pos<=cadena.length();pos=pos+1)
                {
                    c=cadena.charAt(pos);
                    if(c>=48 && c<=57){
                        validacion=0;
                        pos=cadena.length();
                    }else{
                        validacion=1;
                    }

                }
                break;
            case 3:
                int contanum=0;
                int contacap=0;
                int contamin=0;
                int contasigno=0;
                if (cadena.length()>=8) {
                for (pos = 0; pos <= cadena.length(); pos = pos + 1) {
                    c = cadena.charAt(pos);
                    if (c == 48 && c <= 57) {
                        contanum=contanum+1;

                    }
                    if (c > 64 && c < 91){
                        contacap=contacap+1;
                    }
                    if (c >= 97 && c <= 122){
                        contamin=contamin+1;

                    }
                    if (c >= 32 && c <= 44){
                        contasigno=contasigno+1;
                    }

                }if(contanum>=1&&contamin>=1&&contacap>=1&&contasigno>=1){
                        validacion=1;
                    }else{
                        if(contanum<1){
                            validacion=2;
                        }else{
                            if(contamin<1){
                                validacion=3;
                            }else{
                                if (contacap<1){
                                    validacion=4;
                                }else{
                                    if(contasigno<1){
                                        validacion=5;
                                    }
                                }
                            }
                        }
                    }
                }
                else{
                        validacion=0;

                    }  break;
        } return validacion;



    }
}
