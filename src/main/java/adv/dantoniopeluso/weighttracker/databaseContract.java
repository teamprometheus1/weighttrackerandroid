package adv.dantoniopeluso.weighttracker;

import android.provider.BaseColumns;

public final class databaseContract {

    public databaseContract(){}

    public static class pesos implements BaseColumns {

        public static final String TABLE_NAME = "PESOS";
        public static final String COL1 = "_ID";
        public static final String COL2 = "DATAPESAGEM";
        public static final String COL3 = "PESO";
        public static int version = 3;


    }



}
