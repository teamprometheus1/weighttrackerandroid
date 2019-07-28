package adv.dantoniopeluso.weighttracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    /*final String CREATE_TABLE = "CREATE TABLE " + databaseContract.pesos.TABLE_NAME + " (" +
            databaseContract.pesos.COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            databaseContract.pesos.COL2 + " TEXT, " + databaseContract.pesos.COL3 + " REAL) ";
            */
    //EditText txted1 = (EditText) findViewById(R.id.txtin1);
    //EditText txted2 = (EditText) findViewById(R.id.txtin2);
    Instant date = new Date().toInstant();
    String date2 = date.toString();
    String[] date3 = date2.split("T");


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText txted1 = (EditText) findViewById(R.id.txtin1);
        EditText txted2 = (EditText) findViewById(R.id.txtin2);

        //int peso = (int) Float.parseFloat(txted2.getText().toString());

        Instant date = new Date().toInstant();
        String date2 = date.toString();
        String[] date3 = date2.split("T");
        System.out.println(date3[0] + "////////////// /n " + date3[1]);



        System.out.println(date);

        txted1.setText(date3[0]);
        txted2.setText("90.4");
        /*
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(databaseContract.pesos.COL2, date3[0]);
        values.put(databaseContract.pesos.COL3, peso);
        long row = db.insert(databaseContract.pesos.TABLE_NAME, null, values);
        System.out.println("row number is: " + row);
        */


    }

    public void onClick(View view) {
        EditText txted1 = (EditText) findViewById(R.id.txtin1);
        EditText txted2 = (EditText) findViewById(R.id.txtin2);
        if (txted2.length() > 1){
            int peso = (int) Float.parseFloat(txted2.getText().toString());
        }

        switch (view.getId()) {
            case R.id.btn_save:
                if (txted2.length() > 1) {
                    int peso = (int) Float.parseFloat(txted2.getText().toString());
                    DatabaseHelper dbHelper = new DatabaseHelper(this);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(databaseContract.pesos.COL2, date3[0]);
                    values.put(databaseContract.pesos.COL3, peso);
                    //long row = db.insert(databaseContract.pesos.TABLE_NAME, null, values);
                    if (db.insert(databaseContract.pesos.TABLE_NAME, null, values) != -1) {
                        Toast toast = Toast.makeText(this, "Data Saved! :)", Toast.LENGTH_LONG);
                        toast.show();
                        System.out.println(txted1.getText().toString());
                        System.out.println(txted2.getText().toString());

                        //txted1.setText(date3[0].toString());
                        txted2.setText("");
                    }
                    //System.out.println("row number is: " + row);
                }else{
                    Toast toast = Toast.makeText(this, "Peso nao pode estar vazio", Toast.LENGTH_LONG);
                    toast.show();
                }

                break;

            case R.id.btn_clr:
                DatabaseHelper dbHelper1 = new DatabaseHelper(this);
                SQLiteDatabase db1 = dbHelper1.getWritableDatabase();
                db1.execSQL("drop table if exists pesos");
                Toast toast = Toast.makeText(this, "Database Cleared!", Toast.LENGTH_LONG);
                toast.show();
                    databaseContract.pesos.version++;
                break;

            case R.id.btn_show:

                DatabaseHelper dbHelper2 = new DatabaseHelper(this);
                SQLiteDatabase db = dbHelper2.getReadableDatabase();
                String getColumns[] = {databaseContract.pesos.COL2, databaseContract.pesos.COL3};
                Cursor c = db.query(databaseContract.pesos.TABLE_NAME, getColumns, null, null, null, null, null);
                c.moveToFirst();
                String query = "SELECT * FROM " + databaseContract.pesos.TABLE_NAME;
                Cursor cursor = db.rawQuery(query, null);
                ArrayList list = new ArrayList();
                while (cursor.moveToNext()){
                    list.add(cursor.getString(0));
                    list.add(cursor.getString(1));
                    list.add(cursor.getString(2));
                }
                cursor.close();
                db.close();
                System.out.println(list);

                //System.out.println("cursor retornou " + c.getString(1));
                c.close();
                break;

        }
    }

}
