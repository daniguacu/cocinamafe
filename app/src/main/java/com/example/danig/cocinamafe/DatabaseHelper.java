package com.example.danig.cocinamafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME="DB_PRUEBA";
    public final static String TABLE1_NAME="USUARIOS";
    public final static String TABLE2_NAME="COMPRAS";
    public final static String COL1_1="ID";
    public final static String COL2_1="NOMBRE";
    public final static String COL3_1="APELLIDO";
    public final static String COL4_1="TELEFONO";
    public final static String COL5_1="CEDULA";
    public final static String COL6_1="EMAIL";
    public final static String COL7_1="CONTRASENA";
    public final static String COL1_2="ID";
    public final static String COL2_2="EMAIL";
    public final static String COL3_2="ARTICULO";
    public final static String COL4_2="CANTIDAD";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS"+TABLE1_NAME+
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ "NOMBRE TEXT, APELLIDO TEXT,TELEFONO INTEGER,CEDULA TEXT,EMAIL TEXT,CONTRASENA TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS"+TABLE2_NAME+
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ "EMAIL TEXT, ARTICULO TEXT,CANITIDAD INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS"+TABLE2_NAME);
        onCreate(db);

    }
    public boolean insertData(String nombre, String apellido, String telefono,String cedula,String email,String contrasena){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL2_1,nombre);
        cv.put(COL3_1,apellido);
        cv.put(COL4_1,telefono);
        cv.put(COL5_1,cedula);
        cv.put(COL6_1,email);
        cv.put(COL7_1,contrasena);
        long result=db.insert(TABLE1_NAME,null,cv);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getData(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        //Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+"WHERE ID='"+id+"'",null);
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE1_NAME+"WHERE EMAIL='"+email+"'",null);
        return cursor;
        //reemplazarstring id por string telefono y cambiarlo en la otra app, insertarllenaar cedula en el formulario
    }
    public boolean updateData(String id,String nombre, String apellido, String telefono,String cedula,String email,String contrasena){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL2_1,nombre);
        cv.put(COL3_1,apellido);
        cv.put(COL4_1,telefono);
        cv.put(COL5_1,cedula);
        cv.put(COL6_1,email);
        cv.put(COL7_1,contrasena);
        long result=db.update(TABLE1_NAME,cv,"EMAIL=?",new String[]{email});
        if (result==-1){
            return false;
        }else{
            return true;
        }

    }
    public boolean deleteData(String email){

        SQLiteDatabase db=this.getWritableDatabase();

        long result=db.delete(TABLE1_NAME,"EMAIL=?",new String[]{email});
        if (result==-1){
            return false;
        }else{
            return true;
        }

    }
}
