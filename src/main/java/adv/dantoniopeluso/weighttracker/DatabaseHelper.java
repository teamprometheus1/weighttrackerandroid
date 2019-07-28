package adv.dantoniopeluso.weighttracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    final String CREATE_TABLE = "CREATE TABLE " + databaseContract.pesos.TABLE_NAME + " (" +
            databaseContract.pesos.COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            databaseContract.pesos.COL2 + " TEXT, " + databaseContract.pesos.COL3 + " REAL); ";
    int version = 1;

    public DatabaseHelper(Context context) {
        super(context, databaseContract.pesos.TABLE_NAME, null, databaseContract.pesos.version);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        /*final String CREATE_DATABASE = "CREATE TABLE " + databaseContract.pesos.TABLE_NAME + " (" +
                databaseContract.pesos.COL1 + "INTEGER PRIMARY KEY, " +
                databaseContract.pesos.COL2 + "TEXT, " + databaseContract.pesos.COL3 + "REAL) ";
        */
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+ databaseContract.pesos.TABLE_NAME);
        i++;
        onCreate(db);


    }

    @Override
    public void onDowngrade (SQLiteDatabase db, int i, int ii){
        version = 1;
    }
}
